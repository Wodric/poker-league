package com.plm.dao.beans.user;

/**
 * UserInformation mapping class see maapings/UserInformation.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class UserInformation implements java.io.Serializable {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = -7858773338881280048L;
	
	/**
	 * The ID of user table. it's the identifier of user informations
	 */
	private long fkUserId;
	
	/**
	 * user identifiy with @see {@link #fkUserId}
	 */
	private User user;
	
	/**
	 * user phone with @see {@link #fkUserId}
	 */
	private String phone;
	
	/**
	 * user company with @see {@link #fkUserId}
	 */
	private String company;

	/**
	 * Default constructor
	 */
	public UserInformation() {
	}

	/**
	 * Contructor with default mandatory field
	 * @param user user link with
	 */
	public UserInformation(User user) {
		this.user = user;
	}

	/***************
	 * GETTERS & SETTERS
	 ***************/
	
	public long getFkUserId() {
		return this.fkUserId;
	}

	public void setFkUserId(long fkUserId) {
		this.fkUserId = fkUserId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
