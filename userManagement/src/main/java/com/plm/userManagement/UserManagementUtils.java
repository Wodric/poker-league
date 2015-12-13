package com.plm.userManagement;

import java.util.List;

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
	public static void login(String pUsername, String pPassword) throws AuthenticationException{
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
	public static String getHashPassword(String pPassword, String pSalt) throws AuthenticationException{
		Sha512Hash hash = new Sha512Hash(pPassword,pSalt,HASH_ITERATION);
		return hash.toBase64();
	}
	
	/**
	 * Log out user
	 */
	public static void logout(){
		final Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
		{
			subject.logout();
			logger.info(String.format("Logout succeeded for %s", subject.getPrincipal()));
		}
	}

	/**
	 * VErify if user is authenticated (and not remembered)
	 * @return true if user if authenticated, false otherwise
	 */
	public static boolean isAuthenticated(){
		final Subject subject = SecurityUtils.getSubject();
		return subject.isAuthenticated();
	}


	/**
	 * Get unique identify of user
	 * @return the 
	 */
	public static String getPrincipal(){
		final Subject subject = SecurityUtils.getSubject();
		return (String) subject.getPrincipal();
	}

	/**
	 * verify if user is remembered
	 * @return true if user if remembered, false otherwise
	 */
	public static boolean isRemembered(){
		final Subject subject = SecurityUtils.getSubject();
		return subject.isRemembered();
	}

	/**
	 * MEthod which get if the user is a guest 
	 * @return true if no user authenticated or remembered
	 */
	public static boolean isGuest(){

		final Subject subject = SecurityUtils.getSubject();
		return (subject.getPrincipal() == null);
	}

	/**
	 * Verify if subject has role
	 * @param pRole the role to verify on user
	 * @return true if user has role
	 */
	public static boolean hasRole(String pRole){
		final Subject subject = SecurityUtils.getSubject();
		return subject.hasRole(pRole);
	}

	/**
	 * Verify if subject has a list of role
	 * @param pRoles the role list to verify on user
	 * @return true or false for each role
	 */
	public static boolean[] hasRoles(List<String> pRoles){
		final Subject subject = SecurityUtils.getSubject();
		return subject.hasRoles(pRoles);
	}

	/**
	 * Verify if subject has all role in the list
	 * @param pRoles the role list to verify on user
	 * @return true if user has all role in the list
	 */
	public static boolean hasAllRoles(List<String> pRoles){
		final Subject subject = SecurityUtils.getSubject();
		return subject.hasAllRoles(pRoles);
	}

	/**
	 * Verify if subject has permission
	 * @param pPermission the permission to verify on user
	 * @return true if user has permission
	 */
	public static boolean hasPermission(String pPermission){
		final Subject subject = SecurityUtils.getSubject();
		return subject.isPermitted(pPermission);
	}

	/**
	 * Verify if subject has a list of permissions
	 * @param pPermissions the permissions list to verify on user
	 * @return true or false for each permisison
	 */
	public static boolean[] hasPermission(List<String> pPermissions){
		final Subject subject = SecurityUtils.getSubject();
		final boolean[] permissionList = new boolean[pPermissions.size()];

		for (int i = 0; i < pPermissions.size(); i++)
			permissionList[i] = subject.isPermitted(pPermissions.get(i));

		return permissionList;
	}

	/**
	 * Verify if subject has all permissions in the list
	 * @param pPermissions the permission list to verify on user
	 * @return true if user has all permissions in the list
	 */
	public static boolean hasAllPermissions(List<String> pPermissions){
		final Subject subject = SecurityUtils.getSubject();

		for (String permission : pPermissions)
			if (!subject.isPermitted(permission))
				return false;

		return true;
	}

}
