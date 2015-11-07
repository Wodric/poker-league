package com.plm.dao.beans.permission;

import java.util.HashSet;
import java.util.Set;

import com.plm.dao.beans.user.User;

/**
 * Role mapping class see mappings/Role.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class Role implements java.io.Serializable {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 8765293637396640228L;
	
	/*
	 * role Identifier in database
	 */
	private long roleId;
	
	/*
	 * role display name
	 */
	private String roleName;
	
	/*
	 * User the role is attach on
	 */
	private Set<User> users = new HashSet<User>(0);
	
	/**
	 * the permission attach to this role
	 */
	private Set<Permission> permissions = new HashSet<Permission>(0);

	/**
	 * default constructor
	 */
	public Role() {
	}

	/**
	 * Constructor with mandatory parameters
	 * @param roleId role Identifier in databass
	 */
	public Role(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Constructor with all parameters
	 * @param roleId role Identifier in database
	 * @param roleName role display name
	 * @param users User the role is attach on
	 * @param permissions the permission attach to this role
	 */
	public Role(long roleId, String roleName, Set<User> users, Set<Permission> permissions) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
		this.permissions = permissions;
	}

	/***************
	 * GETTERS & SETTERS
	 ***************/
	
	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
