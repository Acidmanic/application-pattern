/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern.utility;

/**
 *
 * @author diego
 */
public class OnceRunner {

    private boolean isRunned;
    private final Runnable toRun;

    public OnceRunner(Runnable toRun) {

        this.toRun = toRun;
        this.isRunned = false;
    }

    public synchronized void run() {
        if (!isRunned) {
            isRunned = true;
            toRun.run();
        }
    }

}
