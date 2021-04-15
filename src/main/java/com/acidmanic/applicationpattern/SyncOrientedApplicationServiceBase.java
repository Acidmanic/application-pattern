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
public abstract class SyncOrientedApplicationServiceBase
        extends ApplicationServiceBase
        implements ApplicationService {

    private final SynchronizedAccessor<ApplicationStatus> status;
    private Thread syncAsAsyncThread;

    public SyncOrientedApplicationServiceBase(Logger logger) {
        super(logger);
        status = new SynchronizedAccessor<>(ApplicationStatus.Stopped);

    }

    @Override
    public void start(boolean async) {

        if (status.get() == ApplicationStatus.Stopped) {

            logServiceState("Starting...");

            preStartAction();

            status.set(ApplicationStatus.Starting);

            if (async) {
                syncAsAsyncThread = new Thread(() -> {

                    runWithStatusUpdate();
                });
                syncAsAsyncThread.setName(getServicename() + " - Thread");

                syncAsAsyncThread.start();

            } else {

                runWithStatusUpdate();
            }
        }
    }

    private void runWithStatusUpdate() {
        status.set(ApplicationStatus.Running);

        logServiceState("Started");

        performSyncAction();

        status.set(ApplicationStatus.Stopped);

        logServiceState("Stopped");
    }

    @Override
    public void stop() {
        status.set(ApplicationStatus.Stopping);

        logServiceState("Stopping...");

        tryWakingThread();

        stopSyncAction();

        tryWakingThread();
    }

    @Override
    public ApplicationStatus getStatus() {
        return this.status.get();
    }

    protected void preStartAction() {
    }

    protected abstract void performSyncAction();

    protected abstract void stopSyncAction();

    private void tryWakingThread() {
        if (syncAsAsyncThread != null) {

            syncAsAsyncThread.interrupt();
        }
    }

}
