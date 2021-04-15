/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.applicationpattern.utility.SynchronizedAccessor;
import com.acidmanic.diymotiondetector.applicationpattern.models.ApplicationStatus;
import com.acidmanic.lightweight.logger.Logger;

/**
 *
 * @author diego
 */
public abstract class AsyncOrientedApplicationServiceBase 
        extends ApplicationServiceBase
        implements ApplicationService {

    private final SynchronizedAccessor<ApplicationStatus> status;

    public AsyncOrientedApplicationServiceBase(Logger logger) {
        super(logger);
        status = new SynchronizedAccessor<>(ApplicationStatus.Stopped);
    }

    @Override
    public void start(boolean async) {

        if (status.get() == ApplicationStatus.Stopped) {
            
            logServiceState("Starting...");
            
            preStartAction();

            status.set(ApplicationStatus.Starting);

            startAsyncAction();

            if (!async) {

                waitUntilItStops();
            }
        }
    }

    protected void declareAsyncActionIsRunning() {

        status.set(ApplicationStatus.Running);
        
        logServiceState("Started");
    }

    protected void declareAsyncActionIsStopped() {

        status.set(ApplicationStatus.Stopped);
        
        logServiceState("Stopped");
    }

    @Override
    public void stop() {
        status.set(ApplicationStatus.Stopping);

        logServiceState("Stopping...");
        
        stopAsyncAction();
    }

    @Override
    public ApplicationStatus getStatus() {
        return this.status.get();
    }

    protected void preStartAction() {
    }

    protected abstract void startAsyncAction();

    protected abstract void stopAsyncAction();

    protected void snozeThread(long snozeFor) {
        try {
            Thread.sleep(snozeFor);
        } catch (Exception e) {
        }
    }

    protected void waitUntilItStops() {
        while (this.status.get() != ApplicationStatus.Stopped) {

            snozeThread(250);
        }
    }

}
