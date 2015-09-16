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
import com.plm.dao.beans.user.UserInformation;
import com.plm.dao.util.HibernateUtil;

public class UserInformationTest extends DAOTest {

    @Test
    public void haveASessionFactory() {
        assertNotNull(HibernateUtil.getSessionFactory());
    }
 
    
    @Test
    // VErify table is empty
    public void haveNoObjectsAtStart() {
    	Query selectAllQuery = HibernateUtil.getCommitFlushModeSession().createQuery("from UserInformation");
        List<?> results = selectAllQuery.list();
        assertTrue(results.isEmpty());
    }
	
	@Test
	// verify table empty then add object then delete in database
	public void addAndRemoveBlindStructureToDatabase(){
		// verify table size
		Session session = HibernateUtil.getCommitFlushModeSession();
		assertEquals(0, session.createQuery("from UserInformation").list().size());
		//add a blind structure in database
		UserInformation userInformationAdd = new UserInformation();
		// create user linked to user information
		User userAdd = new User();
		userAdd.setUsername("test");
		userAdd.setPassword("test");
		userAdd.setPasswordSalt("test");
		userAdd.setEmail("test");
		UserDao.persist(userAdd);

		// there is an element in database  and save the blind structure id
		List<?> list = session.createQuery("from User").list();
		long persistId = ((User) list.get(0)).getUserId();
		userInformationAdd.setUser(UserDao.getById(persistId));
		UserInformationDao.persist(userInformationAdd);
		// there is an element in database  and save the blind structure id
		List<?> listInfo = session.createQuery("from UserInformation").list();
		long persistIdInfo = ((UserInformation) listInfo.get(0)).getFkUserId();
		assertEquals(1, listInfo.size());
		// then delete
		UserInformation userInformationToRemove = UserInformationDao.getById(persistIdInfo);
		UserInformationDao.delete(userInformationToRemove);
		assertEquals(0, session.createQuery("from UserInformation").list().size());
		session.close();
	}
}
