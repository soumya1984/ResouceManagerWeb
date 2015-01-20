package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;

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
	
	// Resource Allocation is done by Geo Location Algorithm
		public String processGeoLocationRequest(ResourceRequest request) {
			//RequestResourceStorage requestResourceStorage = new RequestResourceStorage();
			long startTime = System.currentTimeMillis();
			System.out
			.println("startTime");
			BackendStorage  resourceStorage = new BackendStorage();
			ArrayList<Resources> resourceList = new ArrayList<Resources>(
					resourceStorage.getResourcesFromHashMap());
			ArrayList<String> resourceNames = new ArrayList<String>();

			resourceList = resourceStorage.getResourcesFromHashMap();
			//requestList = requestResourceStorage.getResourcesFromHashMap();

		/*	for (int j = 0; j < resourceList.size(); j++) {
				String requestName = resourceList.get(j).getResourceName();
				int cpu = resourceList.get(j).getCpu_units();
				int memory = resourceList.get(j).getMemory();
				int storage = resourceList.get(j).getStorage();
				/////////////////////////////////////////////////////////////////////////////////
				System.out
						.println("**********************************************************");
				System.out.println("### Resources Before Allocation ###");
				System.out.println("Resource Name: " + requestName);
				System.out.println("Resource CPU units: " + cpu);
				System.out.println("Resource Memory units: " + memory);
				System.out.println("Resource Storage units: " + storage);
				System.out.println("Full Allocation Flag: "
						+ resourceList.get(j).isFullAllocation());
				System.out.println("Partial Allocation Flag: "
						+ resourceList.get(j).isPartialAllocation());
				System.out
						.println("**********************************************************");
			}*/
			/////////////////////////////////////////////////////////////////////////////////////
			System.out
			.println("Single Request");
			System.out
					.println("#####################################################################################################");
				int requestID = request.getRequestId();
				int cpu = request.getCpu_units();
				int memory = request.getMemory();
				int storage = request.getStorage();
				System.out
						.println("**********************************************************");
				System.out.println("Request ID: " + requestID);
				System.out.println("Request CPU units: " + cpu);
				System.out.println("Request Memory units: " + memory);
				System.out.println("Request Storage units: " + storage);
				System.out.println("Request Is Allocated? "
						+ request.isAllocated());
				System.out
						.println("**********************************************************");


		//	int count = 0;
			//for (int i = 0; i < count; i++) {
				int Request_CPU_units = request.getCpu_units();
				int Request_Memory = request.getMemory();
				int Request_Storage = request.getStorage();
				int requestServed = 0;

				while ((request.isAllocated() == false)
						&& requestServed == 0) {
					for (int j = 0; j < resourceList.size(); j++) {
						int Resource_CPU_units = resourceList.get(j).getCpu_units();
						int Resoure_Memory = resourceList.get(j).getMemory();
						int Resource_Storage = resourceList.get(j).getStorage();
						int Resource_Location = resourceList.get(j).getLocationId();

						if ((resourceList.get(j).isFullAllocation() == false)
								&& (request.getLocationId() == Resource_Location)) {
							if ((Math.abs(Resource_CPU_units) - Math
									.abs(Request_CPU_units)) > 0) {
								if (((Math.abs(Resoure_Memory)) - Math
										.abs(Request_Memory)) > 0) {

									if ((Math.abs(Resource_Storage) - Math
											.abs(Request_Storage)) > 0) {
										Resource_CPU_units = Math
												.abs(Resource_CPU_units)
												- Math.abs(Request_CPU_units);
										Resoure_Memory = (Math.abs(Resoure_Memory))
												- Math.abs(Request_Memory);
										Resource_Storage = Math
												.abs(Resource_Storage)
												- Math.abs(Request_Storage);

										// Update the resource List
										resourceList.get(j).setCpu_units(
												Resource_CPU_units);
										resourceList.get(j).setMemory(
												Resoure_Memory);
										resourceList.get(j).setStorage(
												Resource_Storage);

										resourceList.get(j)
												.setFullAllocation(false);
										resourceList.get(j).setPartialAllocation(
												true);

										// Set the request allocated to true
										request.setAllocated(true);

									} else {
										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) == 0) {
											Resource_CPU_units = Math
													.abs(Resource_CPU_units)
													- Math.abs(Request_CPU_units);
											Resoure_Memory = (Math
													.abs(Resoure_Memory))
													- Math.abs(Request_Memory);
											Resource_Storage = Math
													.abs(Resource_Storage)
													- Math.abs(Request_Storage);

											// Update the resource List
											resourceList.get(j).setCpu_units(
													Resource_CPU_units);
											resourceList.get(j).setMemory(
													Resoure_Memory);
											resourceList.get(j).setStorage(
													Resource_Storage);

											resourceList.get(j).setFullAllocation(
													true);
											resourceList.get(j)
													.setPartialAllocation(false);

											// Set the request allocated to true
											request.setAllocated(true);
										}

									}
									if ((Math.abs(Resource_Storage) - Math
											.abs(Request_Storage)) < 0) {
										System.out
												.println("Due to Insufficient Storage, Request cannot be processed inspite of having memory & Cpu");
										continue;
									}

								} else {
									if (((Math.abs(Resoure_Memory)) - Math
											.abs(Request_Memory)) == 0) {

										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) > 0) {
											Resource_CPU_units = Math
													.abs(Resource_CPU_units)
													- Math.abs(Request_CPU_units);
											Resoure_Memory = (Math
													.abs(Resoure_Memory))
													- Math.abs(Request_Memory);
											Resource_Storage = Math
													.abs(Resource_Storage)
													- Math.abs(Request_Storage);

											// Update the resource List
											resourceList.get(j).setCpu_units(
													Resource_CPU_units);
											resourceList.get(j).setMemory(
													Resoure_Memory);
											resourceList.get(j).setStorage(
													Resource_Storage);

											resourceList.get(j).setFullAllocation(
													true);
											resourceList.get(j)
													.setPartialAllocation(false);

											// Set the request allocated to true
											request.setAllocated(true);

										} else {
											if ((Math.abs(Resource_Storage) - Math
													.abs(Request_Storage)) == 0) {
												Resource_CPU_units = Math
														.abs(Resource_CPU_units)
														- Math.abs(Request_CPU_units);
												Resoure_Memory = (Math
														.abs(Resoure_Memory))
														- Math.abs(Request_Memory);
												Resource_Storage = Math
														.abs(Resource_Storage)
														- Math.abs(Request_Storage);

												// Update the resource List
												resourceList.get(j).setCpu_units(
														Resource_CPU_units);
												resourceList.get(j).setMemory(
														Resoure_Memory);
												resourceList.get(j).setStorage(
														Resource_Storage);

												resourceList.get(j)
														.setFullAllocation(true);
												resourceList
														.get(j)
														.setPartialAllocation(false);

												// Set the request allocated to true
												request.setAllocated(
														true);
											}

										}
										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) < 0) {
											System.out
													.println("Due to Insufficient Storage, Request cannot be processed inspite of having memory & Cpu");
											continue;
										}
										if (((Math.abs(Resoure_Memory)) - Math
												.abs(Request_Memory)) < 0) {
											System.out
													.println("Due to Insufficient Memory, Request cannot be processed inspite of having Cpu");
											continue;

										}

									}

								}
							} else {
								if ((Math.abs(Resource_CPU_units) - Math
										.abs(Request_CPU_units)) == 0) {
									if (((Math.abs(Resoure_Memory)) - Math
											.abs(Request_Memory)) > 0) {

										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) > 0) {
											Resource_CPU_units = Math
													.abs(Resource_CPU_units)
													- Math.abs(Request_CPU_units);
											Resoure_Memory = (Math
													.abs(Resoure_Memory))
													- Math.abs(Request_Memory);
											Resource_Storage = Math
													.abs(Resource_Storage)
													- Math.abs(Request_Storage);

											// Update the resource List
											resourceList.get(j).setCpu_units(
													Resource_CPU_units);
											resourceList.get(j).setMemory(
													Resoure_Memory);
											resourceList.get(j).setStorage(
													Resource_Storage);

											resourceList.get(j).setFullAllocation(
													true);
											resourceList.get(j)
													.setPartialAllocation(false);

											// Set the request allocated to true
											request.setAllocated(true);

										} else {
											if ((Math.abs(Resource_Storage) - Math
													.abs(Request_Storage)) == 0) {
												Resource_CPU_units = Math
														.abs(Resource_CPU_units)
														- Math.abs(Request_CPU_units);
												Resoure_Memory = (Math
														.abs(Resoure_Memory))
														- Math.abs(Request_Memory);
												Resource_Storage = Math
														.abs(Resource_Storage)
														- Math.abs(Request_Storage);

												// Update the resource List
												resourceList.get(j).setCpu_units(
														Resource_CPU_units);
												resourceList.get(j).setMemory(
														Resoure_Memory);
												resourceList.get(j).setStorage(
														Resource_Storage);

												resourceList.get(j)
														.setFullAllocation(true);
												resourceList
														.get(j)
														.setPartialAllocation(false);

												// Set the request allocated to true
												request.setAllocated(
														true);
											}

										}
										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) < 0) {
											System.out
													.println("Due to Insufficient Storage, Request cannot be processed inspite of having memory & Cpu");
											continue;
										}

									} else {
										if (((Math.abs(Resoure_Memory)) - Math
												.abs(Request_Memory)) == 0) {

											if ((Math.abs(Resource_Storage) - Math
													.abs(Request_Storage)) > 0) {
												Resource_CPU_units = Math
														.abs(Resource_CPU_units)
														- Math.abs(Request_CPU_units);
												Resoure_Memory = (Math
														.abs(Resoure_Memory))
														- Math.abs(Request_Memory);
												Resource_Storage = Math
														.abs(Resource_Storage)
														- Math.abs(Request_Storage);

												// Update the resource List
												resourceList.get(j).setCpu_units(
														Resource_CPU_units);
												resourceList.get(j).setMemory(
														Resoure_Memory);
												resourceList.get(j).setStorage(
														Resource_Storage);

												resourceList.get(j)
														.setFullAllocation(true);
												resourceList
														.get(j)
														.setPartialAllocation(false);

												// Set the request allocated to true
												request.setAllocated(
														true);

											} else {
												if ((Math.abs(Resource_Storage) - Math
														.abs(Request_Storage)) == 0) {
													Resource_CPU_units = Math
															.abs(Resource_CPU_units)
															- Math.abs(Request_CPU_units);
													Resoure_Memory = (Math
															.abs(Resoure_Memory))
															- Math.abs(Request_Memory);
													Resource_Storage = Math
															.abs(Resource_Storage)
															- Math.abs(Request_Storage);

													// Update the resource List
													resourceList
															.get(j)
															.setCpu_units(
																	Resource_CPU_units);
													resourceList.get(j).setMemory(
															Resoure_Memory);
													resourceList.get(j).setStorage(
															Resource_Storage);

													resourceList
															.get(j)
															.setFullAllocation(true);
													resourceList.get(j)
															.setPartialAllocation(
																	false);

													// Set the request allocated to
													// true
													request.setAllocated(true);
												}

											}
											if ((Math.abs(Resource_Storage) - Math
													.abs(Request_Storage)) < 0) {
												System.out
														.println("Due to Insufficient Storage, Request cannot be processed inspite of having memory & Cpu");
												continue;
											}

										}
										if (((Math.abs(Resoure_Memory)) - Math
												.abs(Request_Memory)) < 0) {
											System.out
													.println("Due to Insufficient Memory, Request cannot be processed inspite of having Cpu");
											continue;

										}
									}
								}
							}

							if ((Math.abs(Resource_CPU_units) - Math
									.abs(Request_CPU_units)) < 0) {
								System.out
										.println("Due to Insufficient CPU, Request cannot be processed.");
								continue;
							}

						} else {
							System.out
									.println("Resource Not available in the Location");
						}
					}
					if (request.isAllocated() == true) {
						System.out
								.println("------------------------------------------------------------");
						System.out.println("Request ID: "
								+ request.getRequestId()
								+ " is Allocated");
						System.out
								.println("------------------------------------------------------------");
					} else {
						System.out
								.println("****************************************************************");
						System.out.println("Request ID: "
								+ request.getRequestId()
								+ " is not Allocated");
						System.out
								.println("****************************************************************");
						requestServed = requestServed + 1;
					}
				}
				
				System.out
				.println("Total Time for executing Geo Location Algorithm : " + (System.currentTimeMillis() - startTime));
		/*

			for (int j = 0; j < resourceList.size(); j++) {
				String requestName = resourceList.get(j).getResourceName();
				int cpu1 = resourceList.get(j).getCpu_units();
				int memory1 = resourceList.get(j).getMemory();
				int storage1 = resourceList.get(j).getStorage();
				System.out
						.println("**********************************************************");
				System.out.println("### Resources After Allocation ###");
				System.out.println("Resource Name: " + requestName);
				System.out.println("Resource CPU units: " + cpu1);
				System.out.println("Resource Memory units: " + memory1);
				System.out.println("Resource Storage units: " + storage1);
				System.out.println("Full Allocation Flag: "
						+ resourceList.get(j).isFullAllocation());
				System.out.println("Partial Allocation Flag: "
						+ resourceList.get(j).isPartialAllocation());
				System.out
						.println("**********************************************************");
			}*/

			resourceNames = resourceStorage.updateResourcesInHashMap(resourceList);

			return "**********Following Resources Allocated*********"
					+ resourceNames;

		}
}
