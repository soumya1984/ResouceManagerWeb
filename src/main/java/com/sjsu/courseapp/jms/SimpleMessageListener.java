package com.sjsu.courseapp.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sjsu.courseapp.loadbalancer.LoadBalancer;


public class SimpleMessageListener implements MessageListener {

	private static final Logger LOG = LoggerFactory
			.getLogger(SimpleMessageListener.class);


	public void onMessage(Message message) {
		try {
			//RequestResourceStorage resourceStorage = new RequestResourceStorage();
			String responseMessage = ((TextMessage) message).getText();
			com.sjsu.courseapp.loadbalancer.ResourceRequest request =XmlStringConvertor.procesor(responseMessage);
			System.out.println("************************************************");
			System.out.println("CPU:"+request.getCpu_units());
			System.out.println("MEMORY:"+request.getMemory());
			System.out.println("REQUESTID"+request.getRequestId());
			System.out.println("STORAGE:"+request.getStorage());
			System.out.println("GEOLOCATION:"+request.getGeolocation());
			System.out.println("OS:"+request.getOs());
			System.out.println("TYPE:"+request.getType());
			System.out.println("USERID:"+request.getUserId());
			System.out.println("TotalCount:"+request.getTotalCount());
			System.out.println("************************************************");
			//resourceStorage.addRequestsToHashMap(request.getRequestId(), request);
			LoadBalancer balancer = new LoadBalancer(); 
	        LoadBalancer.noOfRequest=request.getTotalCount();
			//ant colony 
			if(request.getAlgorithm().equals("ant"))
			balancer.antColonyRequestProcesor(request);
			else if(request.getAlgorithm().equals("geolocation"))
				balancer.processGeoLocationRequest(request);
		} catch (JMSException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
