package com.grabm.tracker;

import com.grabm.factory.AbstractFacade;

/**
 *
 * @author Sachith Dickwella
 * @param <T>
 */
public class DatabaseHandler<T> extends AbstractFacade {

    public DatabaseHandler(Class<T> classType) {
        super(classType);
    }
}
