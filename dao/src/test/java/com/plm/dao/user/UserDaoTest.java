package com.plm.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.plm.dao.DAOTest;
import com.plm.dao.beans.user.User;
import com.plm.dao.util.HibernateUtil;

public class UserDaoTest extends DAOTest{

    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from User");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveBlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from User").list().size());
		//add a blind structure in database
		User userAdd = new User();
		userAdd.setUsername("test");
		userAdd.setPassword("test");
		userAdd.setPasswordSalt("test");
		userAdd.setEmail("test");
		UserDao.persist(userAdd);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from User").list();
		long persistId = ((User) list.get(0)).getUserId();
		assertEquals(1, list.size());
		// then delete
		User userToRemove = UserDao.getById(persistId);
		UserDao.delete(userToRemove);
		assertEquals(0, session.createQuery("from User").list().size());
		session.close();
	}
}
