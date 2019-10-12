package com.cpe.springboot.user.model;

import com.cpe.springboot.card.model.CardModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserModel implements Serializable {

	private static final long serialVersionUID = 2733795832476568049L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String login;
	private String pwd;
	private float account;
	private String lastname;
	private String surname;
	private String email;
	private Integer money;


	

	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "user")
	//@OneToMany(mappedBy = "user")
	private Set<CardModel> cardList = new HashSet<>();

	public UserModel() {
		this.login = "";
		this.pwd = "";
		this.lastname="lastname_default";
		this.surname="surname_default";
		this.email="email_default";
	}

	public UserModel(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.lastname="lastname_default";
		this.surname="surname_default";
		this.email="email_default";
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

	public Set<CardModel> getCardList() {
		return cardList;
	}

	public void setCardList(Set<CardModel> cardList) {
		this.cardList = cardList;
	}

	public void addAllCardList(Collection<CardModel> cardList) {
		this.cardList.addAll(cardList);
	}


	public void addCard(CardModel card) {
		this.cardList.add(card);
		card.setUser(this);
	}

	private boolean checkIfCard(CardModel c){
		for(CardModel c_c: this.cardList){
			if(c_c.getId()==c.getId()){
				return true;
			}
		}
		return false;
	}

	public float getAccount() {
		return account;
	}

	public void setAccount(float account) {
		this.account = account;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public String getSurName() {
		return surname;
	}

	public void setSurName(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

}
