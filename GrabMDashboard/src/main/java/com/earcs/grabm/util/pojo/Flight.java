package com.earcs.grabm.util.pojo;

import java.io.Serializable;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight implements Serializable {

    private static final long serialVersionUID = -7540155054601595155L;
    private static final Logger LOGGER = Logger.getLogger(Flight.class);
  
    private String flightNumber;
    private Date departureDateTime;
    private String departureFrom;
    private Date arriveDateTime;
    private String arriveTo;

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the departureDateTime
     */
    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * @param departureDateTime the departureDateTime to set
     */
    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    /**
     * @return the departureFrom
     */
    public String getDepartureFrom() {
        return departureFrom;
    }

    /**
     * @param departureFrom the departureFrom to set
     */
    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    /**
     * @return the arriveDateTime
     */
    public Date getArriveDateTime() {
        return arriveDateTime;
    }

    /**
     * @param arriveDateTime the arriveDateTime to set
     */
    public void setArriveDateTime(Date arriveDateTime) {
        this.arriveDateTime = arriveDateTime;
    }

    /**
     * @return the arriveTo
     */
    public String getArriveTo() {
        return arriveTo;
    }

    /**
     * @param arriveTo the arriveTo to set
     */
    public void setArriveTo(String arriveTo) {
        this.arriveTo = arriveTo;
    }

    @XmlTransient
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("flightNumber", flightNumber)
                .add("departureDateTime", departureDateTime.toString())
                .add("departureFrom", departureFrom)
                .add("arriveDateTime", arriveDateTime.toString())
                .add("arriveTo", arriveTo)
                .build();
    }

    @XmlTransient
    public static List<Flight> fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = reader.readArray();

        final SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");

        final List<Flight> flights = new ArrayList<>();
        jsonArray.stream().forEachOrdered((JsonValue jsonValue) -> {
            Flight flight = new Flight();
            switch (jsonValue.getValueType()) {
                case OBJECT:
                    try {
                        JsonObject jsonObject = Json.createReader(new StringReader(jsonValue.toString())).readObject();
                        flight.setFlightNumber(jsonObject.getString("flightNumber"));

                        String departureDateTimeString = jsonObject.getString("departureDateTime");
                        String arrivalDateTimeString = jsonObject.getString("arriveDateTime");

                        flight.setDepartureDateTime(departureDateTimeString != null ? formatter.parse(departureDateTimeString) : null);
                        flight.setArriveDateTime(arrivalDateTimeString != null ? formatter.parse(arrivalDateTimeString) : null);

                        flight.setDepartureFrom(jsonObject.getString("departureFrom"));
                        flight.setArriveTo(jsonObject.getString("arriveTo"));
                        
                        flights.add(flight);
                    } catch (ParseException ex) {
                        LOGGER.error(ex.getMessage(), ex);
                    }
                    break;
            }
        });
        return flights;
    }

    @XmlTransient
    @Override
    public String toString() {
        return toJson().toString();
    }
}
