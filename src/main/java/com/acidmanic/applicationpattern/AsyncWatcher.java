/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import java.util.Date;
import java.util.function.Consumer;

/**
 *
 * @author diego
 */
public class AsyncWatcher {

    private Thread watchThread;
    private boolean keepWatching;

    public interface Evaluator {

        boolean matched();
    }

    public void go(Evaluator evaluator, Consumer<Boolean> onFinish) {
        go(evaluator, onFinish, Long.MAX_VALUE);
    }

    public void go(Evaluator evaluator, Consumer<Boolean> onFinish, long timeout) {
        this.watchThread = new Thread(
                () -> {

                    keepWatching = true;
                    boolean matched = false;
                    boolean timedOut = false;
                    long start = new Date().getTime();

                    while (keepWatching && !timedOut && !matched) {
                        matched = evaluator.matched();

                        timedOut = (new Date().getTime() - start) > timeout;

                        try {
                            Thread.sleep(333);
                        } catch (Exception e) {
                        }
                    }
                    onFinish.accept(matched);
                }
        );
        this.watchThread.setName("Async Watcher Thread");
        this.watchThread.start();
    }

    public void stop() {
        this.keepWatching = false;

        if (this.watchThread != null) {
            try {
                this.watchThread.interrupt();
            } catch (Exception e) {
            }
        }
    }

}
