/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.diymotiondetector.applicationpattern.models;

/**
 *
 * @author diego
 */
public enum ApplicationStatus {
    Stopped,// Steady , stopped
    Running,// Steady , running
    Starting,// Transient, going to start
    Stopping// Transient, going to stop
}
