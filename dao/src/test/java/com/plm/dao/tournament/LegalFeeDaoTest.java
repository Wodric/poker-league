package com.plm.dao.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.plm.dao.DAOTest;
import com.plm.dao.beans.tournament.LegalFee;
import com.plm.dao.util.HibernateUtil;

public class LegalFeeDaoTest extends DAOTest{
	
    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from LegalFee");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveLegalFeeToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from LegalFee").list().size());
		//add a blind structure in databse
		LegalFee legalFeeAdd = new LegalFee();
		legalFeeAdd.setDescription("Descript");
		legalFeeAdd.setShortName("Fr");
		LegalFeeDao.persist(legalFeeAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from LegalFee").list();
		long persistId = ((LegalFee) list.get(0)).getFeeId();
		assertEquals(1, list.size());
		// then delete
		LegalFee legalFeeToRemove = LegalFeeDao.getById(persistId);
		LegalFeeDao.delete(legalFeeToRemove);
		assertEquals(0, session.createQuery("from LegalFee").list().size());
		session.close();
	}
}
