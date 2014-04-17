package com.umapus.infrastructure.dao;

import java.util.Hashtable;
import java.util.UUID;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;
import com.umapus.domain.entity.SignUpResponse;
import com.umapus.domain.entity.UMapUsConstants;
import com.umapus.domain.entity.User;
import com.umapus.domain.util.UMapUsMapper;

@Component
public class LdapDaoImpl implements LdapDao {

	@Autowired
	private UMapUsMapper umapusmapper;
	
	@Autowired
	private User user;
	
	

	static final String ENV="java:comp/env";
	static final String LDAP_JNDI ="ldap-ds";

	static final String TOP = "top";
	static final String PERSON = "person";
	static final String ORGANIZATIONALPERSON = "organizationalPerson";
	static final String INETORGPERSON = "inetOrgPerson";
	static final String UMAPUSMEMBERS = "umapusmembers";


	static final String CN = "cn";
	static final String SN = "sn";
	static final String UID = "uid";
	static final String MAIL = "mail";
	static final String USERPASSWORD = "userPassword";
	static final String GRAPHID = "graphid";
	static final String OBJECTCLASS = "objectClass";
	static final String GN = "givenName";

	static final String ldapSearchBase = "dc=umapus,dc=com";


	@Override
	public String CreateLDAPUser(SignUpRequest signuprequest) throws NamingException  {


		DirContext ldapCtx            = getDirContext(UMapUsConstants.LDAP_JNDI);

		NamingEnumeration<SearchResult> result = findLDAPUserByUserId(ldapCtx,UMapUsConstants.LDAPSEARCHBASE,signuprequest.getEmail());

		if(result.hasMoreElements()) {
			System.out.println("User already exists");
			return SignUpResponse.ALREADY_EXISTS.getStatus();
		}


		/*while (result.hasMore()){
    	    	  SearchResult sr = (SearchResult) result.next();  
                  System.out.println(sr.toString()+"\n");  

    	      }*/
		String assign_GraphId         = UUID.randomUUID().toString();

		String entryDN                = "uid="+signuprequest.getEmail()+","+UMapUsConstants.LDAPSEARCHBASE; 
		Attribute cn                  = new BasicAttribute(UMapUsConstants.CN, signuprequest.getEmail());  
		Attribute sn                  = new BasicAttribute(UMapUsConstants.SN, signuprequest.getFamilyName());  
		Attribute uid                 = new BasicAttribute(UMapUsConstants.UID, signuprequest.getEmail());  
		Attribute mail                = new BasicAttribute(UMapUsConstants.MAIL, signuprequest.getEmail());    
		Attribute userPassword        = new BasicAttribute(UMapUsConstants.USERPASSWORD, signuprequest.getPassWord());  
		Attribute graphid             = new BasicAttribute(UMapUsConstants.GRAPHID, assign_GraphId);  
		Attribute oc                  = new BasicAttribute(UMapUsConstants.OBJECTCLASS);

		oc.add(UMapUsConstants.TOP);  
		oc.add(UMapUsConstants.PERSON);  
		oc.add(UMapUsConstants.ORGANIZATIONALPERSON);  
		oc.add(UMapUsConstants.INETORGPERSON); 
		oc.add(UMapUsConstants.UMAPUSMEMBERS); 

		BasicAttributes entry         = new BasicAttributes();  
		entry.put(cn);  
		entry.put(sn);  
		entry.put(mail);  
		entry.put(uid); 
		entry.put(userPassword);
		entry.put(graphid);
		entry.put(oc);

		try {
			ldapCtx.createSubcontext(entryDN, entry);
		} catch (NamingException e) {
			//TODO add log statement
			return SignUpResponse.FAILED.getStatus();
		} 

		return SignUpResponse.SUCCESS.getStatus();
	}

	private DirContext getDirContext(String jndiName) throws NamingException{
		Context initCtx                = null;
		initCtx                        = new InitialContext();
		Context envCtx                 = (Context) initCtx.lookup(UMapUsConstants.ENV);
		DirContext ldapCtx             = (DirContext) envCtx.lookup(UMapUsConstants.LDAP_JNDI);
		return ldapCtx;
	}


	private NamingEnumeration<SearchResult> findLDAPUserByUserId(DirContext ctx, String ldapSearchBase, String userId) throws NamingException {

		String searchFilter                     = "(&(" 
		                                          +UMapUsConstants.OBJECTCLASS 
		                                          + "=" + 
		                                          UMapUsConstants.UMAPUSMEMBERS
		                                          + ")(" +
		                                          UMapUsConstants.UID 
		                                          +"=" 
		                                          + userId 
		                                          + "))";

		SearchControls searchControls           = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

		return results;
	}

	@Override
	public User LoginUser(LoginRequest loginRequest) throws NamingException {


		String userDN = "uid=" +loginRequest.getuserName() +","+UMapUsConstants.LDAPSEARCHBASE;

		DirContext ldapCtx            = getDirContext(UMapUsConstants.LDAP_JNDI);
		//ldapCtx.bind(userDN, loginRequest.getPassWord());
		//isSuccess = true;
		try {
			Hashtable environment = (Hashtable) ldapCtx.getEnvironment().clone();
			environment.put(Context.SECURITY_PRINCIPAL, userDN);
			environment.put(Context.SECURITY_CREDENTIALS, loginRequest.getPassWord());

			DirContext dirContext = new InitialDirContext(environment);
			Attributes userAttributes = dirContext.getAttributes(userDN);
			umapusmapper.MapDNAttributesToUser(userAttributes);
			
			
		} catch (AuthenticationException e) {

			user.setLoggedin(false);

		}

		return user;
	}
}
