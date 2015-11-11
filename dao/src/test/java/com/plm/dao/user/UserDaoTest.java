package com.plm.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
		userAdd.setFirstname("test");
		userAdd.setLastname("test");
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
	
	@Test
	// verify table empty then add object then delete in database
	public void getUserByEmailTest(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from User").list().size());
		// test null pointer
		assertEquals(null, UserDao.getUserByEmail("plm@plm.com"));
		//add a user in database
		User userAdd = new User();
		userAdd.setFirstname("firstNamePlm");
		userAdd.setLastname("lastNamePlm");
		userAdd.setPassword("test");
		userAdd.setPasswordSalt("test");
		userAdd.setEmail("plm@plm.com");
		UserDao.persist(userAdd);
		
		
		User userAdd2 = new User();
		userAdd2.setFirstname("firstNamePlm2");
		userAdd2.setLastname("lastNamePlm2");
		userAdd2.setPassword("test");
		userAdd2.setPasswordSalt("test");
		userAdd2.setEmail("plm2@plm.com");
		UserDao.persist(userAdd2);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from User").list();
		assertEquals(2, list.size());
		// then delete
		User userFromEmail = UserDao.getUserByEmail("plm@plm.com");
		assertEquals("firstNamePlm", userFromEmail.getFirstname());
		assertEquals(null, UserDao.getUserByEmail("plm3@plm.com"));
		this.removeUsersFromTests(session);
		session.close();
	}
	
	@Test
	// verify table empty then add object then delete in database
	public void alreadyExistEmailTest(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from User").list().size());
		// test null condition 
		assertFalse(UserDao.alreadyExistingEmail("plm@plm.com"));	
		//add a user in database
		User userAdd = new User();
		userAdd.setFirstname("firstNamePlm");
		userAdd.setLastname("lastNamePlm");
		userAdd.setPassword("test");
		userAdd.setPasswordSalt("test");
		userAdd.setEmail("plm@plm.com");
		UserDao.persist(userAdd);
		
		
		User userAdd2 = new User();
		userAdd2.setFirstname("firstNamePlm2");
		userAdd2.setLastname("lastNamePlm2");
		userAdd2.setPassword("test");
		userAdd2.setPasswordSalt("test");
		userAdd2.setEmail("plm2@plm.com");
		UserDao.persist(userAdd2);
		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from User").list();
		assertEquals(2, list.size());
		// then delete
		assertTrue(UserDao.alreadyExistingEmail("plm@plm.com"));		
		assertTrue(UserDao.alreadyExistingEmail("plm2@plm.com"));
		assertFalse(UserDao.alreadyExistingEmail("plm3@plm.com"));
		this.removeUsersFromTests(session);
		session.close();
	}
	
	private void removeUsersFromTests(Session session){
		List<User> usersToRemove = session.createQuery("from User").list();
		
		for(User aUser : usersToRemove){
			UserDao.delete(
					UserDao.getById(
							aUser.getUserId()));
		}
	}
}
