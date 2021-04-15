/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.applicationpattern;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author diego
 */
public interface ServiceManager {

    UUID declareService(ApplicationService service);

    void startServicesAsync();
    
    void startService(UUID id, boolean async);

    void startServicesAsync(Class<ApplicationService> type);
    
    void stopServices();
    
    void stopService(UUID id);

    void stopServices(Class<ApplicationService> type);

    List<ApplicationService> getServices();
    
    HashMap<UUID,ApplicationService> getDeclaredServices();
    
    ApplicationService getService(UUID id);
    
    List<ApplicationService> getServices(Class<? extends ApplicationService> type);
}
