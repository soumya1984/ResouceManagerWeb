package edu.sjsu.courseapp.controllers;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.courseapp.jms.SimpleMessageProducer;
import com.sjsu.review.httpclient.HttpClientInterface;
import edu.sjsu.courseapp.services.ProductService;

@Controller
public class EquipmentController {

	@Autowired
	ProductService productservice;

	private static final Logger logger = LoggerFactory
			.getLogger(EquipmentController.class);

	/***
	 * product class
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView newProductDataForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("index");
		try {
			ApplicationContext context = 
		             new ClassPathXmlApplicationContext("producer-jms-context.xml");

		      SimpleMessageProducer messageProducer = (SimpleMessageProducer) context.getBean("messageProducer");
		      messageProducer.sendMessages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//modelView.addObject("product", new Product());
		return modelView;
	}

}
