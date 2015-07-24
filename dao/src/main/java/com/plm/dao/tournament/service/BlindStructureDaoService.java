package com.plm.dao.tournament.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.tournament.BlindStructure;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the service to access to Blind structure data
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructureDaoService {
	
	private static Logger logger = LoggerFactory.getLogger(BlindStructureDaoService.class);


	/**
	 * THis methode save in database the pBlindStructureAsJson converted as blind structure
	 * @param pBlindStructureAsJson JSon of structure
	 * @return return the id auto generate for database
	 */
	public static long createBlindStructure(String pBlindStructureAsJson){
		return BlindStructureDaoService.createBlindStructure(new BlindStructure(pBlindStructureAsJson));
	}
	
	/**
	 * THis method save in database the a blind structure
	 * @param pBlindStructure the blindstructure to save. Must be the object of DAO
	 * @return return the id auto generate for database
	 */
	public static long createBlindStructure(BlindStructure pBlindStructure){
        Transaction tcx = null;
        System.out.println("test");
        Session session = HibernateUtil.getCommitFlushModeSession();
        long id = -1;
    	try{
            tcx = session.beginTransaction();
            id = (Integer) session.save(pBlindStructure);
            tcx.commit();
    	}
    	catch( Throwable e){
    		tcx.rollback();
    		logger.error(e.getMessage(), e);
    	}
    	finally{
            session.close();
    	}
		return id;
	}

}
