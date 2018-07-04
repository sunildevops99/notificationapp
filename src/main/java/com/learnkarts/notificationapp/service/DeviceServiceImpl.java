package com.learnkarts.notificationapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.learnkarts.notificationapp.model.Device;

@Service
public class DeviceServiceImpl implements DeviceService {

	private static final AtomicInteger counter = new AtomicInteger();

	private static List<Device> devices = new ArrayList<Device>(
			Arrays.asList(
					new Device(counter.incrementAndGet(), "ac:00:00:01"),
					new Device(counter.incrementAndGet(), "ac:00:00:02"),
					new Device(counter.incrementAndGet(), "ac:00:00:03"),
					new Device(counter.incrementAndGet(), "ac:00:00:04")));
	
	public List<Device> getAll() {
		return devices;
	}

	public Device findById(int id) {
		for (Device device : devices){
			if (device.getDeviceId() == id){
				return device;
			}
		}
		return null;
	}

	public Device findByName(String name) {
		for (Device device : devices){
			if (device.getDeviceName().equals(name)){
				return device;
			}
		}
		return null;
	}

	public void create(Device device) {
		device.setDeviceId(counter.incrementAndGet());
		devices.add(device);

	}

	public void update(Device device) {
		int index = devices.indexOf(findById(device.getDeviceId()));
		devices.set(index, device);

	}

	public void delete(int id) {
		Device device = findById(id);
		devices.remove(device);		
	}

	public boolean exists(Device device) {
		return findByName(device.getDeviceName()) != null;
	}

}
