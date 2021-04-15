/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.applicationpattern.utility.SynchronizedAccessor;
import com.acidmanic.lightweight.logger.Logger;
import java.util.Date;

/**
 *
 * @author diego
 */
public abstract class SyncLoopApplicationServiceBase extends SyncOrientedApplicationServiceBase {

    private final SynchronizedAccessor<Boolean> keepRunning;
    private final long interval;

    public SyncLoopApplicationServiceBase(Logger logger, long interval) {
        super(logger);
        this.keepRunning = new SynchronizedAccessor<>(false);
        this.interval = interval;
    }

    @Override
    protected void performSyncAction() {

        long start, elpassed, remaining;

        this.keepRunning.set(true);

        while (this.keepRunning.get()) {

            start = new Date().getTime();

            loopJob();

            elpassed = new Date().getTime() - start;

            remaining = interval - elpassed;

            if (remaining > 0) {
                try {
                    Thread.sleep(remaining);
                } catch (Exception e) {
                }
            }
        }
    }

    protected abstract void loopJob();

    @Override
    protected void stopSyncAction() {

        this.keepRunning.set(false);
    }

}
