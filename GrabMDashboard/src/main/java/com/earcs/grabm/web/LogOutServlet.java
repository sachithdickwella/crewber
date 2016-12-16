package com.earcs.grabm.web;

import com.earcs.grabm.util.GrabmDashboardConstant.Bundle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roshin Perera
 */
@WebServlet(name = "LogOutServlet", urlPatterns = {"/logout"})
public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 5450562713587067818L;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(Bundle.context());
    }
}
