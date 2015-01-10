package com.sjsu.courseapp.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
public class MessageReceiver {
	public static void main(String[] args) throws JMSException {
		  ApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");
		  JmsTemplate jmsTemplate=(JmsTemplate) context.getBean("jmsTemplate");
		  jmsTemplate.send(
        		new MessageCreator() {
        	          public ObjectMessage  createMessage(Session session) throws JMSException {
        	        	  ObjectMessage message = session.createObjectMessage();
        	        	  message.setObject("My first Message");       	              
        	               return message;
        	          }
        }  );
      
		  System.out.println("MESSAGE SENT TO myMessageQueue");
		  Message receivedMessage=jmsTemplate.receive("myMessageQueue");
		  ObjectMessage msg = (ObjectMessage)receivedMessage;
		  System.out.println("Message Received :"+msg.getObject().toString());
	}
}