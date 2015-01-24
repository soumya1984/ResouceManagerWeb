package com.sjsu.courseapp.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.courseapp.dao.jdbc.CloudDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.UserDaoJdbcImpl;
import edu.sjsu.courseapp.domain.Cloud;
import edu.sjsu.courseapp.domain.Instance;

public class BackendStorage {
	private static ApplicationContext context;
	public static HashMap<String, Resources> resourceDetails = new HashMap<String, Resources>();
	public static ConcurrentHashMap<String, ArrayList<Resources>> geoLocationMap = new ConcurrentHashMap<String, ArrayList<Resources>>();

	static {
		if (context == null)
			context = new ClassPathXmlApplicationContext("root-context.xml");
		if (resourceDetails.size() == 0) {
			InstanceDaoJdbcImpl instances = (InstanceDaoJdbcImpl) context
					.getBean("instanceServ");
			CloudDaoJdbcImpl clouds = (CloudDaoJdbcImpl) context
					.getBean("cloudServ");
			
			// set instances details
			List<Instance> insList = instances.getInstanceallList();
			for (int i = 0; i < insList.size(); i++) {
				Resources resource = new Resources();
				resource.setCloudId(insList.get(i).getCloudid());
				resource.setInstanceId(insList.get(i).getInstanceid());
				resource.setResourceName(insList.get(i).getName());
				resource.setCpu_units(insList.get(i).getCpu());
				resource.setMemory(insList.get(i).getMemory());
				resource.setStorage(insList.get(i).getStorage());
				resource.setOs((insList.get(i).getOs()));
				resource.setType((insList.get(i).getType()));
				resource.setGeolocation(clouds.getGeolocationById(insList.get(i).getCloudid()));
				if( insList.get(i).getUserid() == 0) {
					resource.setFullAllocation(false);
					resource.setPartialAllocation(false);
					resource.setUserId(0);
				} else {
					resource.setFullAllocation(true);
					resource.setPartialAllocation(true);
					resource.setUserId(insList.get(i).getUserid());
				}
				addResourceToHashMap(insList.get(i).getName(), resource);
				addResourceToGeoLocationHashMap(resource.getGeolocation(),resource);
			}
		}
	}

	public static void addResourceToHashMap(String resourceName,
			Resources resource) {
		resourceDetails.put(resourceName, resource);
	}
	
	public static void addResourceToGeoLocationHashMap(String geoLocation,
			Resources resource) {
		if (!geoLocationMap.containsKey(geoLocation)) {
			ArrayList<Resources> rArrList = new ArrayList<Resources>();
			rArrList.add(resource);
			geoLocationMap.put(geoLocation, rArrList);
		} else {
			ArrayList<Resources> rArrList = geoLocationMap.get(geoLocation);
			rArrList.add(resource);
			geoLocationMap.replace(geoLocation, rArrList);
		}
	}

	public ConcurrentHashMap<String, ArrayList<Resources>> getGeoLocationHashMap() {
		return geoLocationMap;
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

	
//	public ArrayList<Resources> getResourceListGeoLocationHashMap() {
//		ArrayList<Resources> resourceList = new ArrayList<Resources>();
//
//		for (String resourceName : geoLocationMap.keySet()) {
//			resourceList.add(geoLocationMap.get(resourceName));
//		}
//
//		return resourceList;
//	}

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
	
	public int updateDatabase(Resources res) {
		if (context == null)
			context = new ClassPathXmlApplicationContext("root-context.xml");
		InstanceDaoJdbcImpl instances = (InstanceDaoJdbcImpl) context
				.getBean("instanceServ");
		return instances.updateInstance(res.getInstanceId(), res.getUserId());
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
