package com.auth.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.auth.dtos.UserDto;



public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto userDto);
	
    UserDto authenticate(UserDto userDto);
	
	List<UserDto> getAllUsers();

}
