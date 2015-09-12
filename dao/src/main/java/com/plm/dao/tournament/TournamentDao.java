package com.plm.dao.tournament;

import com.plm.dao.beans.tournament.Tournament;

/**
 * DAO for tournament table. You must call Dao operation from this class
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class TournamentDao {
	
	private static final TournamentDaoImpl tournamentDao = new TournamentDaoImpl();
	
	public static void persist(Tournament pTournamentInstance) throws RuntimeException{
		tournamentDao.persist(pTournamentInstance);
	}

	public static Tournament getById(long pId) throws RuntimeException  {
		return tournamentDao.getById(pId);
	}

	public static void delete(Tournament pPersistentInstance) throws RuntimeException{	
		tournamentDao.delete(pPersistentInstance);
	}

}
