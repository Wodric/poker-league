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
import com.plm.dao.util.HibernateUtil;

/**
 * Test BlindStructure DAO class
 */
public class BlindStructureDaoTest extends DAOTest{
	
    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from BlindStructure");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveBlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		//add a blind structure in databse
		BlindStructure structureToAdd = new BlindStructure("String to add");
		BlindStructureDao.persist(structureToAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from BlindStructure").list();
		long persistBlindStructureId = ((BlindStructure) list.get(0)).getIdBlindStructure();
		assertEquals(1, list.size());
		// then delete
		BlindStructure structureToRemove = BlindStructureDao.getById(persistBlindStructureId);
		BlindStructureDao.delete(structureToRemove);
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		session.close();
		
	}
	

	
	@Test
	// verify table empty then add object in database
	public void addABlindStructureFromStringToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		//add a blind structure in databse
		BlindStructureDao.persist("String to add");
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from BlindStructure").list();
		long persistBlindStructureId = ((BlindStructure) list.get(0)).getIdBlindStructure();
		assertEquals(1, list.size());
		// then delete
		BlindStructure structureToRemove = BlindStructureDao.getById(persistBlindStructureId);
		BlindStructureDao.delete(structureToRemove);
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		session.close();
		
	}

}
