package com.sjsu.courseapp.cloudwatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;

public class AwsCloudWatch {

	private static AmazonCloudWatchClient cwc = null;
	private static String metric = "CPUUtilization";
	private static AmazonCloudWatchClient getCWC() throws IOException {
		if (cwc == null) {
			AWSCredentials credentials = new PropertiesCredentials(
					AwsMainProcessor.class
							.getResourceAsStream("AwsCredentials.properties"));
			cwc = new AmazonCloudWatchClient(credentials);
		}

		return cwc;
	}
	
	public static java.util.List<Datapoint> getCloudWatchMonitoringData(String met, String instanceId) {
		metric = met;
		return getCloudWatchMonitoringData(instanceId);
	}

	public static java.util.List<Datapoint> getCloudWatchMonitoringData(String instanceId) {
		AmazonCloudWatchClient cws;
		try {
			cws = getCWC();

			cws.setRegion(Region.getRegion(Regions.US_WEST_2));
			System.out.println("::Service Name::" + cws.getServiceName());
			System.out.println("::getRequestMetricsCollector::"
					+ cws.getRequestMetricsCollector());
			GetMetricStatisticsRequest getMetricStatisticsRequest = new GetMetricStatisticsRequest();
			//getMetricStatisticsRequest.setMetricName("CPUUtilization");
			getMetricStatisticsRequest.setMetricName(metric);
			Calendar cal = Calendar.getInstance();
			// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			getMetricStatisticsRequest.setEndTime(cal.getTime());
			System.out.println("end time" + cal.getTime());
			System.out.println(cal.getTime());
			cal.setTime(cal.getTime());
			cal.add(Calendar.MINUTE, -60);
			getMetricStatisticsRequest.setStartTime(cal.getTime());
			System.out.println("start time" + cal.getTime());
			getMetricStatisticsRequest.setNamespace("AWS/EC2");
			getMetricStatisticsRequest.setPeriod(60);

			Dimension instanceDimension = new Dimension();
			instanceDimension.setName("InstanceId");
			instanceDimension.setValue(instanceId);
			System.out.println("instanceId = "
					+ instanceId);
			getMetricStatisticsRequest.setDimensions(Arrays
					.asList(instanceDimension));

			Collection<String> statistics = new ArrayList<String>();
			statistics.add("Average");
			statistics.add("Maximum");
			statistics.add("Minimum");

			getMetricStatisticsRequest.setStatistics(statistics);
			// getMetricStatisticsRequest.setUnit(StandardUnit.Count);

			GetMetricStatisticsResult getMetricStatisticsResult = cws
					.getMetricStatistics(getMetricStatisticsRequest);
			System.out.println("label = "
					+ getMetricStatisticsResult.getLabel());
			System.out.println("getMetricStatisticsResult = "
					+ getMetricStatisticsResult);
			
			List<Datapoint> listData = getMetricStatisticsResult
					.getDatapoints();
			Collections.sort(listData, new CompData());
			System.out.println("getMetricStatisticsResult.getDatapoints() = "
					+ listData);
			return listData;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String args[]) {

		getCloudWatchMonitoringData(args[0]);
	}

}
