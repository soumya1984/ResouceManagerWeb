package edu.sjsu.courseapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import edu.sjsu.courseapp.domain.Instance;
/**
 * 
 * @author Sudip githubid:sudipk
 * 
 */
public class InstanceRowMapper implements RowMapper<Instance>{{

}

@Override
public Instance mapRow(ResultSet rs, int rowNum) throws SQLException {

	int instanceid;
	int cloudid;
	String name;
	String status;
	String type;
	String os;
	int cpu;
	int memory;
	int storage;
	String publicip;
	String privateip;
	Time uptime;
	Double bill;
	int userid;

	cloudid = rs.getInt("cloudid");
	instanceid = rs.getInt("instanceid");
	name = rs.getString("name");
	status = rs.getString("status");
	type = rs.getString("type");
	os = rs.getString("os");
	cpu = rs.getInt("cpu");
	memory = rs.getInt("memory");
	storage = rs.getInt("storage");
	publicip = rs.getString("publicip");
	privateip = rs.getString("privateip");
	uptime = rs.getTime("uptime");
	bill = rs.getDouble("bill");
	userid = rs.getInt("userid");
	
	Instance instance = new Instance();
	
	instance.setInstanceid(instanceid);
	instance.setName(name);
	instance.setCloudid(cloudid);
	instance.setStatus(status);
	instance.setType(type);
	instance.setOs(os);
	instance.setCpu(cpu);
	instance.setMemory(memory);
	instance.setStorage(storage);
	instance.setPublicip(publicip);
	instance.setPrivateip(privateip);
	instance.setUptime(uptime);
	instance.setBill(bill);
	instance.setUserid(userid);

	return instance;

	}
}
