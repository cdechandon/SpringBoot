package atelierASI2.Store.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cpe.springboot.card.Controller.CardModelService;
import com.cpe.springboot.user.controller.UserService;

import atelierASI2.CommonModel.Card.CardModel;
import atelierASI2.CommonModel.Store.StoreModel;
import atelierASI2.CommonModel.User.UserModel;


@Service
@Component
public class StoreService {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	CardModelService cardService;
	@Autowired
	UserService userService; 
	@Autowired
	StoreRepository storeRepository;
	
	private StoreModel store;
	
	@JmsListener(destination="BUS_USER-STORE",containerFactory="connectionFactory")
	public void receiveMsg(String msgStr) {
		System.out.println("Received  "+msgStr+" from BUS_USER-STORE ! ");
	}
	
	
	
	public void generateNewStore(String name, int nb) {
		StoreModel store =new StoreModel();
		store.setName(name);
		
		List<CardModel> cardList=cardService.getRandCard(nb);
		for(CardModel c: cardList) {
			store.addCard(c);
		}
		storeRepository.save(store);
		this.store=store;
	}
	
	public boolean buyCard(Integer user_id, Integer card_id) {
		Optional<UserModel> u_option=userService.getUser(user_id);
		Optional<CardModel> c_option=cardService.getCard(card_id);
		if(!u_option.isPresent() || !c_option.isPresent()){
			return false;
		}
		UserModel u=u_option.get();
		CardModel c=c_option.get();
		if(u.getAccount() > c.getPrice()) {
			u.addCard(c);
			u.setAccount(u.getAccount()-c.getPrice());
			userService.updateUser(u);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean sellCard(Integer user_id, Integer card_id) {
		Optional<UserModel> u_option=userService.getUser(user_id);
		Optional<CardModel> c_option=cardService.getCard(card_id);
		if(!u_option.isPresent() || !c_option.isPresent()){
			return false;
		}
		UserModel u=u_option.get();
		CardModel c=c_option.get();

		//c.setStore(this.store);
		c.setUser(null);
		cardService.updateCard(c);
		u.setAccount(u.getAccount()+c.computePrice());
		userService.updateUser(u);
		return true;
	}
	
	public Set<CardModel> getAllStoreCard(){
		return this.store.getCardList();
	}
}
