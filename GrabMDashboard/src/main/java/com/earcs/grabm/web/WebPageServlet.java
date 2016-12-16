package com.earcs.grabm.web;

import com.earcs.grabm.client.GrabmRESTClient;
import com.earcs.grabm.pojo.Administrator;
import com.earcs.grabm.pojo.WebPage;
import com.earcs.grabm.util.GrabmDashboardConstant;
import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import com.earcs.grabm.util.GrabmDashboardConstant.Attributes;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "WebPageServlet", urlPatterns = {"/webpagesweb"})
public class WebPageServlet extends HttpServlet
        implements Bundle, Attributes {

    private static final long serialVersionUID = 331004968358310844L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String get = request.getParameter("get"),
                stat = request.getParameter("stat");
        String targetUrl = "/webpage/";
        if (get != null
                && (get.equals("all") || get.equals("idnameurl"))) {
            targetUrl += get;
        }

        GrabmRESTClient rCleint = new GrabmRESTClient();
        Invocation.Builder builder = rCleint.getTarget()
                .path(targetUrl)
                .request()
                .headers(Bundle.getHeaders())
                .accept(MediaType.APPLICATION_JSON_TYPE);

        List<WebPage> webPages = builder.get(new GenericType<List<WebPage>>() {
        });
        getServletContext().setAttribute(CONTEXT_WEBPAGES, webPages);

        String redirecr_url = Bundle.context() + "ui-profile-registry";
        if (stat != null) {
            redirecr_url += "?stat=" + stat;
        }
        response.sendRedirect(redirecr_url);
        rCleint.getClient().close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String file_name = request.getParameter("ui_web_page_rg_file_name"),
                mapping_url = request.getParameter("ui_web_page_rg_mapping_url");

        Administrator admin = (Administrator) request.getSession().getAttribute(SESSION_ADMIN);

        WebPage webPage = new WebPage();
        webPage.setFileName(file_name);
        webPage.setMappingUrl(mapping_url);

        if (admin != null) {
            webPage.setCreateUser(admin.getId());
            webPage.setLastupdateUser(admin.getId());
        }

        GrabmRESTClient rClient = new GrabmRESTClient();
        Invocation.Builder builder = rClient.getTarget()
                .path("/webpage/create")
                .request(MediaType.TEXT_PLAIN)
                .headers(GrabmDashboardConstant.Bundle.getHeaders());

        String stat = builder.put(Entity.json(webPage), String.class);
        response.sendRedirect(Bundle.context() + "webpagesweb?get=idnameurl&stat=" + stat);
        rClient.getClient().close();
    }
}
