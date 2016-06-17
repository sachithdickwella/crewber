package com.grabm.service.subresource;

import com.grabm.DownstreamHTTPMessages;
import com.grabm.entity.EndUser;
import com.grabm.entity.LatestTracking;
import com.grabm.entity.pojo.GoogleDistanceMatrixInfo;
import com.grabm.factory.AbstractFacade;
import com.grabm.service.client.GCMClient;
import com.grabm.service.client.GMDMClient;
import com.grabm.util.GrabMConstant;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sachith Dickwella
 */
public class LocationServiceResource
        extends AbstractFacade<LatestTracking>
        implements GrabMConstant {

    private static int COUNT1 = 0;
    private static int COUNT2 = 0;
    private static double[][] coordinates;

    public LocationServiceResource() {
        super(LatestTracking.class);
        //coordinates = (double[][]) context.getAttribute(CONTEXT_DUMMY_DATA);
    }

    @POST
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LatestTracking> getAllTracking() {
        final List<LatestTracking> _TRACKINGS = new LinkedList<>();
        ONGOING_TRACKINGS.values()
                .parallelStream()
                .forEach((LatestTracking t) -> {
                    _TRACKINGS.add(t);
                });
        return _TRACKINGS;
    }

    @POST
    @Path("/latest/{memno}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LatestTracking/*List<ClientTrip>*/ getLatestTrip(@PathParam("memno") String memno) {
        /*Session session = sessionFactory.openSession();
        try {
            Criteria criteriaOne = session.createCriteria(PickupSchedule.class);
            criteriaOne.createAlias("enduserId", "enduser");
            criteriaOne.add(Restrictions.eq("enduser.membershipNumber", memno));
            List<PickupSchedule> list = criteriaOne.list();
            if (list.size() > 0) {

                LatestTracking lt = ONGOING_TRACKINGS.get(list.get(0).getVehicleDriver().getVehicle().getTrackerImei());

                Invocation.Builder builder = new GMDMClient().getTarget()
                        .queryParam("key", GoogleMapDistanceMatrixClient.GOOGLE_MAP_DISTANCE_MATRIX_SERVER_KEY)
                        .queryParam("origin", lt.getLatitude() + "," + lt.getLongitude())
                        .queryParam("destination", list.get(0).getEnduserId().getHomeLatitude() + "," + list.get(0).getEnduserId().getHomeLongitude())
                        .queryParam("units", "metric")
                        .request()
                        .accept(MediaType.APPLICATION_JSON_TYPE);

                String distanceMatrix = builder.get(String.class);
                JsonObject jsonMatrix;
                try (JsonReader jsonReader = Json.createReader(new StringReader(distanceMatrix))) {
                    jsonMatrix = jsonReader.readObject();
                }

                GoogleDistanceMatrixInfo info = new GoogleDistanceMatrixInfo();
                info.setOrigin(jsonMatrix.getJsonArray("origin_addresses").getString(0));
                info.setDestination(jsonMatrix.getJsonArray("destination_addresses").getString(0));
                info.setDuration(jsonMatrix.getJsonArray("rows").getJsonArray(0).getJsonObject(0).getString("text"));
                info.setDistance(jsonMatrix.getJsonArray("rows").getJsonArray(0).getJsonObject(1).getString("text"));

                Criteria criteriaTwo = session.createCriteria(PickupSchedule.class);
                criteriaTwo.add(Restrictions.eq("pickupId", list.get(0).getPickupId()));
                list = criteriaTwo.list();

                List<ClientTrip> clientTrips = new LinkedList<>();
                list.parallelStream().forEach((PickupSchedule t) -> {
                    ClientTrip clientTrip = new ClientTrip();
                    clientTrip.setPickupIndex(t.getPickupIndex());
                    clientTrip.setDriverFisrtName(t.getVehicleDriver().getDriver().getFirstName());
                    clientTrip.setDriverLastName(t.getVehicleDriver().getDriver().getLastName());
                    clientTrip.setDriverMobileNumber(t.getVehicleDriver().getDriver().getMobileNumber1());
                    clientTrip.setHomeLatitude(t.getEnduserId().getHomeLatitude());
                    clientTrip.setHomeLongitude(t.getEnduserId().getHomeLongitude());

                    clientTrip.setVehicleLatitude(lt.getLatitude());
                    clientTrip.setVehicleLongitude(lt.getLongitude());
                    clientTrip.setGoogleDistanceMatrixInfo(info);

                    clientTrips.add(clientTrip);
                });

                return clientTrips;
            }
        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }*/
        //Invocation.Builder builder = new GMDMClient().getTarget();

        LatestTracking latestTracking = new LatestTracking();
        latestTracking.setImie("359710046180452");
        latestTracking.setAlarm("tracker");
        latestTracking.setSpeed(25.6);
        latestTracking.setValidity(true);
        latestTracking.setLatitude(coordinates[COUNT1][0]);
        latestTracking.setLongitude(coordinates[COUNT1++][1]);
        latestTracking.setTrackerDateTime(new Date());
        //return latestTracking;//ONGOING_TRACKINGS.get("359710046180452");;
        return null;
    }

    @POST
    @Path("/google/{memno}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public GoogleDistanceMatrixInfo getLocationData(@PathParam("memno") String memno) {

        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(EndUser.class);
            criteria.add(Restrictions.eq("membershipNumber", memno));
            List<EndUser> users = criteria.list();

            if (users.size() > 0) {
                EndUser user = users.get(0);

                //LatestTracking lt = ONGOING_TRACKINGS.get("359710046180452");
                LatestTracking lt = new LatestTracking();
                lt.setLatitude(coordinates[COUNT2][0]);
                lt.setLongitude(coordinates[COUNT2++][1]);

                Invocation.Builder builder = new GMDMClient().getTarget()
                        .queryParam("key", GoogleMapDistanceMatrixClient.GOOGLE_MAP_DISTANCE_MATRIX_SERVER_KEY)
                        .queryParam("origins", lt.getLatitude() + "," + lt.getLongitude())
                        .queryParam("destinations", user.getHomeLatitude() + "," + user.getHomeLongitude())
                        .queryParam("units", "metrics")
                        .request()
                        .accept(MediaType.APPLICATION_JSON_TYPE);

                String distanceMatrix = builder.get(String.class);
                JsonObject jsonMatrix;
                try (JsonReader jsonReader = Json.createReader(new StringReader(distanceMatrix))) {
                    jsonMatrix = jsonReader.readObject();
                }

                GoogleDistanceMatrixInfo info = new GoogleDistanceMatrixInfo();
                info.setOrigin(jsonMatrix.getJsonArray("origin_addresses").getString(0));
                info.setDestination(jsonMatrix.getJsonArray("destination_addresses").getString(0));
                info.setDuration(jsonMatrix.getJsonArray("rows").getJsonObject(0)
                        .getJsonArray("elements").getJsonObject(0).getJsonObject("duration").getString("text"));
                info.setDistance(jsonMatrix.getJsonArray("rows").getJsonObject(0)
                        .getJsonArray("elements").getJsonObject(0).getJsonObject("distance").getString("text"));

                int distance = jsonMatrix.getJsonArray("rows").getJsonObject(0)
                        .getJsonArray("elements").getJsonObject(0).getJsonObject("distance").getInt("value");

                return info;
            }
        } catch (HibernateException | NumberFormatException ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }
        return null;
    }

    public void createNotification(final String memNo, String title, String message, String type) {
        Map<String, String> data = new HashMap<>();
        data.put("title", title);
        data.put("message", message);
        data.put("type", type);

        EndUser endUser = null;
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(EndUser.class);
            criteria.add(Restrictions.eq("membershipNumber", memNo));
            List<EndUser> endUsers = criteria.list();
            if (endUsers.size() > 0) {
                endUser = endUsers.get(0);
            }

        } catch (Exception ex) {
            logger.error(ENTITY_EXCEPTION_FETCH, ex);
        } finally {
            session.close();
        }

        if (endUser != null) {
            DownstreamHTTPMessages.Payload payload
                    = new DownstreamHTTPMessages.Payload();
            payload.setTo(endUser.getGcmToken())
                    .setPriority(DownstreamHTTPMessages.Priorities.HIGH.toString().toLowerCase())
                    .setData(data);

            GCMClient client = new GCMClient();
            Response response = client.request(Entity.entity(payload.toJson().toString(), MediaType.APPLICATION_JSON));
            if (response.getStatus() != 200) {
                logger.error("Notification sending failed due to connection issue.");
            }
        }
    }
}
