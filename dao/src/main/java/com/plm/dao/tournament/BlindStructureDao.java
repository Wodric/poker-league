package com.plm.dao.tournament;

import com.plm.dao.beans.tournament.BlindStructure;
/**
 * Dao for blind structure table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class BlindStructureDao{
	
	private static final BlindStructureDaoImpl blindStructureDao = new BlindStructureDaoImpl();
	
	public static void persist(String pBlindStructureAsJson){
		blindStructureDao.persist(pBlindStructureAsJson);
	}
	
	public static void persist(BlindStructure pBlindStructure) throws RuntimeException{
		blindStructureDao.persist(pBlindStructure);
	}

	public static BlindStructure getById(long pId) throws RuntimeException  {
		return blindStructureDao.getById(pId);
	}

	public static void delete(BlindStructure pPersistentInstance) throws RuntimeException{	
		blindStructureDao.delete(pPersistentInstance);
	}

}
