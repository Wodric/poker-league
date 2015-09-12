package com.plm.dao.tournament;

import com.plm.dao.beans.tournament.LegalFee;
/**
 * DAO for legal fee table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class LegalFeeDao {
	
	private static final LegalFeeDaoImpl legalFeeDao = new LegalFeeDaoImpl();
	
	public static void persist(LegalFee pLegalFee) throws RuntimeException{
		legalFeeDao.persist(pLegalFee);
	}

	public static LegalFee getById(long pId) throws RuntimeException  {
		return legalFeeDao.getById(pId);
	}

	public static void delete(LegalFee pPersistentInstance) throws RuntimeException{	
		legalFeeDao.delete(pPersistentInstance);
	}

}
