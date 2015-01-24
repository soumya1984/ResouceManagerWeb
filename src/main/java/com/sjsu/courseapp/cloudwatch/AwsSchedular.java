package com.sjsu.courseapp.cloudwatch;

import java.util.Calendar;

public class AwsSchedular {

	public void processSchedular() {
		AwsMainProcessor.syncAwsT0Db();
		System.out  
	    .println("Sync AWS details to DB : " + Calendar.getInstance().getTime());  
	}

}