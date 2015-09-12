package com.plm.dao.tournament;

import com.plm.dao.beans.tournament.BlindStructure;
/**
 * Dao for blind structure table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class BlindStructureDao{
	
	private static final BlindStructureDaoImpl blindStructureDao = new BlindStructureDaoImpl();
	
	public void persist(String pBlindStructureAsJson){
		blindStructureDao.persist(pBlindStructureAsJson);
	}
	
	public void persist(BlindStructure pBlindStructure) throws RuntimeException{
		blindStructureDao.persist(pBlindStructure);
	}

	public BlindStructure getById(long pId) throws RuntimeException  {
		return blindStructureDao.getById(pId);
	}

	public void delete(BlindStructure pPersistentInstance) throws RuntimeException{	
		blindStructureDao.delete(pPersistentInstance);
	}

}
