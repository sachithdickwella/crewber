package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import com.earcs.grabm.util.pojo.ScheduledUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "PickupScheduleSubmitServlet", urlPatterns = {"/pickupschedulesubmitweb"})
public class PickupScheduleSubmitServlet extends HttpServlet {

    private static final long serialVersionUID = 2023589844581287851L;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("plain/text; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tableData = request.getParameter("tableData");

        JsonReader reader = Json.createReader(new StringReader(tableData));
        JsonArray jsonArray = reader.readArray();

        final List<ScheduledUser> scheduledUsers = new ArrayList<>();
        IntStream.range(0, jsonArray.size())
                .forEachOrdered(idx -> {
                    switch (jsonArray.get(idx).getValueType()) {
                        case OBJECT:
                            JsonObject jsonObject = Json.createReader(new StringReader(jsonArray.get(idx).toString())).readObject();
                            JsonArray groupArray = jsonObject.getJsonArray("group");

                            groupArray.stream().forEachOrdered((JsonValue groupValue) -> {
                                switch (groupValue.getValueType()) {
                                    case OBJECT:
                                        JsonObject jsonGroupObject = Json.createReader(new StringReader(groupValue.toString())).readObject();
                                        ScheduledUser scheduledUser = new ScheduledUser();
                                        scheduledUser.setId(Long.parseLong(jsonGroupObject.get("id").toString()));
                                        scheduledUser.setPickupIndex(jsonGroupObject.getInt("pickupIndex"));

                                        String[] pickupCoordinates = jsonGroupObject.getString("pickupPoint").split(",");
                                        scheduledUser.setPickupLatitude(Double.parseDouble(pickupCoordinates[0].trim()));
                                        scheduledUser.setPickupLongitude(Double.parseDouble(pickupCoordinates[1].trim()));

                                        String dropoffPoint = jsonGroupObject.getString("dropoffPoint");
                                        if (dropoffPoint.trim().equals("BIA")) {
                                            scheduledUser.setDropoffLatitude(GrabmDashboardConstant.BIA_LATLANG[0]);
                                            scheduledUser.setDropoffLongitude(GrabmDashboardConstant.BIA_LATLANG[1]);
                                        } else {
                                            String[] dropoffCoordinates = dropoffPoint.split(",");
                                            scheduledUser.setDropoffLatitude(Double.parseDouble(dropoffCoordinates[0].trim()));
                                            scheduledUser.setDropoffLongitude(Double.parseDouble(dropoffCoordinates[1].trim()));
                                        }
                                        scheduledUser.setVehicleGroup("GROUP " + (idx + 1));
                                        scheduledUsers.add(scheduledUser);
                                        break;
                                }
                            });
                            break;
                    }
                });
        final Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
        if (admin != null) {
            GrabmRESTClient client = new GrabmRESTClient();
            Invocation.Builder builder = client
                    .getTarget().path("/pickupschedule/update/bulk/" + admin.getId())
                    .request()
                    .headers(GrabmDashboardConstant.Bundle.getHeaders());

            String result = builder.post(Entity.json(new GenericEntity<List<ScheduledUser>>(scheduledUsers) {
            }), String.class);
            if (result != null) {
                out.println(result);
            }
            client.getClient().close();
        }
    }
}
