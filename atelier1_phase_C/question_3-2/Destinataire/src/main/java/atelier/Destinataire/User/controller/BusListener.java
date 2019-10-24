package atelier.Destinataire.User.controller;



import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import atelierASI2.CommonModel.User.UserModel;

@Component 
public class BusListener {
	
	@Autowired
	UserRestController userController;
	
	@JmsListener(destination="Update_User_Bus", containerFactory="connectionFactory")
	public void receiveUserUpdate(UserModel user, Message message) {
		System.out.println("Internal message received!");
		userController.updateUser(user, Integer.toString(user.getId()));
	}
	
	@JmsListener(destination="Update_Ext_User_Bus",containerFactory="connectionFactory")
	public void receiveExtUserUpdate(UserModel user,Message message) {
		System.out.println("External message received!");
		userController.updateUser(user, Integer.toString(user.getId()));
	}
}
