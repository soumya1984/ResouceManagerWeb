package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl;
import edu.sjsu.courseapp.domain.Instance;

public class BackendStorage {
	private static ApplicationContext context;
	public static HashMap<String, Resources> resourceDetails = new HashMap<String, Resources>();
	static {
		context = new ClassPathXmlApplicationContext("root-context.xml");
		InstanceDaoJdbcImpl instances = (InstanceDaoJdbcImpl) context
				.getBean("instanceServ");
		Resources resource = new Resources();
		// set instances details
		// resource.setCpu_units(instances.get)
		List<Instance> insList = instances.getInstanceallList();
		for (int i = 0; i < insList.size(); i++) {
			String resourceName = "EC2_" + i;
			resource.setResourceName(resourceName);
			resource.setCpu_units(Integer.parseInt(instances
					.getInstanceallList().get(i).getCpu()));
			resource.setCpu_units(Integer.parseInt(instances
					.getInstanceallList().get(i).getCpu()));
			resource.setMemory(Integer.parseInt(instances.getInstanceallList()
					.get(i).getMemory()));
			resource.setStorage(Integer.parseInt(instances.getInstanceallList()
					.get(i).getStorage()));
			resource.setOs((instances.getInstanceallList().get(i).getOs()));
			resource.setType((instances.getInstanceallList().get(i).getType()));
			resource.setFullAllocation(false);
			resource.setPartialAllocation(false);
			addResourceToHashMap(resourceName, resource);
		}
	}

	public static void addResourceToHashMap(String resourceName,
			Resources resource) {
		resourceDetails.put(resourceName, resource);
	}

	public HashMap<String, Resources> getResourceFromHashMap() {
		return resourceDetails;
	}

	public ArrayList<Resources> getResourcesFromHashMap() {
		ArrayList<Resources> resourceList = new ArrayList<Resources>();

		for (String resourceName : resourceDetails.keySet()) {
			resourceList.add(resourceDetails.get(resourceName));
		}

		return resourceList;
	}

	public ArrayList<String> updateResourcesInHashMap(
			ArrayList<Resources> resourceLists) {
		ArrayList<String> allocatedResources = new ArrayList<String>();

		for (int i = 0; i < resourceLists.size(); i++) {
			if (resourceDetails.containsKey(resourceLists.get(i)
					.getResourceName())) {
				if ((resourceLists.get(i).isFullAllocation() == true)
						|| (resourceLists.get(i).isPartialAllocation() == true)) {
					// resourceDetails.replace(resourceLists.get(i).getResourceName(),
					// resourceLists.get(i));
					allocatedResources.add(resourceLists.get(i)
							.getResourceName());
				}
			}
		}

		return allocatedResources;
	}

	public ArrayList<Resources> getAllocatedResourcesFromHashMap() {
		ArrayList<Resources> allocatedResources = new ArrayList<Resources>();
		Resources resources = new Resources();

		Iterator<Map.Entry<String, Resources>> iterator = resourceDetails
				.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Resources> resourceEntry = iterator.next();
			resources = resourceEntry.getValue();
			if ((resources.isFullAllocation() == true)
					|| (resources.isPartialAllocation() == true)) {
				allocatedResources.add(resources);
			}
		}
		return allocatedResources;
	}

}
