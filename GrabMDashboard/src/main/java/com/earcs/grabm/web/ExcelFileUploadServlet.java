package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.util.ExcelProcessor;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.pojo.CrewMember;
import com.earcs.grabm.util.pojo.Flight;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Roshin Perera
 */
@MultipartConfig
@WebServlet(name = "ExcelFileUpload", urlPatterns = {"/fileupload"})
public class ExcelFileUploadServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private static final long serialVersionUID = -3871559060744528505L;

    private final Logger logger = Logger.getLogger(ExcelFileUploadServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        final Part flightPart = request.getPart("flight_schedule_upload_file");
        final Part crewPart = request.getPart("crew_schedule_upload_file");
        String type = request.getParameter("type");

        try {

            ExcelProcessor excel_processor;
            File temp_file;
            switch (type) {
                case "flight":
                    temp_file = getTempFile(flightPart);
                    excel_processor = new ExcelProcessor(temp_file);
                    
                    List<Flight> flights = excel_processor.rowIterator_FlightSchedule();
                    final JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                    
                    flights.parallelStream().forEachOrdered((Flight flight) -> {
                        jsonArrayBuilder.add(flight.toJson());
                    });
                    
                    out.print(jsonArrayBuilder.build().toString());
                    break;
                case "crew":
                    temp_file = getTempFile(crewPart);
                    excel_processor = new ExcelProcessor(temp_file);
                    
                    List<CrewMember> crewList = excel_processor.rowIterator_CrewList();
                    
                    GrabmRESTClient client = new GrabmRESTClient();
                    Invocation.Builder builder = client
                            .getTarget().path("/enduser/validate")
                            .request()
                            .headers(GrabmDashboardConstant.Bundle.getHeaders());
                    
                    String endUserJson = builder.accept(MediaType.APPLICATION_JSON_TYPE)
                            .post(Entity.json(new GenericEntity<List<CrewMember>>(crewList) {
                            }), String.class);
                    
                    out.print(endUserJson);
                    client.getClient().close();
                    break;
                default:
                    throw new FileNotFoundException("Uploaded file not found");
            }

        } catch (IOException | InvalidFormatException ex) {
            logger.error(ex.getMessage(), ex);
            out.print("[]");
        }
    }

    private File getTempFile(Part part) throws IOException {
        try (InputStream is = part.getInputStream()) {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            File temp_file = File.createTempFile(part.getSubmittedFileName(), null);
            try (FileOutputStream fos = new FileOutputStream(temp_file)) {
                fos.write(buffer);
                fos.flush();
            }

            return temp_file;
        }
    }
}
