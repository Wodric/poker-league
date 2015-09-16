package com.plm.dao.permission;

import com.plm.dao.beans.permission.Role;

/**
 * Dao for role table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class RoleDao {
	
	private static final RoleDaoImpl roleDao = new RoleDaoImpl();
	
	public static void persist(Role pRole) throws RuntimeException{
		roleDao.persist(pRole);
	}

	public static Role getById(long pId) throws RuntimeException  {
		return roleDao.getById(pId);
	}

	public static void delete(Role pPersistentInstance) throws RuntimeException{	
		roleDao.delete(pPersistentInstance);
	}

}
