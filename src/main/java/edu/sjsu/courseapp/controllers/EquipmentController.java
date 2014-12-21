package edu.sjsu.courseapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.npu.bankProcess.domain.Customer;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.review.httpclient.BestBuyHttpClient;
import com.sjsu.review.httpclient.HttpClientInterface;


import edu.sjsu.courseapp.domain.Product;
import edu.sjsu.courseapp.domain.ProductEbay;
import edu.sjsu.courseapp.services.ProductService;

@Controller
public class EquipmentController {

	@Autowired
	ProductService productservice;
	private HttpClientInterface callBestBuyApi;

	private static final Logger logger = LoggerFactory
			.getLogger(EquipmentController.class);

	/***
	 * product class
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showWhereToBuy", method = RequestMethod.GET)
	public ModelAndView newProductDataForm() {
		ModelAndView modelView;

		modelView = new ModelAndView("showWheretoBuy");
		modelView.addObject("product", new Product());
		return modelView;
	}

	@RequestMapping(value = "/search/name", method = RequestMethod.POST)
	public ModelAndView searchProductbyName(@Valid Product product,
			BindingResult result, HttpSession session) throws Exception {
		List<Product> productbb = new ArrayList<Product>();
		List<ProductEbay> productEbay = new ArrayList<ProductEbay>();
		//ModelAndView modelView = null;
		System.out.println("reached");
		if (null != product.getName())
		callBestBuyApi = new BestBuyHttpClient();
		callBestBuyApi.setProductservice(productservice);
		productbb=callBestBuyApi.getBestBuyProductInformations(product.getName());


		return null;
		// List products=productservice.getProductByName(product.getName());
		// modelView = new ModelAndView("resultShowWheretoBuy");

		// modelView.addObject("product", products);
		// return modelView;

	}

}
