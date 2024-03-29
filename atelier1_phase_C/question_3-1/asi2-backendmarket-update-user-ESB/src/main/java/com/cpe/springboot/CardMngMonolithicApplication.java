package com.cpe.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cpe.springboot.user.model.UserModel;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

@EnableJms
@SpringBootApplication
public class CardMngMonolithicApplication {

	@Autowired
	JmsTemplate jmsTemplate;
	
	
	
	

	@Bean
	public JmsListenerContainerFactory< ? > connectionFactory(ConnectionFactory connectionFactory,
															  DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.

		//enable topic mode
		factory.setPubSubDomain(true);
		return factory;
	}


	/**
	 * Executed after application start
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doInitAfterStartup() {
		//enable to be in topic mode! to do at start
		jmsTemplate.setPubSubDomain(true);
	}

	public static void main(String[] args) {
		
		SpringApplication.run(CardMngMonolithicApplication.class, args);
	}




}
