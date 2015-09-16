package com.plm.dao.user;

import com.plm.dao.beans.user.UserInformation;

/**
 * Dao for user information table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class UserInformationDao {
	
	private static final UserInformationDaoImpl userInformationDao = new UserInformationDaoImpl();
	
	public static void persist(UserInformation pTournamentInstance) throws RuntimeException{
		userInformationDao.persist(pTournamentInstance);
	}

	public static UserInformation getById(long pId) throws RuntimeException  {
		return userInformationDao.getById(pId);
	}

	public static void delete(UserInformation pPersistentInstance) throws RuntimeException{	
		userInformationDao.delete(pPersistentInstance);
	}

}
