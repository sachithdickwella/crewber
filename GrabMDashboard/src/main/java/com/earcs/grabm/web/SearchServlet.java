package com.earcs.grabm.web;

import com.earcs.grabm.pojo.AreaGroup;
import com.earcs.grabm.util.GrabmDashboardConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet
        implements GrabmDashboardConstant.Attributes {

    private final Logger logger = Logger.getLogger(SearchServlet.class);

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
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type"),
                search_key = request.getParameter("search_key");

        try {
            if (type != null) {
                ServletContext context = getServletContext();
                switch (type) {
                    case "areagroup":
                        List<AreaGroup> areaGroups = (List<AreaGroup>) context.getAttribute(CONTEXT_AREAGROUP);
                        areaGroups = (List<AreaGroup>) areaGroups.parallelStream().filter(obj -> ((AreaGroup) obj).getName().startsWith(search_key))
                                .collect(Collectors.toList());
                        getJaxbMarshaller().marshal(areaGroups, System.out);
                        break;
                }
            }
        } catch (JAXBException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
