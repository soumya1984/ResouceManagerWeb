package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;

public class LoadBalancer {
	public static int noOfRequest = 0;
	private static int processRequest = 0;
	private long endTime;
	private static long startTime = new DateTime().getMillis();
	BackendStorage backendStorage = new BackendStorage();

	// Resource Allocation is done by Ant Colony Algorithm
	public synchronized String antColonyRequestProcesor(ResourceRequest request) {
		System.out.println("Single Request");
		System.out
				.println("#####################################################################################################");
		// request parameter received from the request.
		System.out
				.println("**********************************************************");
		System.out.println("Request ID: " + request.getRequestId());
		System.out.println("Request CPU units: " + request.getCpu_units());
		System.out.println("Request Memory units: " + request.getMemory());
		System.out.println("Request OS: " + request.getOs());
		System.out.println("Request Type: " + request.getType());
		System.out.println("Request Geolocation: " + request.getGeolocation());
		System.out.println("Request Is Allocated? " + request.isAllocated());
		System.out
				.println("**********************************************************");
		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();
		while ((request.isAllocated() == false)) {
			// loop through the all request and assign the proper one
			// Iterator<Resources> it = resourceList.iterator();
			for (int i = 0; i < resourceList.size(); i++) {
				int resourceCpu = resourceList.get(i).getCpu_units();
				int resourceMemory = resourceList.get(i).getMemory();
				int resourceStorage = resourceList.get(i).getStorage();
				if (resourceList.get(i).isFullAllocation() == false) {
					if (Math.abs(resourceCpu)
							- Math.abs(request.getCpu_units()) > 0
							|| Math.abs(resourceCpu)
									- Math.abs(request.getCpu_units()) == 0) {

						if (Math.abs(resourceMemory)
								- Math.abs(request.getMemory()) > 0
								|| Math.abs(resourceMemory)
										- Math.abs(request.getMemory()) == 0) {

							if (Math.abs(resourceStorage)
									- Math.abs(request.getStorage()) > 0
									|| Math.abs(resourceStorage)
											- Math.abs(request.getStorage()) == 0) {

								resourceCpu = Math.abs(resourceCpu)
										- Math.abs(request.getCpu_units());
								resourceMemory = (Math.abs(resourceMemory))
										- Math.abs(request.getMemory());
								resourceStorage = Math.abs(resourceStorage)
										- Math.abs(request.getStorage());
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
				// break;
			}
		}
		processRequest = processRequest + 1;
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
		// int requestId = request.getRequestId();
		// int requestCpu = request.getCpu_units();
		// int requestMemory = request.getMemory();
		// int requestStorage = request.getStorage();

		ArrayList<Resources> resourceList = new ArrayList<Resources>(
				backendStorage.getResourcesFromHashMap());
		ArrayList<String> resourceNames = new ArrayList<String>();
		resourceList = backendStorage.getResourcesFromHashMap();

		for (Resources resource : resourceList) {
			if (resource.isFullAllocation() == false) {

				resourceCpu = resource.getCpu_units();
				resourceMemory = resource.getMemory();
				resourceStorage = resource.getStorage();

				if (resourceCpu > request.getCpu_units()
						&& resourceMemory > request.getMemory()
						&& resourceStorage > request.getStorage()) {
					// Update the resource List
					resource.setCpu_units(resourceCpu - request.getCpu_units());
					resource.setMemory(resourceMemory - request.getMemory());
					resource.setStorage(resourceStorage - request.getMemory());

					resource.setFullAllocation(true);

					// Set the request allocated to true
					request.setAllocated(true);
				}

			}
		}

		if (request.isAllocated() == true) {
			System.out
					.println("******************************************************************");
			System.out.println("Request ID: " + request.getRequestId()
					+ " is Allocated");
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

	// Resource Allocation is done by Geo Location Algorithm
	public String processGeoLocationRequest(ResourceRequest request) {
		long startTime = System.currentTimeMillis();
		System.out.println("startTime");
		BackendStorage resourceStorage = new BackendStorage();
		// ArrayList<Resources> resourceList = new ArrayList<Resources>(
		// resourceStorage.getResourcesFromHashMap());
		// ArrayList<String> resourceNames = new ArrayList<String>();
		// resourceList = resourceStorage.getResourcesFromHashMap();

		// ConcurrentHashMap<String, ArrayList<Resources>> geoMap =
		// resourceStorage.getGeoLocationHashMap();
		Resources allocatedResource = null;
		if (resourceStorage.getGeoLocationHashMap().containsKey(
				request.getGeolocation())) {
			// ArrayList<Resources> arrList =
			// (ArrayList<Resources>)resourceStorage.getGeoLocationHashMap().get(request.getGeolocation());
			// while (request.isAllocated() == false) {
			for (int j = 0; j < resourceStorage.getGeoLocationHashMap()
					.get(request.getGeolocation()).size(); j++) {
				int Resource_CPU_units = resourceStorage
						.getGeoLocationHashMap().get(request.getGeolocation())
						.get(j).getCpu_units();
				int Resoure_Memory = resourceStorage.getGeoLocationHashMap()
						.get(request.getGeolocation()).get(j).getMemory();
				int Resource_Storage = resourceStorage.getGeoLocationHashMap()
						.get(request.getGeolocation()).get(j).getStorage();
				String OS = resourceStorage.getGeoLocationHashMap()
						.get(request.getGeolocation()).get(j).getOs();
				String Type = resourceStorage.getGeoLocationHashMap()
						.get(request.getGeolocation()).get(j).getType();
				String Resource_GeoLocation = resourceStorage
						.getGeoLocationHashMap().get(request.getGeolocation())
						.get(j).getGeolocation();
				if (OS.equalsIgnoreCase(request.getOs())
						&& Type.equalsIgnoreCase(request.getType())) {
					if ((resourceStorage.getGeoLocationHashMap()
							.get(request.getGeolocation()).get(j)
							.isFullAllocation() == false)
							&& (request.getGeolocation()
									.equalsIgnoreCase(Resource_GeoLocation))) {
						if ((Math.abs(Resource_CPU_units) - Math.abs(request
								.getCpu_units())) >= 0) {
							if (((Math.abs(Resoure_Memory)) - Math.abs(request
									.getMemory())) >= 0) {
								if ((Math.abs(Resource_Storage) - Math
										.abs(request.getStorage())) >= 0) {
									resourceStorage.getGeoLocationHashMap()
											.get(request.getGeolocation())
											.get(j).setFullAllocation(true);
									resourceStorage.getGeoLocationHashMap()
									.get(request.getGeolocation())
									.get(j).setUserId(request.getUserId());
									request.setAllocated(true);
									allocatedResource = resourceStorage.getGeoLocationHashMap()
											.get(request.getGeolocation()).get(j);
									allocatedResource.setUserId(request.getUserId());
									resourceStorage.updateDatabase(allocatedResource);
									break;
								}
								System.out
										.println("Storage is not sufficient so the request cannot be processed inspite of having cpu & memory");
							}
						}

					} else {
						System.out
								.println("Resource Not available in the Location");
					}

				} else {
					continue;
				}

				// }
				if (request.isAllocated() == true) {
					System.out
							.println("------------------------------------------------------------");
					System.out.println("Request ID: " + request.getRequestId()
							+ " is Allocated to ");
					System.out
							.println("------------------------------------------------------------");
				}
			}

			System.out
					.println("Total Time for executing Geo Location Algorithm : "
							+ (System.currentTimeMillis() - startTime));
			/*
			 * 
			 * for (int j = 0; j < resourceList.size(); j++) { String
			 * requestName = resourceList.get(j).getResourceName(); int cpu1 =
			 * resourceList.get(j).getCpu_units(); int memory1 =
			 * resourceList.get(j).getMemory(); int storage1 =
			 * resourceList.get(j).getStorage(); System.out
			 * .println("**********************************************************"
			 * ); System.out.println("### Resources After Allocation ###");
			 * System.out.println("Resource Name: " + requestName);
			 * System.out.println("Resource CPU units: " + cpu1);
			 * System.out.println("Resource Memory units: " + memory1);
			 * System.out.println("Resource Storage units: " + storage1);
			 * System.out.println("Full Allocation Flag: " +
			 * resourceList.get(j).isFullAllocation());
			 * System.out.println("Partial Allocation Flag: " +
			 * resourceList.get(j).isPartialAllocation()); System.out
			 * .println("**********************************************************"
			 * ); }
			 */

			// resourceNames =
			// resourceStorage.updateResourcesInHashMap(resourceList);

			// return "**********Following Resources Allocated*********"
			// + resourceNames;
			if(allocatedResource!=null) {
			System.out.println(" Name of Allocated Resource is " + allocatedResource.getResourceName());
			} else {
				System.out.println("Not Allocated " );
			}
		}
		if(allocatedResource!=null) {
			return allocatedResource.getResourceName();
		} else {
			return "Not Allocated";
		}
	}
	//
	// System.out.println("Single Request");
	// System.out
	// .println("------------------------------------------------------------------------------------");
	// System.out.println("Request ID = " + request.getRequestId());
	// System.out.println("Request CPU units = " + request.getCpu_units());
	// System.out.println("Request Memory units = " + request.getMemory());
	// System.out.println("Request Storage units = " + request.getStorage());
	// System.out.println("Request OS = " + request.getOs());
	// System.out.println("Request OS Type = " + request.getType());
	// System.out
	// .println("Request Geo Location = " + request.getGeolocation());
	// System.out.println("Request Is Allocated = " + request.isAllocated());
	// System.out
	// .println("------------------------------------------------------------------------------------");

}
