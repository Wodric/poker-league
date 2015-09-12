package com.plm.dao.tournament;

import com.plm.dao.beans.tournament.LegalFee;

public class LegalFeeDao {
	
	private static final LegalFeeDaoImpl legalFeeDao = new LegalFeeDaoImpl();
	
	public static void persist(LegalFee pBlindStructure) throws RuntimeException{
		legalFeeDao.persist(pBlindStructure);
	}

	public static LegalFee getById(long pId) throws RuntimeException  {
		return legalFeeDao.getById(pId);
	}

	public static void delete(LegalFee pPersistentInstance) throws RuntimeException{	
		legalFeeDao.delete(pPersistentInstance);
	}

}
