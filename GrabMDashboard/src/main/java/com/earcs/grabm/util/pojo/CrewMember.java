package com.earcs.grabm.util.pojo;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roshin Perera
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CrewMember implements Serializable {

    private static final long serialVersionUID = 7458014990346337896L;

    private String firstName;
    private String lastName;
    private String designation;
    private char level;
    private String memberNumber;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the level
     */
    public char getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(char level) {
        this.level = level;
    }

    /**
     * @return the memberNumber
     */
    public String getMemberNumber() {
        return memberNumber;
    }

    /**
     * @param memberNumber the memberNumber to set
     */
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    @XmlTransient
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("designation", designation)
                .add("level", level)
                .add("memberNumber", memberNumber)
                .build();
    }

    @XmlTransient
    public static List<CrewMember> fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = reader.readArray();

        final List<CrewMember> crewMembers = new ArrayList<>();
        jsonArray.stream().forEach((JsonValue jsonValue) -> {
            CrewMember crewMember = new CrewMember();
            switch (jsonValue.getValueType()) {
                case OBJECT:

                    JsonObject jsonObject = Json.createReader(new StringReader(jsonValue.toString())).readObject();

                    crewMember.setFirstName(jsonObject.getString("firstName"));
                    crewMember.setLastName(jsonObject.getString("lastName"));
                    crewMember.setMemberNumber(jsonObject.getString("membershipNumber"));

                    JsonObject designationJsonObj = Json.createReader(new StringReader(jsonObject.get("designation").toString())).readObject();
                    crewMember.setDesignation(designationJsonObj.getString("shortName"));
                    crewMember.setLevel(designationJsonObj.getString("memberType").charAt(0));

                    crewMembers.add(crewMember);
                    break;
            }
        });
        return crewMembers;
    }

    @XmlTransient
    @Override
    public String toString() {
        return this.toJson().toString();
    }
}
