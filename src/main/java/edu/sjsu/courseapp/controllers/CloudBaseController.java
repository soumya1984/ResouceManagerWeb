package edu.sjsu.courseapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.courseapp.jms.SimpleMessageProducer;

import edu.sjsu.courseapp.services.ProductService;

@Controller
public class CloudBaseController {

	@Autowired
	ProductService productservice;
	private static ApplicationContext context ;
	
	static{
		context= new ClassPathXmlApplicationContext("producer-jms-context.xml");
	}
	/**
	 * Load the index page jsp 
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
	 * @return
	 */
	@RequestMapping(value = "/requestGenerator", method = RequestMethod.POST)
	public ModelAndView newRequestGenerator() {
		ModelAndView modelView=null;
		try {
		      SimpleMessageProducer messageProducer = (SimpleMessageProducer) context.getBean("messageProducer");
		      messageProducer.sendMessages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}
}
