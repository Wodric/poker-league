package com.plm.dao.user;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.user.UserInformation;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAO implementation to access to UserInformation data.
 * Developers mustn't call directly the implementation but class UserInformationDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class UserInformationDaoImpl implements UserInformationDaoInterface, PokerLeagueManagerDao<UserInformation>{
	private static Logger logger = LoggerFactory.getLogger(UserInformationDaoImpl.class);

	 /**
	  * get user information persistent instance by Id
	  * @param pId  database id of user information searched
	  * @return Null if no entity with {@code id} exists
	  */
	@Override
	public UserInformation getById(long pId) {
		logger.debug("getting user information instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		UserInformation instance = (UserInformation) session.get(UserInformation.class.getName(), pId);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get user information instance with id: " + pId + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * THis method save in database the user information in parameter
	 * @param pBlindStructure the user information to save. Must be the object of DAO
	 */
	@Override
	public void persist(UserInformation pTransientInstance) {
		logger.debug("add perssistence to user information instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
            tcx = session.beginTransaction();
            session.save(pTransientInstance);
            tcx.commit();
            logger.debug("add persistence successful");
            session.close();
    	}
    	catch(RuntimeException e){
			logger.error("add persistence to user information instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}
	}

	/**
	 * Delete a persistent instance of user Information
	 * @param pPersistentInstance user information instance to delete, it must be a persistence instance
	 */
	@Override
	public void delete(UserInformation pPersistentInstance) {
		logger.debug("deleting user information instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete user information instance failed");
			session.close();
			throw re;
		}
	}


}
