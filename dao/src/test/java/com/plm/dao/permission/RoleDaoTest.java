package com.plm.dao.permission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.plm.dao.DAOTest;
import com.plm.dao.beans.permission.Role;
import com.plm.dao.util.HibernateUtil;

/**
 * Test Tournament DAO class
 */
public class RoleDaoTest extends DAOTest{
    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from Role");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveBlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from Role").list().size());
		//add a blind structure in databse
		Role roleAdd = new Role();
		RoleDao.persist(roleAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from Role").list();
		long persistId = ((Role) list.get(0)).getRoleId();
		assertEquals(1, list.size());
		// then delete
		Role roleToRemove = RoleDao.getById(persistId);
		RoleDao.delete(roleToRemove);
		assertEquals(0, session.createQuery("from Role").list().size());
		session.close();
	}
}
