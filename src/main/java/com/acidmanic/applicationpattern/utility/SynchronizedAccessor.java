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
public class SynchronizedAccessor<T> {

    private T value;

    public SynchronizedAccessor(T value) {
        this.value = value;
    }

    public synchronized T get() {
        return value;
    }

    public synchronized void set(T value) {
        this.value = value;
    }

    public boolean isNull() {
        return this.value == null;
    }
}
