package com.plm.dao.permission;

import com.plm.dao.beans.permission.Permission;

/**
 * Dao for permission table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class PermissionDao {
	
	private static final PermissionDaoImpl permissionDao = new PermissionDaoImpl();
	
	public static void persist(Permission pPermission) throws RuntimeException{
		permissionDao.persist(pPermission);
	}

	public static Permission getById(long pId) throws RuntimeException  {
		return permissionDao.getById(pId);
	}

	public static void delete(Permission pPersistentInstance) throws RuntimeException{	
		permissionDao.delete(pPersistentInstance);
	}

}
