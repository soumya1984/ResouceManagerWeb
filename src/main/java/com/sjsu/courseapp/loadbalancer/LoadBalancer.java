package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;



public class LoadBalancer {
	// Resource Allocation is done by Ant Colony Algorithm
	public String processRequest(ResourceRequest request) {
		RequestResourceStorage requestResourceStorage = new RequestResourceStorage();
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

					if (resourceList.get(j).isFullAllocation() == false) {
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
									break;
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
												request
														.setAllocated(true);
											}

										}
										if ((Math.abs(Resource_Storage) - Math
												.abs(Request_Storage)) < 0) {
											continue;
										}

									}
								}
							}
						}

						if ((Math.abs(Resource_CPU_units) - Math
								.abs(Request_CPU_units)) < 0) {
							continue;
						}

					} else {
						System.out
								.println("Insufficient Resource for allocation");
					}
				}
				if (request.isAllocated() == true) {
					System.out
							.println("******************************************************************");
					System.out.println("Request ID: "
							+ request.getRequestId()
							+ " is Allocated");
					System.out
							.println("******************************************************************");
				} else {
					requestServed = requestServed + 1;
				}
			}
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