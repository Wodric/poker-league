package com.plm.dao.beans.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.plm.dao.beans.permission.Role;

/**
 * User mapping class see mappings/User.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class User implements java.io.Serializable {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 2362844662347631041L;
	
	/*
	 * User Identifier in database
	 */
	private long userId;
	
	/**
	 * The attached user information
	 */
	private UserInformation userInformation;
	
	/**
	 * user display name
	 */
	private String firstname;
	
	/**
	 * user display name
	 */
	private String lastname;
	
	/**
	 * the user contact email
	 */
	private String email;
	
	/**
	 * the user password
	 */
	private String password;
	
	/**
	 * the user salt for password 
	 */
	private String passwordSalt;
	
	/**
	 * the user states (active or not)
	 */
	private Boolean active;
	
	/**
	 * the user status (verified account or not)
	 */
	private Boolean verified;
	
	/**
	 * the user date creation
	 */
	private Date createTime;
	
	/**
	 * the user date password modified
	 */
	private Date passwordModificationTime;
	
	/**
	 * List of role attached to user
	 */
	private Set<Role> roles = new HashSet<Role>(0);

	/**
	 * default constructor
	 */
	public User() {
	}

	/**
	 * Constructor with mandatory parameter
	 * @param userId User Identifier in database
	 * @param userInformation The attached user information
	 * @param firstname user first name name
	 * @param lastname user last name name
	 * @param email the user contact email
	 * @param password the user password
	 * @param passwordSalt the user salt for password 
	 */
	public User(long userId, UserInformation userInformation, String firstname, String lastname, String email, String password,
			String passwordSalt) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.userInformation = userInformation;
		this.email = email;
		this.password = password;
		this.passwordSalt = passwordSalt;
	}

	/**
	 * Constructor with all parameters
	 * @param userId User Identifier in database
	 * @param userInformation The attached user information
	 * @param firstname user first name name
	 * @param lastname user last name name
	 * @param email the user contact email
	 * @param password the user password
	 * @param passwordSalt the user salt for password 
	 * @param active the user status (active or not)
	 * @param verified the user status (verified account or not)
	 * @param createTime the user date creation
	 * @param passwordModificationTime the user date password modified
	 * @param roles List of role attached to user
	 */
	public User(long userId, UserInformation userInformation, String firstname, String lastname, String email, String password,
			String passwordSalt, Boolean active, Boolean verified, Date createTime, Date passwordModificationTime,
			Set<Role> roles) {
		this.userId = userId;
		this.userInformation = userInformation;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.active = active;
		this.verified = verified;
		this.createTime = createTime;
		this.passwordModificationTime = passwordModificationTime;
		this.roles = roles;
	}

	/***************
	 * GETTERS & SETTERS
	 ***************/
	
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserInformation getUserInformation() {
		return this.userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getVerified() {
		return this.verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPasswordModificationTime() {
		return this.passwordModificationTime;
	}

	public void setPasswordModificationTime(Date passwordModificationTime) {
		this.passwordModificationTime = passwordModificationTime;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
