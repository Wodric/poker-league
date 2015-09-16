package com.plm.dao.permission;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.permission.Permission;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAo implementation to access to permission data.
 * Developers mustn't call directly the implementation but class PermissionDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class PermissionDaoImpl implements PermissionDaoInterface, PokerLeagueManagerDao<Permission>{

	private static Logger logger = LoggerFactory.getLogger(PermissionDaoImpl.class);

	 /**
	 * get permission persistent instance by Id
	 * @param pId  database id of permission searched
	 * @return Null if no entity with {@code id} exists
	 */
	@Override
	public Permission getById(long pId) {
		logger.debug("getting permission instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		Permission instance = (Permission) session.get(Permission.class.getName(), pId);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get permission instance with id: " + pId + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * THis method save in database the a Permission
	 * @param pBlindStructure the permission to save. Must be the object of DAO
	 */
	@Override
	public void persist(Permission pTransientInstance) {
		logger.debug("add perssistence to permissison instance");
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
			logger.error("add persistence to permissison instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}		
	}

	/**
	 * Delete a persistent instance of role
	 * @param pPersistentInstance role instance to delete, it must be a persistence instance
	 */
	@Override
	public void delete(Permission pPersistentInstance) {
		logger.debug("deleting permission instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete permission instance failed");
			session.close();
			throw re;
		}
	}

}
