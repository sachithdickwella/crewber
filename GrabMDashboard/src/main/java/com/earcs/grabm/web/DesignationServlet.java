package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.Designation;
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
@WebServlet(name = "DesignationServlet", urlPatterns = {"/designationweb"})
public class DesignationServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(DesignationServlet.class);
    private static final long serialVersionUID = -4734782474607123138L;

    @SuppressWarnings("null")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain; charset=UTF8");
        boolean isValid = true;

        String longName = request.getParameter("crew_member_designation_long_name"),
                shortName = request.getParameter("crew_member_designation_short_name"),
                memberType = request.getParameter("crew_member_type"),
                reportHoursBefore = request.getParameter("crew_member_type_reporting_time");

        if (longName == null || shortName == null
                || memberType == null || reportHoursBefore
                == null) {
            isValid = false;
        } else {
            longName = longName.trim();
            shortName = shortName.trim();
            memberType = memberType.trim();
            reportHoursBefore = reportHoursBefore.trim();

            if (longName.equals("") || shortName.equals("")
                    || memberType.equals("") || reportHoursBefore.equals("")) {
                isValid = false;
            }
        }

        if (isValid) {
            Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);
            if (admin != null) {
                GrabmRESTClient client = new GrabmRESTClient();
                Invocation.Builder builder = client
                        .getTarget().path("/designation/create")
                        .request()
                        .headers(GrabmDashboardConstant.Bundle.getHeaders());
                try {
                    Designation designation = new Designation();
                    designation.setShortName(shortName);
                    designation.setLongName(longName);
                    designation.setMemberType(memberType.charAt(0));

                    SimpleDateFormat formatter = new SimpleDateFormat("HHmm");

                    if (reportHoursBefore.length() > 4) {
                        throw new IllegalArgumentException("Invalid input date value/format");
                    } else if (!reportHoursBefore.startsWith("0")) {
                        int date = Integer.parseInt(reportHoursBefore);
                        if (date <= 2359 && date >= 0) {
                            designation.setHoursBeforeReport(formatter.parse(reportHoursBefore));
                        } else {
                            throw new IllegalArgumentException("Invalid input date value/format");
                        }
                    } else {
                        designation.setHoursBeforeReport(formatter.parse(reportHoursBefore));
                    }

                    designation.setCreateUser(admin.getId());
                    designation.setLastupdateUser(admin.getId());

                    String stat = builder.put(Entity.json(designation), String.class);
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
