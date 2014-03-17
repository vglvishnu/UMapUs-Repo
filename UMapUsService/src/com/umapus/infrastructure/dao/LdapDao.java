package com.umapus.infrastructure.dao;


import javax.naming.NamingException;


import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;

public interface LdapDao {

	public String CreateLDAPUser(SignUpRequest signuprequest) throws NamingException ;
	public boolean LoginUser(LoginRequest loginRequest) throws NamingException ;
	
}
