package com.auth.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dtos.UserDto;
import com.auth.requests.UserLogin;
import com.auth.requests.UserRequest;
import com.auth.responses.AuthResponse;
import com.auth.responses.UserResponse;
import com.auth.services.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@PostMapping(path="/signup",
			       consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
			       produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
		
	    ModelMapper modelMapper = new ModelMapper();
	    
	    UserDto userDto = modelMapper.map(userRequest, UserDto.class);
	    
	    UserDto newUser = userService.createUser(userDto);
	    
	    UserResponse userResponse = modelMapper.map(newUser, UserResponse.class);
	    
	    return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);

	}
	

	@PostMapping(path="/signin",
		       consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
		       produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
	public AuthResponse login(@RequestBody UserLogin userLogin) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto = modelMapper.map(userLogin, UserDto.class);
		
		UserDto loginUser = userService.authenticate(userDto);
		
		AuthResponse authResponse = modelMapper.map(loginUser, AuthResponse.class);
		
		return authResponse;
	}
	
}
