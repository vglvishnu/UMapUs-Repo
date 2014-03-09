package com.umapus.infrastructure.dao;

public class DAOFactory {

	  public  LdapDao getLdapDao(){
		   return new LdapDaoImpl();
	  }
}
