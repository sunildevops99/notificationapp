/**
 * 
 */
package com.learnkarts.notificationapp.service;

import java.util.List;

import com.learnkarts.notificationapp.model.Device;

/**
 * @author BGH19927
 *
 */
public interface DeviceService {
	
    List<Device> getAll();

    Device findById(int id);

    Device findByName(String name);

    void create(Device notification);

    void update(Device notification);

    void delete(int id);

    boolean exists(Device notification);

}
