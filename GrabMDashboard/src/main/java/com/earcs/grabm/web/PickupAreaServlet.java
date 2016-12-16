package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.PickupArea;
import com.earcs.grabm.util.GrabmDashboardConstant;
import static com.earcs.grabm.util.GrabmDashboardConstant.Attributes.SESSION_ADMIN;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import org.apache.log4j.Logger;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "PickupAreaServlet", urlPatterns = {"/pickupareaweb"})
public class PickupAreaServlet extends HttpServlet {

    private static final long serialVersionUID = 3520958633061058989L;
    private final Logger logger = Logger.getLogger(PickupAreaServlet.class);

    @SuppressWarnings("null")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain; charset=UTF8");
        boolean isValid = true;

        String pickupArea = request.getParameter("user_area"),
                pickupTimeBefore = request.getParameter("user_area_first_pickup_time");

        if (pickupArea == null || pickupTimeBefore == null) {
            isValid = false;
        } else {
            pickupArea = pickupArea.trim();
            pickupTimeBefore = pickupTimeBefore.trim();

            if (pickupArea.equals("") || pickupTimeBefore.equals("")) {
                isValid = false;
            }
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/pickuparea/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());
                try {
                    PickupArea pa = new PickupArea();
                    pa.setName(pickupArea);

                    SimpleDateFormat formatter = new SimpleDateFormat("HHmm");

                    if (pickupTimeBefore.length() > 4) {
                        throw new IllegalArgumentException("Invalid input date value/format");
                    } else if (!pickupTimeBefore.startsWith("0")) {
                        int date = Integer.parseInt(pickupTimeBefore);
                        if (date <= 2359 && date >= 0) {
                            pa.setPickupTime(formatter.parse(pickupTimeBefore));
                        } else {
                            throw new IllegalArgumentException("Invalid input date value/format");
                        }
                    } else {
                        pa.setPickupTime(formatter.parse(pickupTimeBefore));
                    }

                    pa.setCreateUser(admin.getId());
                    pa.setLastupdateUser(admin.getId());

                    String stat = builder.put(Entity.json(pa), String.class);
                    out.print(stat);
                } catch (IllegalArgumentException | ParseException ex) {
                    logger.error(ex.getMessage(), ex);
                    out.print(GrabmDashboardConstant.CLIENT_ERROR);
                } finally {
                    client.getClient().close();
                }
            } else {
                out.print(GrabmDashboardConstant.SESSION_TIMEOUT);
            }
        } else {
            out.print(GrabmDashboardConstant.VALIDATION_FAILED);
        }
    }
}
