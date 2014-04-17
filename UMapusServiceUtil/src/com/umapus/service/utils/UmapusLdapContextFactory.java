package com.umapus.service.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.naming.spi.InitialContextFactory;
import javax.naming.directory.InitialDirContext;

public class UmapusLdapContextFactory implements ObjectFactory,
InitialContextFactory {


    public Object getObjectInstance( Object obj, Name name, Context
nameCtx, Hashtable environment)
    throws NamingException {

        Hashtable env = new Hashtable();
        Reference ref = (Reference) obj;
        Enumeration addrs = ref.getAll();

        while (addrs.hasMoreElements()) {
            RefAddr addr = (RefAddr) addrs.nextElement();
            if (addr.getType().equals("java.naming.factory.initial")) {
                env.put(addr.getType(), addr.getContent().toString());
            } else if (addr.getType().equals("java.naming.provider.url")){
                env.put(addr.getType(), addr.getContent().toString());
            } else if
(addr.getType().equals("java.naming.security.authentication")) {
                env.put(addr.getType(), addr.getContent().toString());
            } else if
(addr.getType().equals("java.naming.security.principal")) {
                env.put(addr.getType(), addr.getContent().toString());
            } else if
(addr.getType().equals("java.naming.security.credentials")) {
                env.put(addr.getType(), addr.getContent().toString());
            } else if
(addr.getType().equals("com.sun.jndi.ldap.connect.pool")) {
                env.put(addr.getType(), addr.getContent().toString());
            }else if
(addr.getType().equals("ldap.base")) {
                env.put(addr.getType(), addr.getContent().toString());
            }
        }

        return this.getInitialContext(env);
    }

    public Context getInitialContext(Hashtable environment) throws
NamingException {
        return new InitialDirContext(environment);
    }

}