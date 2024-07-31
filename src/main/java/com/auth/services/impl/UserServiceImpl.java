package com.auth.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dtos.UserDto;
import com.auth.entities.UserEntity;
import com.auth.repositories.UserRepository;
import com.auth.security.JwtService;
import com.auth.services.UserService;
import com.auth.shared.Utils;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
    PasswordEncoder passwordEncoder;
    	@Autowired
    UserRepository userRepository;
		@Autowired
	JwtService jwtService;	
	
	@Autowired
    Utils utils;

	@Autowired
	@Lazy
    AuthenticationManager authenticationManager;

	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 return  (UserDetails) userRepository.findByEmail(email)
				  .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
	    ModelMapper modelMapper = new ModelMapper();
	    
		UserEntity userEntity  = modelMapper.map(userDto, UserEntity.class);
		
	    userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		userEntity.setUserId(utils.generateStringId(32));
		
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userD = modelMapper.map(newUser, UserDto.class);
				
		return userD;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
	    ModelMapper modelMapper = new ModelMapper();
	  
	    List<UserEntity> usersEntity = userRepository.findAll();
	    
	    List <UserDto> usersDto = new ArrayList<>();
	    
	    for(UserEntity userEntity:usersEntity) {
	    	
	    	UserDto userDto = modelMapper.map(userEntity, UserDto.class);
	    	usersDto.add(userDto);
	    }
	    
	    return usersDto;
	  
	}

	@Override
	public UserDto authenticate(UserDto request) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    	UserEntity userEntity = userRepository.findByEmail(request.getEmail()).orElseThrow();

    	String jwt = jwtService.generateToken(userEntity);
		UserDto userDto  = new UserDto();
		userDto.setToken(jwt);
		userDto.setUserId(userEntity.getUserId());
		return userDto;
	}


	
	
	

}
