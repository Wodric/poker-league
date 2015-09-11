package com.plm.dao;

/**
 * This interface declare some methode to implement for each DAO
 * @Author Alexandre "Wodric" Lefèvre
 *
 * @param <T> Class of beans this interface will implement the DAO
 */
public interface PokerLeagueManagerDao<T> {
	
	  /**
	   * @param id it is search 
	   * @return Null if no entity with {@code id} exists
	   */
	  T getById(long id);
	  
	  /**
	   * save the transient(non existing in persistance) instance 
	   * @param transientInstance instance to save
	   */
	  void persist(T transientInstance);

	  /**
	   * Delete a persistant instance
	   * @param persistentInstance instance to delete, it must be a persistance instance
	   */
	  void delete(T persistentInstance);
}
