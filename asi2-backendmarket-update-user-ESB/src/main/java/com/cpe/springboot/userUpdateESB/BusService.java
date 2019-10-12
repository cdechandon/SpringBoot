package com.cpe.springboot.userUpdateESB;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.cpe.springboot.user.model.UserModel;
@Service
public class BusService {
	/*
	 * Inject the dependence JmsTemplate.
	 * this object could be used as soon
	 * as the app server create an instance of it
	 */
	@Autowired
	JmsTemplate jmsTemplate;
	
	Map<Integer,UserModel> userMap = new HashMap<>();
	
	public void updateUserESB(UserModel user, String busName) {
		/*
		 * Send a message to the bus channel busName with as content
		 * an UserModel object.
		 */
		jmsTemplate.convertAndSend(busName,user);
	}
}
