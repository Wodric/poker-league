package com.plm.dao.tournament;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.PokerLeagueManagerDao;
import com.plm.dao.beans.tournament.BlindStructure;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the DAo implementation to access to Blind structure data.
 * Developers mustn't call directly the implementation but class BlindStructureDao
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructureDaoImpl implements BlindStructureDaoInterface, PokerLeagueManagerDao<BlindStructure>{
	
	private static Logger logger = LoggerFactory.getLogger(BlindStructureDaoImpl.class);

	public BlindStructureDaoImpl(){
	}
	/**
	 * THis methode save in database the pBlindStructureAsJson converted as blind structure
	 * @param pBlindStructureAsJson JSon of structure
	 */
	@Override
	public void persist(String pBlindStructureAsJson){
		this.persist(new BlindStructure(pBlindStructureAsJson));
	}
	
	/**
	 * THis method save in database the a blind structure
	 * @param pBlindStructure the blindstructure to save. Must be the object of DAO
	 */
	@Override
	public void persist(BlindStructure pBlindStructure) throws RuntimeException{
		logger.debug("add perssistence to BlindStructure instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
            tcx = session.beginTransaction();
            session.save(pBlindStructure);
            tcx.commit();
            logger.debug("add persistence successful");
            session.close();
    	}
    	catch(RuntimeException e){
			logger.error("add persistence to BlindStructure instance failed", e);
    		tcx.rollback();
    		session.close();
    		throw e;
    	}
	}

	 /**
	 * get blind structure persistant instance by Id
	 * @param id  database id of blind structure search
	 * @return Null if no entity with {@code id} exists
	 */
	@Override
	public BlindStructure getById(long id) throws RuntimeException  {
		logger.debug("getting BlindStructure instance with id: " + id);
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
			BlindStructure instance = (BlindStructure) session.get(BlindStructure.class.getName(), id);
			logger.debug("get successful");
			session.close();
			return instance;
		} catch (RuntimeException re) {
			logger.error("get BlindStructure instance with id: " + id + " failed", re);
			session.close();
			throw re;
		}
	}

	/**
	 * Delete a persistante instance of blind structure
	 * @param blind structure instance to delete, it must be a persistance instance
	 */
	@Override
	public void delete(BlindStructure persistentInstance) throws RuntimeException{
		logger.debug("deleting BlindStructure instance");
        Transaction tcx = null;
        Session session = HibernateUtil.getCommitFlushModeSession();
		try {
            tcx = session.beginTransaction();
            session.delete(persistentInstance);
            tcx.commit();
            logger.debug("delete successful");
            session.close();
		} catch (RuntimeException re) {
			logger.error("delete BlindStructure instance failed");
			session.close();
			throw re;
		}		
	}

}
