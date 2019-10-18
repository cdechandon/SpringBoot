package atelierASI2.User.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import atelierASI2.CommonModel.User.UserModel;;

@RestController
public class MsgEmitterRestController {
	@Autowired
	BusService busService;
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateUserESB/{busName}")
	public void updateExtUserESB(@RequestBody UserModel user, @PathVariable String busName) {
		busService.updateExtUserESB(user, busName);
		System.out.println("Sent!");
	}
		
}
