package com.umapus.domain.entity;

public class UMapUsConstants {
	public static final String ENV="java:comp/env";
	public static final String LDAP_JNDI ="ldap-ds";

	public static final String TOP = "top";
	public static final String PERSON = "person";
	public static final String ORGANIZATIONALPERSON = "organizationalPerson";
	public static final String INETORGPERSON = "inetOrgPerson";
	public static final String UMAPUSMEMBERS = "umapusmembers";


	public static final String CN = "cn";
	public static final String SN = "sn";
	public static final String UID = "uid";
	public static final String MAIL = "mail";
	public static final String USERPASSWORD = "userPassword";
	public static final String GRAPHID = "graphid";
	public static final String OBJECTCLASS = "objectClass";
	public static final String GN = "givenName";

	public static final String LDAPSEARCHBASE = "dc=umapus,dc=com";
	
	
	public static final String JsEMAIL = "email";
	public static final String JsFAMILYNAME = "familyname";
	public static final String JsFIRSTNAME = "firstname";
	public static final String JsLASTNAME = "lastname";
	public static final String JsPASSWD = "passwd";
	
	public static final int SessionMaxInActiveTimeinSec = 5 * 60;
	public static final int RedisKeyTTLInHrs = 24;
	
}

