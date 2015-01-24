package com.sjsu.courseapp.cloudwatch;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

import edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl;

public class AwsMainProcessor {

	private static final String Date = null;
	private static AmazonEC2 ec2 = null;
	private static AmazonCloudWatchClient cwc = null;
	private static ApplicationContext context = null;
	static {
		context = new ClassPathXmlApplicationContext("root-context.xml");
	}

	@SuppressWarnings("deprecation")
	public static void syncAwsT0Db() {
		try {
			AmazonEC2 ec2 = getEC2();
			//ec2.setEndpoint("ec2.us-west-2.amazonaws.com");
			ec2.setRegion(Region.getRegion(Regions.US_WEST_2));
			System.out.println("::instance status" + ec2.describeInstances());
			java.util.List<Reservation> reservation = ec2.describeInstances()
					.getReservations();
			for (Reservation reserv : reservation) {
				List<edu.sjsu.courseapp.domain.Instance> instanceList = new ArrayList<edu.sjsu.courseapp.domain.Instance>();
				java.util.List<Instance> instances = reserv.getInstances();
				for (Instance instance : instances) {
					InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context
							.getBean("instanceServ");
					edu.sjsu.courseapp.domain.Instance currentinstance = cs
							.findInstanceByName(instance.getInstanceId());
					if (currentinstance != null) {
						currentinstance.setName(instance.getInstanceId());
						System.out.println("instance.getInstanceType():"
								+ instance.getInstanceType());
						//currentinstance.setType(instance.getInstanceType());
						currentinstance.setType("Computer");
						
						Date startDate = instance.getLaunchTime();
						Date stopDate = Calendar.getInstance().getTime();

						long diff = stopDate.getTime() - startDate.getTime();
						int diffSeconds = (int)(diff / 1000 % 60);  
						int diffMinutes = (int)(diff / (60 * 1000) % 60);       
						int diffHours = (int)(diff / (60 * 60 * 1000));           
						System.out.println("startDate: " + startDate);  
						System.out.println("stopDate: " + stopDate);  
						System.out.println("Difference of Time in seconds: " + diffSeconds + " seconds.");         
						System.out.println("Difference of Time in minutes: " + diffMinutes + " minutes.");         
						System.out.println("Difference of Time in hours: " + diffHours + " hours."); 
						Time diffTime = new Time(0);
						diffTime.setHours(diffHours);
						diffTime.setMinutes(diffMinutes);
						diffTime.setSeconds(diffSeconds);
						currentinstance.setUptime(diffTime);
						System.out.println("UPTIME: " + diffTime );
						currentinstance.setPrivateip(instance
								.getPrivateIpAddress());
						currentinstance.setPublicip(instance
								.getPublicIpAddress());
						currentinstance.setStatus(instance.getState().getName());
						if (null == instance.getPlatform()) {
							currentinstance.setOs("linux");
						} else {
							currentinstance.setOs(instance.getPlatform());
						}
						currentinstance.setCpu(1);
						currentinstance.setMemory(1024);
						currentinstance.setStorage(8);
						
						cs.updateInstanceDetails(currentinstance);

					} else {
						edu.sjsu.courseapp.domain.Instance dbInstance = new edu.sjsu.courseapp.domain.Instance();
						dbInstance.setName(instance.getInstanceId());
						System.out.println("instance.getInstanceType():"
								+ instance.getInstanceType());
						//dbInstance.setType(instance.getInstanceType());
						dbInstance.setType("Computer");
						Date date = instance.getLaunchTime();
						dbInstance.setUptime(new Time(date.getHours(), date
								.getMinutes(), date.getSeconds()));
						dbInstance.setPrivateip(instance.getPrivateIpAddress());
						dbInstance.setPublicip(instance.getPublicIpAddress());
						dbInstance.setCloudid(1);
						dbInstance.setStatus(instance.getState().getName());
						if (null == instance.getPlatform()) {
							dbInstance.setOs("linux");
						} else {
							dbInstance.setOs(instance.getPlatform());
						}
						dbInstance.setCpu(1);
						dbInstance.setMemory(1024);
						dbInstance.setStorage(8);
						instanceList.add(dbInstance);
						cs.insertInstance(instanceList);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Error!!!!!");
		}

	}

	private static AmazonEC2 getEC2() throws IOException {
		if (ec2 == null) {
			AWSCredentials credentials = new PropertiesCredentials(
					AwsMainProcessor.class
							.getResourceAsStream("AwsCredentials.properties"));
			ec2 = new AmazonEC2Client(credentials);
		}

		return ec2;
	}

	private static AmazonCloudWatchClient getCWC() throws IOException {
		if (cwc == null) {
			AWSCredentials credentials = new PropertiesCredentials(
					AwsMainProcessor.class
							.getResourceAsStream("AwsCredentials.properties"));
			cwc = new AmazonCloudWatchClient(credentials);
		}

		return cwc;
	}

	public static void main(String args[]) {
		AwsMainProcessor.syncAwsT0Db();
	}

}