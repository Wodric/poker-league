package com.plm.dao.beans.permission;

import java.util.HashSet;
import java.util.Set;

/**
 * User mapping class see maapings/User.hbm.xml files in ressources for mapping
 * @Author Alexandre "Wodric" Lefèvre
 */
public class Permission implements java.io.Serializable {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = -3956771937015847996L;
	
	/*
	 * permission Identifier in database
	 */
	private long permissionId;
	
	/*
	 * value of the permission
	 */
	private String permission;
	
	/**
	 * Roles this Permission is attach on
	 */
	private Set<Role> roles = new HashSet<Role>(0);
	
	/**
	 * default constructor
	 */
	public Permission() {
	}

	/**.
	 * Constructor with mandatory values
	 * @param permissionId permission Identifier in database
	 */
	public Permission(long permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * Constructor with all  parameter
	 * @param permissionId permission Identifier in database
	 * @param permission value of the permission
	 * @param roles  Roles this Permission is attach on
	 */
	public Permission(long permissionId, String permission, Set<Role> roles) {
		this.permissionId = permissionId;
		this.permission = permission;
		this.roles = roles;
	}
	
	/**
	 * Constructor with all  parameter and without auto generated permissionId
	 * @param permission value of the permission
	 * @param roles  Roles this Permission is attach on
	 */
	public Permission(String permission, Set<Role> roles) {
		this.permission = permission;
		this.roles = roles;
	}
	
	/***************
	 * GETTERS & SETTERS
	 ***************/

	public long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * Added method
	 */
		
	public void addRoles(Set<Role> roles) {
		if(this.roles == null){
			this.roles = new HashSet<Role>();
		}
		this.roles.addAll(roles);
	}
	
	public void addRole(Role role) {
		if(this.roles == null){
			this.roles = new HashSet<Role>();
		}
		this.roles.add(role);
	}

}
