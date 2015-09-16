package com.plm.dao.tournament;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.tournament.Tournament;
import com.plm.dao.util.HibernateUtil;


/**
 * This class contains the DAO implementation to access to Tournament data.
 * Developers mustn't call directly the implementation but class TournamentDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class TournamentDaoImpl implements TournamentDaoInterface, PokerLeagueManagerDao<Tournament> {

	private static Logger logger = LoggerFactory.getLogger(TournamentDaoImpl.class);
	
	 /**
	  * get tournament persistent instance by Id
	  * @param pId  database id of tournament searched
	  * @return Null if no entity with {@code id} exists
	  */
	@Override
	public Tournament getById(long pId) {
		logger.debug("getting LegalFee instance with id: " + pId);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		Tournament instance = (Tournament) session.get(Tournament.class.getName(), pId);
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
	 * THis method save in database the tournament in parameter
	 * @param pBlindStructure the tournament to save. Must be the object of DAO
	 */
	@Override
	public void persist(Tournament pTransientInstance) {
		logger.debug("add perssistence to tournament instance");
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
			logger.error("add persistence to tournament instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}
	}

	/**
	 * Delete a persistent instance of tournament
	 * @param pPersistentInstance tournament instance to delete, it must be a persistence instance
	 */
	@Override
	public void delete(Tournament pPersistentInstance) {
		logger.debug("deleting tournament instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(pPersistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete tournament instance failed");
			session.close();
			throw re;
		}
	}
}
