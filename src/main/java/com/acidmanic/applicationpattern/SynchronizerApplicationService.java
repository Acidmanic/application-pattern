/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.lightweight.logger.SilentLogger;

/**
 *
 * @author diego
 */
public class SynchronizerApplicationService extends AsyncOrientedApplicationServiceBase {

    public SynchronizerApplicationService() {
        super(new SilentLogger());
    }

    @Override
    protected void startAsyncAction() {

        this.declareAsyncActionIsRunning();
    }

    @Override
    protected void stopAsyncAction() {

        this.declareAsyncActionIsStopped();
    }

}
