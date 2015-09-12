package com.plm.dao.tournament;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.tournament.LegalFee;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAo implementation to access to LegalFee data.
 * Developers mustn't call directly the implementation but class LegalFeeDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class LegalFeeDaoImpl implements LegalFeeDaoInterface, PokerLeagueManagerDao<LegalFee> {

	private static Logger logger = LoggerFactory.getLogger(LegalFeeDaoImpl.class);
	
	/**
	 * get legal fee persistent instance by Id
	 * @param pId  database id of legal fee search
	 * @return Null if no entity with {@code id} exists
	 */
	@Override
	public LegalFee getById(long pId) {
		logger.debug("getting LegalFee instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		LegalFee instance = (LegalFee) session.get(LegalFee.class.getName(), pId);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get LegalFee instance with id: " + pId + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * Delete a persistent instance of legal fee
	 * @param pPersistentInstance legal fee instance to delete, it must be a persistence instance
	 */
	@Override
	public void persist(LegalFee pTransientInstance) {
		logger.debug("add perssistence to LegalFee instance");
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
			logger.error("add persistence to LegalFee instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}
		
	}

	/**
	 * Delete a persistent instance of legal fee
	 * @param pPersistentInstance legal fee instance to delete, it must be a persistence instance
	 */
	@Override
	public void delete(LegalFee pPersistentInstance) {
		logger.debug("deleting LegalFee instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete LegalFee instance failed");
			session.close();
			throw re;
		}
	}

}
