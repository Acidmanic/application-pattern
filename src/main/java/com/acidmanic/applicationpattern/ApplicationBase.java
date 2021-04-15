/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.applicationpattern.utility.OnceRunner;
import com.acidmanic.diymotiondetector.applicationpattern.models.ApplicationStatus;

/**
 *
 * @author diego
 */
public abstract class ApplicationBase implements Application {

    private final SynchronizerApplicationService synchronizer;
    private final ServiceManager serviceManager;
    private final OnceRunner serviceDeclareRunner;

    public ApplicationBase(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.synchronizer = new SynchronizerApplicationService();

        serviceDeclareRunner = new OnceRunner(() -> declareServices(serviceManager));
    }

    @Override
    public void start(String[] args, boolean async) {
        
        serviceDeclareRunner.run();

        beforeStartingServices(args);

        serviceManager.startServicesAsync();

        new AsyncWatcher().go(
                () -> allServicesStatus(ApplicationStatus.Running),
                suc -> {
                    if (suc) {
                        afterServicesStarted(args);
                    }
                }, 10000);

        this.synchronizer.start(async);

    }

    protected boolean allServicesStatus(ApplicationStatus status) {

        for (ApplicationService service : this.serviceManager.getServices()) {

            if (service.getStatus() != status) {

                return false;
            }
        }
        return true;
    }

    @Override
    public void stop() {

        serviceManager.stopServices();

        this.synchronizer.stop();
    }

    @Override
    public ApplicationStatus getStatus() {
        return this.synchronizer.getStatus();
    }

    protected final <T extends ServiceManager> T getServiceManager() {
        return (T) this.serviceManager;
    }

    protected void beforeStartingServices(String[] args) {
    }

    protected void afterServicesStarted(String[] args) {
    }

    protected abstract void declareServices(ServiceManager manager);
}
