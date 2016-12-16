package com.earcs.grabm.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Roshin Perera
 */
public class ExecuteStatParam extends TagSupport {
    
    private static final String PARAMETER_STAT = "stat";
    private static final long serialVersionUID = -8954138484916658932L;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        String stat = request.getParameter(PARAMETER_STAT);
        if (stat != null) {
            request.setAttribute(PARAMETER_STAT, stat);
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
