package com.plm.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ProfileUserTest {
	
	@Test
	public void getAllowedUserChoiseTest(){
		List<UserProfile> profiles = UserProfile.getAllowedUserChoise();
		assertTrue(profiles.contains(UserProfile.ASSOCIATION_CREATOR));
		assertTrue(profiles.contains(UserProfile.CASINO_CREATOR));
		assertTrue(profiles.contains(UserProfile.PLAYER));
		assertTrue(profiles.contains(UserProfile.TOURNAMENT_DIRECTOR));
		assertTrue(profiles.contains(UserProfile.OTHER_PROFESSIONAL));
		assertFalse(profiles.contains(UserProfile.ASSOCIATION_MANAGER));
		assertFalse(profiles.contains(UserProfile.ASSOCIATION_OPERATOR));
		assertFalse(profiles.contains(UserProfile.CASINO_ADMINISTRATOR));
		assertFalse(profiles.contains(UserProfile.CASINO_MANAGER));
		assertFalse(profiles.contains(UserProfile.CASINO_OPERATOR));
		assertFalse(profiles.contains(UserProfile.GUEST));
		assertFalse(profiles.contains(UserProfile.ADMINISTRATOR));
	}

}
