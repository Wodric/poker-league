package com.plm.dao.tournament.service;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.plm.dao.tournament.BlindStructure;
import com.plm.dao.util.HibernateUtil;

/**
 * This class contains the service to access to Blind structure data
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructureDaoService {

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
        Session session = HibernateUtil.getCommitFlushModeSession();
    	try{
    		session.setFlushMode(FlushMode.COMMIT);
            tcx = session.beginTransaction();
            session.save(pBlindStructure);
            tcx.commit();
    	}
    	catch( Throwable e){
    		tcx.rollback();
    	}
    	finally{
            session.close();
    	}
		return pBlindStructure.getIdBlindStructure();
	}

}
