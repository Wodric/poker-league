package com.plm.userManagement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
     * Number of iteration for password security
     */
    public final static int HASH_ITERATION = 50000;
    
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
	public static void login(String pUsername, String pPassword) throws AuthenticationException
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
	
	/**
	 * Return a random salt in base 64
	 * @return return a salt, each call return different salt
	 */
	public static String getRandomSalt(){
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        ByteSource salt = rng.nextBytes();
        return salt.toBase64();
	}
	
	/**
	 * Hash password with sha512 with 50000 iteration with salt
	 * @param pPassword password to hash
	 * @param pSalt salt used in password hash
	 * @throws AuthenticationException
	 */
	public static String getHashPassword(String pPassword, String pSalt) throws AuthenticationException
	{
		System.out.println(pSalt);
		System.out.println(pPassword);
		Sha512Hash hash = new Sha512Hash(pPassword,pSalt,HASH_ITERATION);
		return hash.toBase64();
	}

}
