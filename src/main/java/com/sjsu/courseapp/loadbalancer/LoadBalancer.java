package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.DateTime;

public class LoadBalancer {
	public static int noOfRequest = 0;
	private int processRequest = 0;
	private long endTime;
	private static long startTime = new DateTime().getMillis();
	BackendStorage backendStorage = new BackendStorage();

	// Resource Allocation is done by Ant Colony Algorithm
	public synchronized String antColonyRequestProcesor(ResourceRequest request) {
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
		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();
		while ((request.isAllocated() == false)) {
			// loop through the all request and assign the proper one
			//Iterator<Resources> it = resourceList.iterator();
			for(int i=0;i<resourceList.size();i++) {
				int resourceCpu = resourceList.get(i).getCpu_units();
				int resourceMemory = resourceList.get(i).getMemory();
				int resourceStorage = resourceList.get(i).getStorage();
				if (resourceList.get(i).isFullAllocation() == false) {
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
								resourceList.get(i).setCpu_units(resourceCpu);
								resourceList.get(i).setMemory(resourceMemory);
								resourceList.get(i).setStorage(resourceStorage);

								resourceList.get(i).setFullAllocation(true);

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
						break;
					}

				}
				//break;
			}
			processRequest = processRequest + 1;
		}
		resourceNames = backendStorage.updateResourcesInHashMap(resourceList);
		// check how long it took to process all the request
		if (noOfRequest == processRequest) {
			endTime = new DateTime().getMillis();
			System.out
					.println("**********************************************************");
			System.out.println("End  Time:" + endTime);
			System.out.println("Time Took:" + (endTime - startTime));
			// Reset the values
			noOfRequest = 0;
			processRequest = 0;
			System.out
					.println("**********************************************************");
		}
		return "**********Following Resources Allocated*********"
				+ resourceNames;
	}

	public static int getNoOfRequest() {
		return noOfRequest;
	}

	public static void setNoOfRequest(int noOfRequest) {
		LoadBalancer.noOfRequest = noOfRequest;
	}

	public String psoAlgorithm(ResourceRequest request) {

		int resourceCpu, resourceMemory, resourceStorage;
		// request parameter received from the request.
		int requestId = request.getRequestId();
		int requestCpu = request.getCpu_units();
		int requestMemory = request.getMemory();
		int requestStorage = request.getStorage();
		
		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();

		for (Resources resource : resourceList) {
			if (resource.isFullAllocation() == false) {

				resourceCpu = resource.getCpu_units();
				resourceMemory = resource.getMemory();
				resourceStorage = resource.getStorage();

				if (resourceCpu > requestCpu && resourceMemory > requestMemory
						&& resourceStorage > requestStorage) {
					// Update the resource List
					resource.setCpu_units(resourceCpu - requestCpu);
					resource.setMemory(resourceMemory - requestMemory);
					resource.setStorage(resourceStorage - requestMemory);

					resource.setFullAllocation(true);

					// Set the request allocated to true
					request.setAllocated(true);
				}

			}
		}

		if (request.isAllocated() == true) {
			System.out
					.println("******************************************************************");
			System.out.println("Request ID: " + requestId + " is Allocated");
			System.out
					.println("******************************************************************");
		} else {
			if (request.isAllocated() == true) {
				System.out
						.println("******************************************************************");
				System.out.println("Request ID: " + request.getRequestId()
						+ " is failed. No Suitable Host.");
				System.out
						.println("******************************************************************");
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
}