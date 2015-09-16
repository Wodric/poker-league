package com.plm.dao.user;

import com.plm.dao.beans.user.User;

/**
 * Dao for user table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class UserDao {
	
	private static final UserDaoImpl userDao = new UserDaoImpl();
	
	public static void persist(User pTournamentInstance) throws RuntimeException{
		userDao.persist(pTournamentInstance);
	}

	public static User getById(long pId) throws RuntimeException  {
		return userDao.getById(pId);
	}

	public static void delete(User pPersistentInstance) throws RuntimeException{	
		userDao.delete(pPersistentInstance);
	}
}
