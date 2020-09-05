package com.training.ms.notification.event.listener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.training.ms.notification.email.EmailSender;
import com.training.ms.notification.model.Event;
import com.training.ms.notification.model.MailMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class OrderCreatedListener {

	@Autowired
	EmailSender emailsender;
	@Autowired
	MailMessage mailMessage;
	
    private final List<String> messages = new ArrayList<>();
    
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
   
    @SuppressWarnings("unchecked")
	@KafkaListener(topics = "ORDER_CREATED", groupId = "kafka-sandbox")
    public void listen(Event event) {
        synchronized (event.getMessage()) {
        logger.info("Order Created Listener"); 	
        System.out.println("Order Created Listener"); 	
        LinkedHashMap<String,Object> order	 =  (LinkedHashMap<String, Object>) event.getMessagePayload().get("messagePayload");
        if(order == null)
        {
        	order	 = (LinkedHashMap<String, Object>)  event.getMessagePayload().get("mesaagePayload");
        }
        mailMessage.setSubject("Order Confirmation");
        mailMessage.setBody("Welcome xxx,\n"
        		+  event.getMessage() +"\n"
        		+ "Thanks for ordering with us.Your order id is "+order.get("orderId"));
        mailMessage.setTo("senttog@globallogic.com"); // replace email id
        mailMessage.setFrom("from@gmail.com");        // replace email id
        emailsender.sendMail(mailMessage);
        }
           
    }
    public List<String> getMessages() {
        return messages;
    }
}