package com.plm.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.user.User;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAO implementation to access to user data.
 * Developers mustn't call directly the implementation but class UserDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class UserDaoImpl implements UserDaoInterface, PokerLeagueManagerDao<User>{
	
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	/**
	 * HQL request as string which must get the user from email
	 */
	private static final String HQL_SELECT_USER_FROM_EMAIL = "FROM User u WHERE u.email='?'";

	 /**
	  * get User persistent instance by Id
	  * @param pId  database id of user searched
	  * @return Null if no entity with {@code id} exists
	  */
	@Override
	public User getById(long pId) {
		logger.debug("getting user instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		User instance = (User) session.get(User.class.getName(), pId);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get user instance with id: " + pId + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * THis method save in database the user in parameter
	 * @param pBlindStructure the user to save. Must be the object of DAO
	 */
	@Override
	public void persist(User pTransientInstance) {
		logger.debug("add perssistence to user instance");
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
			logger.error("add persistence to user instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}
	}

	/**
	 * Delete a persistent instance of user
	 * @param pPersistentInstance user instance to delete, it must be a persistence instance
	 */
	@Override
	public void delete(User pPersistentInstance) {
		logger.debug("deleting user instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete user instance failed");
			session.close();
			throw re;
		}
	}
	
	/**
	 * Get the user from email
	 * @param pEmail the email it search
	 */
	public User getUserByEmail(String pEmail){
		logger.debug("get user instance from email");
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
    		Query query = session.createQuery(HQL_SELECT_USER_FROM_EMAIL.replace("?", pEmail));
    		List<User> results = query.list();
    		// email must be unique, must have only 1 result
    		if(results != null && results.size() != 0){
    			return results.get(0);
    		}
    		else{
    			return null;
    		}
		} catch (RuntimeException re) {
			logger.error("get user instance failed");
			session.close();
			throw re;
		}
	}
	
	/**
	 * Verify if user with this email already exist
	 * @param pEmail mail to verify
	 * @return true if mail already exist, faélse otherwise
	 */
	public boolean alreadyExistingEmail(String pEmail){
		return UserDao.getUserByEmail(pEmail) == null ? false : true;
	}

}
