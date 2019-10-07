package atelierASI2.User.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atelierASI2.User.controller.UserService;
import atelierASI2.CommonModel.User.UserModel;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	private List<UserModel> getAllUsers() {
		return userService.getAllUsers();

	}
	
	@RequestMapping("/user/{id}")
	private UserModel getUser(@PathVariable String id) {
		Optional<UserModel> ruser;
		ruser= userService.getUser(id);
		if(ruser.isPresent()) {
			return ruser.get();
		}
		return null;

	}
	
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public void addUser(@RequestBody UserModel user) {
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addUser/{busName}")
	public void addUserBus(@RequestBody UserModel user,@PathVariable String busName) {
		userService.sendUser(user,busName);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/user/{id}")
	public void updateUser(@RequestBody UserModel user,@PathVariable String id) {
		user.setId(Integer.valueOf(id));
		userService.updateUser(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/auth")
	private boolean getAllCourses(@RequestParam("login") String login, @RequestParam("pwd") String pwd) {
		if( userService.getUserByLoginPwd(login,pwd).size() > 0) {
			return true;
		}
		return false;
	}
}
