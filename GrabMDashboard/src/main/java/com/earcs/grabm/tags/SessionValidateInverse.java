package com.earcs.grabm.tags;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Roshin Perera
 */
public class SessionValidateInverse extends SimpleTagSupport {
    
    private Object sessionAttribute;
    private String url;

    public void setSessionAttribute(Object sessionAttribute) {
        this.sessionAttribute = sessionAttribute;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void doTag() throws JspException, IOException {
        HttpServletResponse response = (HttpServletResponse) ((PageContext) getJspContext()).getResponse();
        if (sessionAttribute != null) {
             response.sendRedirect(url);
        }
    }
}
