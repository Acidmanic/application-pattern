/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.diymotiondetector.applicationpattern.models;

import java.util.UUID;

/**
 *
 * @author diego
 */
public class ApplicationServiceInfo {

    private ApplicationStatus status;
    private UUID id;
    private Class serviceType;
    private String name;
    private long upTimeMinutes;

    public ApplicationServiceInfo() {
    }

    public ApplicationServiceInfo(ApplicationStatus status, UUID id, Class serviceType, String name, long upTimeMinutes) {
        this.status = status;
        this.id = id;
        this.serviceType = serviceType;
        this.name = name;
        this.upTimeMinutes = upTimeMinutes;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Class getServiceType() {
        return serviceType;
    }

    public void setServiceType(Class serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpTimeMinutes() {
        return upTimeMinutes;
    }

    public void setUpTimeMinutes(long upTimeMinutes) {
        this.upTimeMinutes = upTimeMinutes;
    }

}
