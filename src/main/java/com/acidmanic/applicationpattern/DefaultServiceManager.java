/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author diego
 */
public class DefaultServiceManager implements ServiceManager {

    private final HashMap<Class<? extends ApplicationService>, List<ApplicationService>> servicesByType;
    private final HashMap<String, ApplicationService> servicesById;

    public DefaultServiceManager() {
        this.servicesByType = new HashMap<>();
        this.servicesById = new HashMap<>();
    }

    @Override
    public synchronized UUID declareService(ApplicationService service) {
        UUID id = UUID.randomUUID();

        this.servicesById.put(id.toString(), service);

        Class type = service.getClass();

        if (!this.servicesByType.containsKey(type)) {
            this.servicesByType.put(type, new ArrayList<>());
        }

        List<ApplicationService> typesServices = this.servicesByType.get(type);

        typesServices.add(service);

        return id;
    }

    @Override
    public void startService(UUID id, boolean async) {
        String key = id.toString();

        if (this.servicesById.containsKey(key)) {

            this.servicesById.get(key).start(async);
        }
    }

    @Override
    public void stopService(UUID id) {
        String key = id.toString();

        if (this.servicesById.containsKey(key)) {

            this.servicesById.get(key).stop();
        }
    }

    @Override
    public void startServicesAsync(Class<ApplicationService> type) {
        if (this.servicesByType.containsKey(type)) {
            this.servicesByType.get(type).forEach(s -> s.start(true));
        }
    }

    @Override
    public void stopServices(Class<ApplicationService> type) {
        if (this.servicesByType.containsKey(type)) {
            this.servicesByType.get(type).forEach(s -> s.stop());
        }
    }

    @Override
    public void startServicesAsync() {
        this.servicesById.values().forEach(s -> s.start(true));
    }

    @Override
    public void stopServices() {
        this.servicesById.values().forEach(s -> s.stop());
    }

    @Override
    public List<ApplicationService> getServices() {
        ArrayList<ApplicationService> services = new ArrayList<>();

        services.addAll(this.servicesById.values());

        return services;
    }

    @Override
    public List<ApplicationService> getServices(Class<? extends ApplicationService> type) {
        ArrayList<ApplicationService> services = new ArrayList<>();

        if (this.servicesByType.containsKey(type)) {

            services.addAll(this.servicesByType.get(type));
        }

        return services;
    }

    @Override
    public ApplicationService getService(UUID id) {
        String key = id.toString();

        if (this.servicesById.containsKey(key)) {
            return this.servicesById.get(key);
        }
        return null;
    }

    @Override
    public HashMap<UUID, ApplicationService> getDeclaredServices() {
        
        HashMap<UUID, ApplicationService> declaredServices
                = new HashMap<>();
        
        for(String key : this.servicesById.keySet()){
            
            UUID uuid = UUID.fromString(key);
            
            declaredServices.put(uuid, this.servicesById.get(key));
        }
        return declaredServices;
    }

}
