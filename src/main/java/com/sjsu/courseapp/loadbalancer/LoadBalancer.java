package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sjsu.courseapp.jms.SimpleMessageListener;

public class LoadBalancer {
	private static BackendStorage backendStorage = null;
	private int noOfRequest = 0;
	private int processRequest = 0;

	private static final Logger LOG = LoggerFactory
			.getLogger(SimpleMessageListener.class);
	static {
		backendStorage = new BackendStorage();
	}

	// Resource Allocation is done by Ant Colony Algorithm
	public String antColonyRequestProcesor(ResourceRequest request) {
		System.out.println("Single Request");
		System.out
				.println("#####################################################################################################");
		// request parameter received from the request.
		int requestId = request.getRequestId();
		int requestCpu = request.getCpu_units();
		int requestMemory = request.getMemory();
		int requestStorage = request.getStorage();
		System.out
				.println("**********************************************************");
		System.out.println("Request ID: " + requestId);
		System.out.println("Request CPU units: " + requestCpu);
		System.out.println("Request Memory units: " + requestMemory);
		System.out.println("Request Storage units: " + requestStorage);
		System.out.println("Request Is Allocated? " + request.isAllocated());
		System.out
				.println("**********************************************************");

		// diositing initial pheromen to each of the resource .
		// consider each resource a cloud center .
		// building the final ant colony map
		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();

		while ((request.isAllocated() == false)) {
			// loop through the all request and assign the proper one
			for (Resources resource : resourceList) {
				int resourceCpu = resource.getCpu_units();
				int resourceMemory = resource.getMemory();
				int resourceStorage = resource.getStorage();
				if (resource.isFullAllocation() == false) {
					if (Math.abs(resourceCpu) - Math.abs(requestCpu) > 0
							|| Math.abs(resourceCpu) - Math.abs(requestCpu) == 0) {

						if (Math.abs(resourceMemory) - Math.abs(requestMemory) > 0
								|| Math.abs(resourceMemory)
										- Math.abs(requestMemory) == 0) {

							if (Math.abs(resourceStorage)
									- Math.abs(requestStorage) > 0
									|| Math.abs(resourceStorage)
											- Math.abs(requestStorage) == 0) {

								resourceCpu = Math.abs(resourceCpu)
										- Math.abs(requestCpu);
								resourceMemory = (Math.abs(resourceMemory))
										- Math.abs(requestMemory);
								resourceStorage = Math.abs(resourceStorage)
										- Math.abs(requestStorage);
								// Update the resource List
								resource.setCpu_units(resourceCpu);
								resource.setMemory(resourceMemory);
								resource.setStorage(resourceStorage);

								resource.setFullAllocation(true);

								// Set the request allocated to true
								request.setAllocated(true);
							}
						}
					}
					if (request.isAllocated() == true) {
						System.out
								.println("******************************************************************");
						System.out.println("Request ID: "
								+ request.getRequestId() + " is Allocated");
						System.out
								.println("******************************************************************");
					}

				}
			}

		}
		resourceNames = backendStorage.updateResourcesInHashMap(resourceList);
		processRequest = processRequest + 1;
		// check how long it took to process all the request
		if (noOfRequest == getNoOfRequest()) {
			// Reset the values
			noOfRequest = 0;
			noOfRequest = 0;
		}
		return "**********Following Resources Allocated*********"
				+ resourceNames;
	}

	public int getNoOfRequest() {
		return noOfRequest;
	}

	public void setNoOfRequest(int noOfRequest) {
		this.noOfRequest = noOfRequest;
	}
}