/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import com.acidmanic.diymotiondetector.applicationpattern.models.ApplicationStatus;

/**
 *
 * @author diego
 */
public interface Application {

    void start(String[] args, boolean async);

    void stop();

    ApplicationStatus getStatus();

    void dispose();

}
