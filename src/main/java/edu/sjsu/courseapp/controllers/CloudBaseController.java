package edu.sjsu.courseapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.sjsu.courseapp.cloudwatch.AwsCloudWatch;
import com.sjsu.courseapp.jms.SimpleMessageProducer;

import edu.sjsu.courseapp.dao.jdbc.CloudDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.RateDaoJdbcImpl;
import edu.sjsu.courseapp.dao.jdbc.UserDaoJdbcImpl;
import edu.sjsu.courseapp.domain.Instance;


@Controller
public class CloudBaseController {
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
	 * function to call the request generator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/generator", method = RequestMethod.POST)
	public ModelAndView newRequestGenerator() {
		ModelAndView modelView;
		modelView = new ModelAndView("generator");
		return modelView;
	}
	
	/**
	 * function to call the requst genarator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loadChart", method = RequestMethod.GET)
	public ModelAndView loadChart() {
		ModelAndView modelView;
		modelView = new ModelAndView("charts");
		return modelView;
	}

	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/monitoring", method = RequestMethod.GET)
	public ModelAndView monitoring() {
		ModelAndView modelView;
		modelView = new ModelAndView("monitoring");
		InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context1
				.getBean("instanceServ");
		String instances="";
		// modelView.addObject("instance_list", cs.getInstanceallList());
		List<Instance> listIns = cs.getInstanceallList();
		int i=1;
		for (Instance ins : listIns) {
			if(ins.getName().startsWith("c")) continue;
			
			List<Datapoint> dataList = AwsCloudWatch
					.getCloudWatchMonitoringData("CPUUtilization",ins.getName());
			String chartData = "[";
			for (Datapoint data : dataList) {
				chartData = chartData + "{" + "time:'" + data.getTimestamp()
						+ "', ";
				chartData = chartData + "maximum:" + data.getMaximum() + ",";
				chartData = chartData + "minimum:" + data.getMinimum() + ",";
				chartData = chartData + "average:" + data.getAverage() + "},";
			}
			chartData = chartData + "]";
			instances = instances + " " + ins.getName() + " ";
			String datakey,instanceNameKey;
			datakey = "chartData" + i;
			instanceNameKey = "instanceId" + i;
			i++;
			modelView.addObject(datakey, chartData);
			modelView.addObject(instanceNameKey, ins.getName());
		}
		//modelView.addObject("instanceId", instances);
		return modelView;
	}
	
	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/networkin", method = RequestMethod.GET)
	public ModelAndView networkin() {
		ModelAndView modelView;
		modelView = new ModelAndView("networkin");
		InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context1
				.getBean("instanceServ");
		String instances="";
		// modelView.addObject("instance_list", cs.getInstanceallList());
		List<Instance> listIns = cs.getInstanceallList();
		int i=1;
		for (Instance ins : listIns) {
			if(ins.getName().startsWith("c")) continue;
			
			List<Datapoint> dataList = AwsCloudWatch
					.getCloudWatchMonitoringData("NetworkIn",ins.getName());
			String chartData = "[";
			for (Datapoint data : dataList) {
				chartData = chartData + "{" + "time:'" + data.getTimestamp()
						+ "', ";
				chartData = chartData + "maximum:" + data.getMaximum() + ",";
				chartData = chartData + "minimum:" + data.getMinimum() + ",";
				chartData = chartData + "average:" + data.getAverage() + "},";
			}
			chartData = chartData + "]";
			instances = instances + " " + ins.getName() + " ";
			String datakey,instanceNameKey;
			datakey = "chartData" + i;
			instanceNameKey = "instanceId" + i;
			i++;
			modelView.addObject(datakey, chartData);
			modelView.addObject(instanceNameKey, ins.getName());
		}
		//modelView.addObject("instanceId", instances);
		return modelView;
	}
	
	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/networkout", method = RequestMethod.GET)
	public ModelAndView networkout() {
		ModelAndView modelView;
		modelView = new ModelAndView("networkout");
		InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context1
				.getBean("instanceServ");
		String instances="";
		// modelView.addObject("instance_list", cs.getInstanceallList());
		List<Instance> listIns = cs.getInstanceallList();
		int i=1;
		for (Instance ins : listIns) {
			if(ins.getName().startsWith("c")) continue;
			
			List<Datapoint> dataList = AwsCloudWatch
					.getCloudWatchMonitoringData("NetworkOut",ins.getName());
			String chartData = "[";
			for (Datapoint data : dataList) {
				chartData = chartData + "{" + "time:'" + data.getTimestamp()
						+ "', ";
				chartData = chartData + "maximum:" + data.getMaximum() + ",";
				chartData = chartData + "minimum:" + data.getMinimum() + ",";
				chartData = chartData + "average:" + data.getAverage() + "},";
			}
			chartData = chartData + "]";
			instances = instances + " " + ins.getName() + " ";
			String datakey,instanceNameKey;
			datakey = "chartData" + i;
			instanceNameKey = "instanceId" + i;
			i++;
			modelView.addObject(datakey, chartData);
			modelView.addObject(instanceNameKey, ins.getName());
		}
		//modelView.addObject("instanceId", instances);
		return modelView;
	}
	
	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/diskread", method = RequestMethod.GET)
	public ModelAndView diskread() {
		ModelAndView modelView;
		modelView = new ModelAndView("diskread");
		InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context1
				.getBean("instanceServ");
		String instances="";
		// modelView.addObject("instance_list", cs.getInstanceallList());
		List<Instance> listIns = cs.getInstanceallList();
		int i=1;
		for (Instance ins : listIns) {
			if(ins.getName().startsWith("c")) continue;
			
			List<Datapoint> dataList = AwsCloudWatch
					.getCloudWatchMonitoringData("DiskReadBytes",ins.getName());
			String chartData = "[";
			for (Datapoint data : dataList) {
				chartData = chartData + "{" + "time:'" + data.getTimestamp()
						+ "', ";
				chartData = chartData + "maximum:" + data.getMaximum() + ",";
				chartData = chartData + "minimum:" + data.getMinimum() + ",";
				chartData = chartData + "average:" + data.getAverage() + "},";
			}
			chartData = chartData + "]";
			instances = instances + " " + ins.getName() + " ";
			String datakey,instanceNameKey;
			datakey = "chartData" + i;
			instanceNameKey = "instanceId" + i;
			i++;
			modelView.addObject(datakey, chartData);
			modelView.addObject(instanceNameKey, ins.getName());
		}
		//modelView.addObject("instanceId", instances);
		return modelView;
	}
	
	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/diskwrite", method = RequestMethod.GET)
	public ModelAndView diskwrite() {
		ModelAndView modelView;
		modelView = new ModelAndView("diskwrite");
		InstanceDaoJdbcImpl cs = (InstanceDaoJdbcImpl) context1
				.getBean("instanceServ");
		String instances="";
		// modelView.addObject("instance_list", cs.getInstanceallList());
		List<Instance> listIns = cs.getInstanceallList();
		int i=1;
		for (Instance ins : listIns) {
			if(ins.getName().startsWith("c")) continue;
			
			List<Datapoint> dataList = AwsCloudWatch
					.getCloudWatchMonitoringData("DiskWriteBytes",ins.getName());
			String chartData = "[";
			for (Datapoint data : dataList) {
				chartData = chartData + "{" + "time:'" + data.getTimestamp()
						+ "', ";
				chartData = chartData + "maximum:" + data.getMaximum() + ",";
				chartData = chartData + "minimum:" + data.getMinimum() + ",";
				chartData = chartData + "average:" + data.getAverage() + "},";
			}
			chartData = chartData + "]";
			instances = instances + " " + ins.getName() + " ";
			String datakey,instanceNameKey;
			datakey = "chartData" + i;
			instanceNameKey = "instanceId" + i;
			i++;
			modelView.addObject(datakey, chartData);
			modelView.addObject(instanceNameKey, ins.getName());
		}
		//modelView.addObject("instanceId", instances);
		return modelView;
	}
	
	
	/**
	 * function to call the cloud watch monitoring service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cloudwatch/{instanceId}", method = RequestMethod.GET)
	public ModelAndView monitoring(@PathVariable String instanceId) {
		ModelAndView modelView;
		System.out.println("Got request param: " + instanceId);  
		modelView = new ModelAndView("cloudwatch");
		List<Datapoint> cpudataList = AwsCloudWatch.getCloudWatchMonitoringData("CPUUtilization",instanceId);
		List<Datapoint> nwindataList = AwsCloudWatch.getCloudWatchMonitoringData("NetworkIn",instanceId);
		List<Datapoint> nwoutdataList = AwsCloudWatch.getCloudWatchMonitoringData("NetworkOut",instanceId);
		List<Datapoint> diskreaddataList = AwsCloudWatch.getCloudWatchMonitoringData("DiskReadBytes",instanceId);
		List<Datapoint> diskwritedataList = AwsCloudWatch.getCloudWatchMonitoringData("DiskWriteBytes",instanceId);
		
		String cpuchartData = "[";
		for (Datapoint data : cpudataList) {
			cpuchartData = cpuchartData + "{" + "time:'" + data.getTimestamp()
					+ "', ";
			cpuchartData = cpuchartData + "maximum:" + data.getMaximum() + ",";
			cpuchartData = cpuchartData + "minimum:" + data.getMinimum() + ",";
			cpuchartData = cpuchartData + "average:" + data.getAverage() + "},";
		}
		cpuchartData = cpuchartData + "]";
		modelView.addObject("instanceId", instanceId);
		modelView.addObject("cpuchartData", cpuchartData);
		
		String nwinchartData = "[";
		for (Datapoint data : nwindataList) {
			nwinchartData = nwinchartData + "{" + "time:'" + data.getTimestamp()
					+ "', ";
			nwinchartData = nwinchartData + "maximum:" + data.getMaximum() + ",";
			nwinchartData = nwinchartData + "minimum:" + data.getMinimum() + ",";
			nwinchartData = nwinchartData + "average:" + data.getAverage() + "},";
		}
		nwinchartData = nwinchartData + "]";
		modelView.addObject("nwinchartData", nwinchartData);
		
		
		String nwoutchartData = "[";
		for (Datapoint data : nwoutdataList) {
			nwoutchartData = nwoutchartData + "{" + "time:'" + data.getTimestamp()
					+ "', ";
			nwoutchartData = nwoutchartData + "maximum:" + data.getMaximum() + ",";
			nwoutchartData = nwoutchartData + "minimum:" + data.getMinimum() + ",";
			nwoutchartData = nwoutchartData + "average:" + data.getAverage() + "},";
		}
		nwoutchartData = nwoutchartData + "]";
		modelView.addObject("nwoutchartData", nwoutchartData);
		
		String diskreadchartData = "[";
		for (Datapoint data : diskreaddataList) {
			diskreadchartData = diskreadchartData + "{" + "time:'" + data.getTimestamp()
					+ "', ";
			diskreadchartData = diskreadchartData + "maximum:" + data.getMaximum() + ",";
			diskreadchartData = diskreadchartData + "minimum:" + data.getMinimum() + ",";
			diskreadchartData = diskreadchartData + "average:" + data.getAverage() + "},";
		}
		diskreadchartData = diskreadchartData + "]";
		modelView.addObject("diskreadchartData", diskreadchartData);
		
		String diskwritechartData = "[";
		for (Datapoint data : diskwritedataList) {
			diskwritechartData = diskwritechartData + "{" + "time:'" + data.getTimestamp()
					+ "', ";
			diskwritechartData = diskwritechartData + "maximum:" + data.getMaximum() + ",";
			diskwritechartData = diskwritechartData + "minimum:" + data.getMinimum() + ",";
			diskwritechartData = diskwritechartData + "average:" + data.getAverage() + "},";
		}
		diskwritechartData = diskwritechartData + "]";
		modelView.addObject("diskwritechartData", diskwritechartData);
		
		
		return modelView;
	}
	
	
	@RequestMapping(value = "/executeGenerator", method = RequestMethod.POST)
	public ModelAndView executeGenerator(@RequestParam("email") String email,
			@RequestParam("memory") String memory,
			@RequestParam("request") int request,
			@RequestParam("userid") int userid,
			@RequestParam("cpu") String cpu,
			@RequestParam("country") String country,
			@RequestParam("storage")String storage,
			@RequestParam("algorithm") String algorithm,
			@RequestParam("osType") String osType,
			@RequestParam("os") String os) {
		ModelAndView modelView;
		System.out.println("*************************************");
		System.out.println("email address::" + email);
		System.out.println("memory::" + memory);
		System.out.println("number of request::" + request);
		System.out.println("no of cpu::" + cpu);
		System.out.println("storage::" + storage);
		System.out.println("country::" + country);
		System.out.println("osType::" + osType);
		System.out.println("os::" + os);
		System.out.println("userid::" + userid);
		System.out.println("Algoritm:" + algorithm);
		System.out.println("*************************************");

		String requestXML = "<request> <email>" + email + "</email>"
				+ "<memory>" + memory + "</memory>" + "<requestId>"
				+ rand.nextInt() + "</requestId>" + "<userId>"
				+ userid + "</userId>" + "<cpu>" + cpu + "</cpu>"+ "<storage>" + storage + "</storage>"
				+ "<osType>" + osType + "</osType>"+ "<os>" + os + "</os>"+ "<country>" + country + "</country>" + "<algorithm>" + algorithm + "</algorithm>"+ "<count>" + request + "</count>"+"</request>";
		simpleMessageProducer.setNumberOfMessages(request);
		try {
			simpleMessageProducer.sendMessages(requestXML);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView = new ModelAndView("generatorResponse");
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
	

