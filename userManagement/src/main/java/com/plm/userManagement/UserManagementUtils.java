package com.plm.userManagement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util mathod for user management
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class UserManagementUtils {
	
	private static Logger logger = LoggerFactory.getLogger(UserManagementUtils.class);
	
	/**
	 * Load shiro context
	 */
	private final static Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

	/**
	 * Security manager
	 */
    private final static SecurityManager securityManager = factory.getInstance();
    
	
	public static void initiateShiro(){
		try{
			SecurityUtils.getSecurityManager();
			// if there is an exception security manager is not initialize
		}
		catch(Exception e){
			logger.info("init security manager");
			SecurityUtils.setSecurityManager(securityManager);
		}
	}

}
