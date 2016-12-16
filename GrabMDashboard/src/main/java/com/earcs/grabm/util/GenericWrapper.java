package com.earcs.grabm.util;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;

/**
 *
 * @author Roshin Perera
 */
public class GenericWrapper<T> {
    
    private List<T> items;
    
    public GenericWrapper() {
        this.items = new LinkedList<>();
    }
    
    public GenericWrapper(List<T> items) {
        this.items = items;
    }
    
    @XmlAnyElement(lax = true)
    public List<T> getItems() {
        return this.items;
    }
}
