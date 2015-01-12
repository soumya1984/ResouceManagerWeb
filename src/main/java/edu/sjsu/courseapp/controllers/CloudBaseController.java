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
import com.sjsu.courseapp.pojo.ResourceRequest;

import edu.sjsu.courseapp.services.ProductService;

@Controller
public class CloudBaseController {

	@Autowired
	ProductService productservice;
	private static ApplicationContext context = null;
	// private static ApplicationContext =null;
	static SimpleMessageProducer simpleMessageProducer=null;
	Random rand = new Random();
	static {
		context = new ClassPathXmlApplicationContext("producer-jms-context.xml");
		simpleMessageProducer = (SimpleMessageProducer) context
				.getBean("messageProducer");
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
		modelView = new ModelAndView("genarator");
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

}
