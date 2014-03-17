package com.umapus.infrastructure.dao;






public class DAOFactory {

	private LdapDao ldapDao;
    

	public void setLdapDao(LdapDao ldapDao) {
		this.ldapDao = ldapDao;
	}
	
	public  LdapDao getLdapDao(){
		return  this.ldapDao;
	}
	
}
