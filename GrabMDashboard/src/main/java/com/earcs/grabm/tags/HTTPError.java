package com.earcs.grabm.tags;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Roshin Perera
 */
public class HTTPError extends SimpleTagSupport {
    
    private int code;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setCode(int code) {
        this.code = code;
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        if (request.getRequestURI().endsWith(".jsp")) {
            response.sendError(code);
        }
    }
}
