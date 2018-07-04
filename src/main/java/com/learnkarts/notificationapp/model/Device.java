/**
 * 
 */
package com.learnkarts.notificationapp.model;

import java.io.Serializable;

/**
 * @author BGH19927
 *
 */
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int deviceId;
	private String deviceName;
	
	public Device() {
		// TODO Auto-generated constructor stub
	}
	
	public Device(int deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
	}
	  
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deviceId;
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (deviceId != other.deviceId)
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Notification [deviceId=" + deviceId + ", deviceName=" + deviceName + "]";
	}
}
