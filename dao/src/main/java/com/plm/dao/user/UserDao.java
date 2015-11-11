package com.plm.dao.user;

import com.plm.dao.beans.user.User;

/**
 * Dao for user table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class UserDao {
	
	private static final UserDaoImpl userDao = new UserDaoImpl();
	
	public static void persist(User pTransientInstance) throws RuntimeException{
		userDao.persist(pTransientInstance);
	}

	public static User getById(long pId) throws RuntimeException  {
		return userDao.getById(pId);
	}

	public static void delete(User pPersistentInstance) throws RuntimeException{	
		userDao.delete(pPersistentInstance);
	}
	
	public static User getUserByEmail(String email) throws RuntimeException{	
		return userDao.getUserByEmail(email);
	}
	
	public static boolean alreadyExistingEmail(String email) throws RuntimeException{	
		return userDao.alreadyExistingEmail(email);
	}
	
}
