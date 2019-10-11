package atelierASI2.User.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



//need Card Model Service and Card Model

import atelierASI2.User.controller.UserRepository;

import atelierASI2.CommonModel.User.UserModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Data
@Component
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
	
	private final Environment environment = null;
	/**
	 * inject the dependence JmsTemplate.
	 * this object could be used as soon
	 * as app server create an instance of it
	 */
	@Autowired
	JmsTemplate jmsTemplate;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	/*@PostConstruct
	public void init() {
		queue = environment.getProperty(QUEUE_KEY);
	}*/
	
	@JmsListener(destination="USER_BUS_DEST",containerFactory="connectionFactory")
	public void receiveUpdateUser(UserModel user) {
		
	}
	
	public void sendMsg(String msg) {
		System.out.println("sending msg...");
		jmsTemplate.convertAndSend("BUS_USER-STORE",msg);
	}
	
	public void sendUserService(UserService userService,String busName) {
		System.out.println("Sending userService to "+busName+".");
		jmsTemplate.convertAndSend(busName,userService);
	}
	
	public void sendUser(UserModel user,String busName) {
		System.out.println("sending an user as a message...");
		/**
		 * send a message to the bus channel busName with as content
		 * a "user".
		 */
		jmsTemplate.convertAndSend(busName,user);
	}
	
	/*@Autowired
	private CardModelService cardModelService;*/
	
	public List<UserModel> getAllUsers(){
		List<UserModel> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}
	
	public Optional<UserModel> getUser(String id) {
		return userRepository.findById(Integer.valueOf(id));
	}
	
	public Optional<UserModel> getUser(Integer id) {
		return userRepository.findById(id);
	}
	
	public void addUser(UserModel user) {
		// needed to avoid detached entity passed to persist error
		userRepository.save(user);
		/*List<CardModel> cardList=cardModelService.getRandCard(5);
		for(CardModel card: cardList) {
			user.addCard(card);
		}*/
		userRepository.save(user);
	}

	public void updateUser(UserModel user) {
		userRepository.save(user);

	}

	public void deleteUser(String id) {
		userRepository.deleteById(Integer.valueOf(id));
	}

	public List<UserModel> getUserByLoginPwd(String login, String pwd) {
		List<UserModel> ulist=null;
		ulist=userRepository.findByLoginAndPwd(login,pwd);
		return ulist;
	}

}
