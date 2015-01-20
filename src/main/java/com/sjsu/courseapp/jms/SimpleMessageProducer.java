package com.sjsu.courseapp.jms;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.sjsu.courseapp.loadbalancer.BackendStorage;
import com.sjsu.courseapp.loadbalancer.LoadBalancer;

public class SimpleMessageProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);
    
    protected JmsTemplate jmsTemplate; 
    
    protected int numberOfMessages = 100; 
    
    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessages(int nextInt,final String payload) throws JMSException {
        final StringBuilder buffer = new StringBuilder(); 

            buffer.append("Message '").append("' sent at: ").append(new Date());
            
           final int count = nextInt;
           // final String payload = buffer.toString();
            
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(payload); 
                    message.setIntProperty("messageCount", count);
                    System.out.println("Sending message number '{}'"+count);
                    return message;
                }
            });
        }
    }