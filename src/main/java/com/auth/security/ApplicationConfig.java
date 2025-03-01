package com.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.services.UserService;



@Configuration
public class ApplicationConfig {
	
    @Autowired
	UserService userDetailsService;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
    
   @Bean
   AuthenticationProvider authenticationProvider() {
	   
	   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	   authProvider.setUserDetailsService(userDetailsService);
	   authProvider.setPasswordEncoder(passwordEncoder);
	   
	   return authProvider;
   }
   
   @Bean 
   AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	   return config.getAuthenticationManager();
   }
   
  
   

}
