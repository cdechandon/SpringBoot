package atelierASI2.CommonModel.User;

import java.util.HashSet;
import java.util.Set;

import atelierASI2.CommonModel.Card.CardModel;
//we need Card Model
import atelierASI2.CommonModel.User.UserModel;

public class UserDisplay {
	private Integer id;
	private String login;
	private String pwd;
    private Set<Integer> cardList = new HashSet<>();
    
    public UserDisplay(UserModel user) {
    	this.id=user.getId();
    	this.login=user.getLogin();
    	this.pwd=user.getPwd();
    	for( CardModel card: user.getCardList()) {
    	this.cardList.add(card.getId());	
    	}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	  public Set<Integer> getCardList() { return cardList; }
	  
	  public void setCardList(Set<Integer> cardList) { this.cardList = cardList; }
	 
    
    

}
