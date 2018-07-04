package com.learnkarts.notificationapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.learnkarts.notificationapp.model.Device;
import com.learnkarts.notificationapp.service.DeviceService;

@RestController
@RequestMapping("/devices")
public class DeviceController {
	
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);


    @Autowired
    private DeviceService deviceService;

    // =========================================== Get All Devices ==========================================

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Device>> getAll() {
    	LOGGER.info("getting all devices");
        List<Device> users = deviceService.getAll();

        if (users == null || users.isEmpty()){
        	LOGGER.info("no devices found");
            return new ResponseEntity<List<Device>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Device>>(users, HttpStatus.OK);
    }

    // =========================================== Get Device By ID =========================================

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Device> get(@PathVariable("id") int id){
        LOGGER.info("getting device with id: {}", id);
        Device device = deviceService.findById(id);

        if (device == null){
        	LOGGER.info("user with id {} not found", id);
            return new ResponseEntity<Device>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }

    // =========================================== Create New Device ========================================

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Device device, UriComponentsBuilder ucBuilder){
    	LOGGER.info("creating new device: {}", device);

        if (deviceService.exists(device)){
        	LOGGER.info("a device with name " + device.getDeviceName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        deviceService.create(device);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/devices/{id}").buildAndExpand(device.getDeviceId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // =========================================== Update Existing Device ===================================

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Device> update(@PathVariable int id, @RequestBody Device device){
        LOGGER.info("updating device: {}", device);
        Device currentDevice = deviceService.findById(id);

        if (currentDevice == null){
        	LOGGER.info("User with id {} not found", id);
            return new ResponseEntity<Device>(HttpStatus.NOT_FOUND);
        }

        currentDevice.setDeviceId(device.getDeviceId());
        currentDevice.setDeviceName(device.getDeviceName());

        deviceService.update(device);
        return new ResponseEntity<Device>(currentDevice, HttpStatus.OK);
    }

    // =========================================== Delete Device ============================================

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        LOGGER.info("deleting user with id: {}", id);
        Device device = deviceService.findById(id);

        if (device == null){
            LOGGER.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        deviceService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
