package com.cpe.springboot.user.controller;

import java.util.Map;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cpe.springboot.user.model.UserModel;

@Component 
public class BusListener {
	
	@Autowired
	UserRestController userController;
	
	@JmsListener(destination="Update_User_Bus", containerFactory="connectionFactory")
	public void receiveUserUpdate(UserModel user, Message message) {
		System.out.println("Message received!");
		userController.updateUser(user, Integer.toString(user.getId()));
	}
}
