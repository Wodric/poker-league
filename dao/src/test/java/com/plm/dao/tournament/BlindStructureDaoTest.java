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

public class BlindStructureDaoTest extends DAOTest{
	
	private static long persistBlindStructureId;
	
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
	// verify table empty then add object in database
	public void addABlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		//add a blind structure in databse
		BlindStructure structureToAdd = new BlindStructure("String to add");
		new BlindStructureDaoImpl().persist(structureToAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from BlindStructure").list();
		persistBlindStructureId = ((BlindStructure) list.get(0)).getIdBlindStructure();
		assertEquals(1, list.size());
		session.close();
		
	}
	
	@Test
	// remove the object previously create
	public void removeBlindStructureToDatabase(){
		// get element in database and delete it
		BlindStructure structureToRemove = new BlindStructureDaoImpl().getById(persistBlindStructureId);
		new BlindStructureDaoImpl().delete(structureToRemove);
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from BlindStructure").list().size());
		session.close();
	}

}
