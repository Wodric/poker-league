package com.plm.dao.permission;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.permission.Role;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAo implementation to access to Blind structure data.
 * Developers mustn't call directly the implementation but class RoleDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class RoleDaoImpl implements RoleDaoInterface, PokerLeagueManagerDao<Role> {

	private static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	 /**
	 * get role persistent instance by Id
	 * @param pId  database id of role searched
	 * @return Null if no entity with {@code id} exists
	 */
	@Override
	public Role getById(long pId) {
		logger.debug("getting role instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		Role instance = (Role) session.get(Role.class.getName(), pId);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get role instance with id: " + pId + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * THis method save in database the role
	 * @param pBlindStructure the Role to save. Must be the object of DAO
	 */
	@Override
	public void persist(Role pTransientInstance) {
		logger.debug("add perssistence to role instance");
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
			logger.error("add persistence to role instance failed", e);
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
	public void delete(Role pPersistentInstance) {
		logger.debug("deleting role instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete role instance failed");
			session.close();
			throw re;
		}		
	}

}
