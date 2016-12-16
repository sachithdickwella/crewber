package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.util.GrabmDashboardConstant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
@WebServlet(name = "CommonServlet", urlPatterns = "/commons")
public class CommonServlet extends HttpServlet
        implements GrabmDashboardConstant {

    private static final long serialVersionUID = 6714720850054935465L;
    private final Logger LOGGER = Logger.getLogger(CommonServlet.class);

    private enum Parameter {
        URL, BODY, ACCEPT, CONTENT;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String rest = processRequest(request, response, null, GrabmDashboardConstant.Method.GET);

        if (rest != null) {
            out.println(rest);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String rest = processRequest(request, response, null, GrabmDashboardConstant.Method.POST);

        if (rest != null) {
            out.println(rest);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        final JsonObject data = Json.createReader(request.getInputStream()).readObject();
        String rest = processRequest(request, response, data, GrabmDashboardConstant.Method.PUT);

        if (rest != null) {
            out.println(rest);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String rest = processRequest(request, response, null, GrabmDashboardConstant.Method.DELETE);

        if (rest != null) {
            out.println(rest);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Intercept the AJAX request from the dashboard front end.
     *
     * @param request
     * @param response
     * @param data
     * @param method
     * @return
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public String processRequest(HttpServletRequest request, HttpServletResponse response,
            final JsonObject data, final GrabmDashboardConstant.Method method)
            throws IOException, ServletException {
        final String url, body, accept_type, content_type;
        if (data != null) {
            url = data.getString(Parameter.URL.toString());
            body = data.getString(Parameter.BODY.toString());
            accept_type = data.getString(Parameter.ACCEPT.toString());
            content_type = data.getString(Parameter.CONTENT.toString());
        } else {
            url = request.getParameter(Parameter.URL.toString());
            body = request.getParameter(Parameter.BODY.toString());
            accept_type = request.getParameter(Parameter.ACCEPT.toString());
            content_type = request.getParameter(Parameter.CONTENT.toString());
        }

        MediaType acceptMediaType = null;
        MediaType contentMediaType = null;

        String[] path_content = url.split("\\?");

        final GrabmRESTClient restClient = new GrabmRESTClient();
        WebTarget target = restClient.getTarget()
                .path(path_content[0]);

        if (path_content.length > 1) {
            String[] params = path_content[1].split("\\&");
            for (String param : params) {
                String[] parts = param.split("=");
                target = target.queryParam(parts[0], parts[1]);
            }
        }

        Invocation.Builder builder = target.request().header("Authorization", Bundle.getValue(Bundle.API_KEY));
        if (accept_type != null && content_type != null) {
            switch (accept_type.toLowerCase()) {
                case "json":
                    acceptMediaType = MediaType.APPLICATION_JSON_TYPE;
                    break;
                case "xml":
                    acceptMediaType = MediaType.APPLICATION_XML_TYPE;
                    break;
                case "plain":
                    acceptMediaType = MediaType.TEXT_PLAIN_TYPE;
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                            "Unsupported accept type provided.");
                    break;
            }
            switch (content_type.toLowerCase()) {
                case "json":
                    contentMediaType = MediaType.APPLICATION_JSON_TYPE;
                    break;
                case "xml":
                    contentMediaType = MediaType.TEXT_XML_TYPE;
                    break;
                case "plain":
                    contentMediaType = MediaType.TEXT_PLAIN_TYPE;
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                            "Unsupported content-type provided.");
            }
            try {
                switch (method) {
                    case GET:
                        return builder.accept(acceptMediaType).get(String.class);
                    case POST:
                        return builder.accept(acceptMediaType).post(Entity.entity(body, contentMediaType), String.class);
                    case PUT:
                        return builder.accept(acceptMediaType).put(Entity.entity(body, contentMediaType), String.class);
                    case DELETE:
                        return builder.accept(acceptMediaType).delete(String.class);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            } finally {
                restClient.getClient().close();
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Accept and Content Types must declare as a query parameter.");
        }
        return null;
    }
}
