package com.plm.userManagement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
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

    /**
     * True if shiro security manager is initialized
     */
	private static boolean SHIRO_INITILIZED = false;
    
	/**
	 * initialize the shiro security manager
	 */
	public static void initiateShiro(){
		if(!SHIRO_INITILIZED){
			SHIRO_INITILIZED = true;
			logger.info("create security manager");
			SecurityUtils.setSecurityManager(securityManager);
		}
	}
	
	/**
	 * Log user with username and password
	 * @param pUsername User login for identification
	 * @param pPassword password non encrypted and non hash password
	 */
	public static void login(String pUsername, String pPassword)
	{
		final UsernamePasswordToken token = new UsernamePasswordToken(pUsername, pPassword);
		
		// ”Remember Me” built-in, just do this:
		token.setRememberMe(true);

		// With most of Shiro, you'll always want to make sure you're working with the currently executing user,
		// referred to as the subject
		final Subject currentUser = SecurityUtils.getSubject();

		// Authenticate
		currentUser.login(token);
	}

}
