package com.umapus.infrastructure.dao;

public class DAOFactory {

	private LdapDao ldapDao;
	private SessionRepositoryDao sessionRepoDao;
    

	public SessionRepositoryDao getSessionRepoDao() {
		return sessionRepoDao;
	}

	public void setSessionRepoDao(SessionRepositoryDao sessionRepoDao) {
		this.sessionRepoDao = sessionRepoDao;
	}

	public void setLdapDao(LdapDao ldapDao) {
		this.ldapDao = ldapDao;
	}
	
	public  LdapDao getLdapDao(){
		return  this.ldapDao;
	}
	
}
