package com.cpe.springboot.userUpdateESB;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpe.springboot.user.model.UserModel;

@RestController
public class MsgEmitterRestController {
	@Autowired
	BusService busService;
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateUserESB/{busName}")
	public void updateUserESB(@RequestBody UserModel user, @PathVariable String busName) {
		busService.updateUserESB(user, busName);
		System.out.println("Sent!");
	}
}
