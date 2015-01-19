package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;

public class LoadBalancer {
	// Resource Allocation is done by Ant Colony Algorithm
	public String antColonyRequestProcesor(ResourceRequest request) {
		BackendStorage backendStorage = new BackendStorage();
		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();
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
		int requestServed = 0;

		// diositing initial pheromen to each of the resource .
		// consider each resource a cloud center .
		// building the final ant colony map

		while ((request.isAllocated() == false) && requestServed == 0) {
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
					} else {
						requestServed = requestServed + 1;
					}

				}
			}

		}
		resourceNames = backendStorage.updateResourcesInHashMap(resourceList);
		return "**********Following Resources Allocated*********"
				+ resourceNames;
	}
}