package com.plm.dao.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.plm.dao.DAOTest;
import com.plm.dao.beans.tournament.BlindStructure;
import com.plm.dao.beans.tournament.Tournament;
import com.plm.dao.util.HibernateUtil;

/**
 * Test Tournament DAO class
 */
public class TournamentDaoTest extends DAOTest{
    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from Tournament");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveBlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from Tournament").list().size());
		//add a blind structure in databse
		Tournament tournamentAdd = new Tournament();
		BlindStructure structureToAdd = new BlindStructure("String to add");
		BlindStructureDao.persist(structureToAdd);
		tournamentAdd.setBlindStructure(structureToAdd);
		TournamentDao.persist(tournamentAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from Tournament").list();
		long persistId = ((Tournament) list.get(0)).getIdTournament();
		assertEquals(1, list.size());
		// then delete
		Tournament tournamentToRemove = TournamentDao.getById(persistId);
		TournamentDao.delete(tournamentToRemove);
		assertEquals(0, session.createQuery("from Tournament").list().size());
		session.close();
	}
}
