package com.plm.dao;

/**
 * This interface declare some methode to implement for each DAO
 * @Author Alexandre "Wodric" Lefèvre
 *
 * @param <T> Class of beans this interface will implement the DAO
 */
public interface PokerLeagueManagerDao<T> {
	
	  /**
	   * get persistent instance by Id
	   * @param pId it is search 
	   * @return Null if no entity with {@code id} exists
	   */
	  T getById(long pId);
	  
	  /**
	   * save the transient(non existing in persistence) instance 
	   * @param pTransientInstance instance to save
	   */
	  void persist(T pTransientInstance);

	  /**
	   * Delete a persistent instance
	   * @param pPersistentInstance instance to delete, it must be a persistence instance
	   */
	  void delete(T pPersistentInstance);
}
