package com.sjsu.courseapp.loadbalancer;

public class Resources {

	private int cpu_units;
	private int memory;
	private int storage;
	private int cloudId;
	private int instanceId;
	private String geolocation;
	private String resourceName;
	private boolean fullAllocation;
	private boolean partialAllocation;
	private String os;
	private String type;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int userId;

	public int getCloudId() {
		return cloudId;
	}

	public void setCloudId(int cloudId) {
		this.cloudId = cloudId;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public String getOs() {
		return os;
	}

	public String getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public int getCpu_units() {
		return cpu_units;
	}

	public void setCpu_units(int cpu_units) {
		this.cpu_units = cpu_units;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public boolean isFullAllocation() {
		return fullAllocation;
	}

	public void setFullAllocation(boolean fullAllocation) {
		this.fullAllocation = fullAllocation;
	}

	public boolean isPartialAllocation() {
		return partialAllocation;
	}

	public void setPartialAllocation(boolean partialAllocation) {
		this.partialAllocation = partialAllocation;
	}
}
