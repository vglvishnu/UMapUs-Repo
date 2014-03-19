package com.umapus.infrastructure.dao;


import javax.naming.NamingException;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;
import com.umapus.domain.entity.User;

public interface LdapDao {

	public String CreateLDAPUser(SignUpRequest signuprequest) throws NamingException ;
	public User LoginUser(LoginRequest loginRequest) throws NamingException ;
	
}
