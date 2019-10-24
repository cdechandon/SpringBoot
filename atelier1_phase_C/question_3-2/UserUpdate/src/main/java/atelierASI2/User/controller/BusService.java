package atelierASI2.User.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import atelierASI2.CommonModel.User.UserModel;
@Service
public class BusService {
	/*
	 * Inject the dependence JmsTemplate.
	 * this object could be used as soon
	 * as the app server create an instance of it
	 */
	@Autowired
	JmsTemplate jmsTemplate;
	
	
	public void updateExtUserESB(UserModel user, String busName) {
		/*
		 * Send a message to the bus channel busName with as content
		 * an UserModel object.
		 */
		jmsTemplate.convertAndSend(busName,user);
	}
}
