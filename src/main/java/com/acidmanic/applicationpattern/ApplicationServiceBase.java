/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.lightweight.logger.Logger;

/**
 *
 * @author diego
 */
public abstract class ApplicationServiceBase {

    private final Logger logger;

    public ApplicationServiceBase(Logger logger) {
        this.logger = logger;
        
        logServiceState("Created");
    }

    protected Logger getLogger() {
        return logger;
    }

    protected String getServicename() {
        String name = this.getClass().getSimpleName();

        if (name.toLowerCase().endsWith("service")) {
            name = name.substring(0, name.length() - "service".length());
        }
        return name;
    }

    protected final void logServiceState(String state) {
        this.logger.info(getServicename() + " service's state: " + state + ".");
    }

}
