package edu.sjsu.courseapp.dao;

import java.util.List;

import edu.sjsu.courseapp.domain.Instance;
/**
 * 
 * @author Sudip githubid:sudipk
 * 
 */

public interface InstanceDAO {

	public int getInstanceCount();

	public String findInstanceNameById(int id);

	public Instance findInstanceByName(String instanceName);

	public void insertInstance(List<Instance> instance);
	
	public void deleteInstance(int instanceid);
	
	public List<Instance> getInstanceallList() ;
	
	public List<Instance> getInstances(int userid);
	
	public int updateBillOfAllInstances();
	
	public Integer findMaxInstanceId();
	
}
