package com.auth.entities;



import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails{


	private static final long serialVersionUID = -2092621308803529955L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(nullable=true)
    private String userId;
    
    @Column(nullable=false)
    private String fullName;
 
    
    @Column
    private boolean isAdmin = false;  
    

	@Column(nullable=true)
    private String email;
    
    @Column(nullable=true)
    private String password;

    
    public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String UserId) {
		this.userId = UserId;
	}
    
    public String getfullName() {
    	return this.fullName;
    }
    
    public void setfullName(String fullName) {
    	this.fullName = fullName;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    
   
    public void setEmail(String email) {
    	this.email = email;
    }
    
   

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
