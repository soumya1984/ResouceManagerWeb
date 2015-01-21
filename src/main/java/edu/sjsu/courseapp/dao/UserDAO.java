package edu.sjsu.courseapp.dao;

import java.util.List;

import edu.sjsu.courseapp.domain.User;

/**
 * 
 * @author Sudip githubid:sudipk
 * 
 */
public interface UserDAO {

	public int getUserCount();

	public String findUserNameById(int id);

	public User findUserByName(String userName);

	public void insertUser(List<User> users);
	
	public void deleteUser(int userid);
	
	public List<User> getUserallList() ;
	
	public int updateBillOfAllUsers();
	
	public double getTotalBillOfUser(int userid);
	
	public double getTotalBillOfAllUsers();
}
