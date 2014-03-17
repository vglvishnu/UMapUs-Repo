package com.umapus.infrastructure.dao;

import java.util.Hashtable;
import java.util.UUID;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.stereotype.Component;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;

@Component
public class LdapDaoImpl implements LdapDao {


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
	public String CreateLDAPUser(SignUpRequest signuprequest) throws NamingException {


		DirContext ldapCtx            = getDirContext(LDAP_JNDI);

		NamingEnumeration<SearchResult> result = findLDAPUserByUserId(ldapCtx,ldapSearchBase,signuprequest.getEmail());

		if(result.hasMoreElements()) {
			System.out.println("User already exists");
			return "User already exists";
		}


		/*while (result.hasMore()){
    	    	  SearchResult sr = (SearchResult) result.next();  
                  System.out.println(sr.toString()+"\n");  

    	      }*/
		String assign_GraphId         = UUID.randomUUID().toString();

		String entryDN                = "uid="+signuprequest.getEmail()+",dc=umapus,dc=com"; 
		Attribute cn                  = new BasicAttribute(CN, signuprequest.getEmail());  
		Attribute sn                  = new BasicAttribute(SN, signuprequest.getFamilyName());  
		Attribute uid                 = new BasicAttribute(UID, signuprequest.getEmail());  
		Attribute mail                = new BasicAttribute(MAIL, signuprequest.getEmail());    
		Attribute userPassword        = new BasicAttribute(USERPASSWORD, signuprequest.getPassWord());  
		Attribute graphid             = new BasicAttribute(GRAPHID, assign_GraphId);  
		Attribute oc                  = new BasicAttribute(OBJECTCLASS);

		oc.add(TOP);  
		oc.add(PERSON);  
		oc.add(ORGANIZATIONALPERSON);  
		oc.add(INETORGPERSON); 
		oc.add(UMAPUSMEMBERS); 

		BasicAttributes entry         = new BasicAttributes();  
		entry.put(cn);  
		entry.put(sn);  
		entry.put(mail);  
		entry.put(uid); 
		entry.put(userPassword);
		entry.put(graphid);
		entry.put(oc);

		ldapCtx.createSubcontext(entryDN, entry); 

		return assign_GraphId;
	}

	private DirContext getDirContext(String jndiName) throws NamingException{
		Context initCtx                = null;
		initCtx                        = new InitialContext();
		Context envCtx                 = (Context) initCtx.lookup(ENV);
		DirContext ldapCtx             = (DirContext) envCtx.lookup(LDAP_JNDI);
		return ldapCtx;
	}


	private NamingEnumeration<SearchResult> findLDAPUserByUserId(DirContext ctx, String ldapSearchBase, String userId) throws NamingException {

		String searchFilter                     = "(&(objectClass=umapusmembers)(uid=" + userId + "))";

		SearchControls searchControls           = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

		return results;
	}

	@Override
	public boolean LoginUser(LoginRequest loginRequest) throws NamingException {


		boolean isSuccess = false;
		String userDN = "uid=" +loginRequest.getuserName() +",dc=umapus,dc=com";

		DirContext ldapCtx            = getDirContext(LDAP_JNDI);
		//ldapCtx.bind(userDN, loginRequest.getPassWord());
		//isSuccess = true;
		try {
			Hashtable environment = (Hashtable) ldapCtx.getEnvironment().clone();
			environment.put(Context.SECURITY_PRINCIPAL, userDN);
			environment.put(Context.SECURITY_CREDENTIALS, loginRequest.getPassWord());

			DirContext dirContext = new InitialDirContext(environment);
			isSuccess = true;
		} catch (AuthenticationException e) {

			isSuccess = false;

		}

		return isSuccess;
	}
}
