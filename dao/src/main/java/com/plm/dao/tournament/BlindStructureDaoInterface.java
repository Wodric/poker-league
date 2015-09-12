package com.plm.dao.tournament;

/**
 * Interfce which reference all method to add in DAo Implementation
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public interface BlindStructureDaoInterface {
	
	/**
	 * THis methode save in database the pBlindStructureAsJson converted as blind structure
	 * @param pBlindStructureAsJson JSon of structure
	 */
	void persist(String pBlindStructureAsJson);

}
