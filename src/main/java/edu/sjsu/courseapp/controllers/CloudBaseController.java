package edu.sjsu.courseapp.controllers;

import java.util.Random;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.courseapp.jms.SimpleMessageProducer;

import edu.sjsu.courseapp.dao.jdbc.CloudDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.RateDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.UserDaoJdbcImpl;
import edu.sjsu.courseapp.services.ProductService;

@Controller
public class CloudBaseController {

	@Autowired
	ProductService productservice;
	private static ApplicationContext context = null;
	private static  ApplicationContext context1 ;
	// private static ApplicationContext =null;
	static SimpleMessageProducer simpleMessageProducer=null;
	Random rand = new Random();
	static {
		context = new ClassPathXmlApplicationContext("producer-jms-context.xml");
		simpleMessageProducer = (SimpleMessageProducer) context
				.getBean("messageProducer");
		context1= new ClassPathXmlApplicationContext("root-context.xml");
	}

	/**
	 * Load the index page jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView newProductDataForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("index");
		return modelView;
	}

	/**
	 * function to call the requst genarator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/generator", method = RequestMethod.POST)
	public ModelAndView newRequestGenerator() {
		ModelAndView modelView;
		modelView = new ModelAndView("forms");
		return modelView;
	}
	
	/**
	 * function to call the requst genarator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loadChart", method = RequestMethod.POST)
	public ModelAndView loadChart() {
		ModelAndView modelView;
		modelView = new ModelAndView("charts");
		return modelView;
	}

	/**
	 * function to call the requst genarator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/executeGenerator", method = RequestMethod.POST)
	public ModelAndView executeGenerator(@RequestParam("email") String email,
			@RequestParam("memory") String memory,
			@RequestParam("request") int request,
			@RequestParam("cpu") String cpu,
			@RequestParam("country") String country,
			@RequestParam("algorithm") String algorithm) {
		ModelAndView modelView;
		System.out.println("*************************************");
		System.out.println("email address::" + email);
		System.out.println("memory::" + memory);
		System.out.println("number of request::" + request);
		System.out.println("no of cpu::" + cpu);
		System.out.println("country::" + country);
		System.out.println("Algoritm" + algorithm);
		System.out.println("*************************************");

		String requestXML = "<request> <email>" + email + "</email>"
				+ "<memory>" + memory + "</memory>" + "<requestId>"
				+ rand.nextInt() + "</requestId>" + "<cpu>" + cpu + "</cpu>"
				+ "</request>";
		simpleMessageProducer.setNumberOfMessages(request);
		try {
			simpleMessageProducer.sendMessages(requestXML);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView = new ModelAndView("genarator");
		return modelView;
	}
	/**
	 * Load the clouds page jsp 
	 * @return
	 */
	@RequestMapping(value = "/clouds", method = RequestMethod.GET)
	public ModelAndView newCloudDataForm() {
		ModelAndView modelView=null;
		try {
			modelView = new ModelAndView("clouds");
			CloudDaoJdbcImpl cs = (CloudDaoJdbcImpl)context1.getBean("cloudServ");
			modelView.addObject("cloud_list", cs.getCloudallList());
			return modelView;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	
	
	/**
	 * Load the instances page jsp 
	 * @return
	 */
	@RequestMapping(value = "/instances", method = RequestMethod.GET)
	public ModelAndView newInstanceDataForm() {
		ModelAndView modelView=null;
		try {
			modelView = new ModelAndView("instances");
			InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl)context1.getBean("instanceServ");
			modelView.addObject("instance_list", cs.getInstanceallList());
			return modelView;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	
	
	/**
	 * Load the users page jsp 
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView newUserDataForm() {
		ModelAndView modelView=null;
		try {
			modelView = new ModelAndView("users");
			UserDaoJdbcImpl cs = (UserDaoJdbcImpl)context1.getBean("userServ");
			modelView.addObject("user_list", cs.getUserallList());
			return modelView;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	
	/**
	 * Load the rates page jsp 
	 * @return
	 */
	@RequestMapping(value = "/rates", method = RequestMethod.GET)
	public ModelAndView newRateDataForm() {
		ModelAndView modelView=null;
		try {
			modelView = new ModelAndView("rates");
			RateDaoJdbcImpl cs = (RateDaoJdbcImpl)context1.getBean("rateServ");
			modelView.addObject("rate_list", cs.getRateallList());
			return modelView;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	
	/**
	 * Load the home page jsp 
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView newHomeDataForm() {
		ModelAndView modelView=null;
		try {
			modelView = new ModelAndView("home");
			return modelView;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
	

}	
	

