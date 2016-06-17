package com.earcs.grabm.util.executor;

/**
 * @author Sachith Dickwella
 */
public interface Interceptable {

    /**
     * Should implement on sub-class before main task execute.
     */
    void doBefore();

    /**
     * Should implement on sub-class after main task done.
     */
    void doAfter();
}
