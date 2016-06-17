package com.grabm;

import com.grabm.util.ContextExceptionMapper;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Sachith Dickwella
 */
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ContextExceptionMapper.class);;

        return resources;
    }
}
