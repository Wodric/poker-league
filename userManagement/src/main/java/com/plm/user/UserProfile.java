package com.plm.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum which represent the principal profile type for inscription
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public enum UserProfile {
	ADMINISTRATOR(1),
	GUEST(2),
	PLAYER(3),
	ASSOCIATION_OPERATOR(4),
	ASSOCIATION_CREATOR(5),
	ASSOCIATION_MANAGER(6),
	CASINO_OPERATOR(7),
	CASINO_CREATOR(8),
	CASINO_MANAGER(9),
	CASINO_ADMINISTRATOR(10),
	TOURNAMENT_DIRECTOR(11),
	OTHER_PROFESSIONAL(12);
	
	long id;
	
	/**
	 * init user profile with it's database ID, define in initiation database script
	 * @param id the database id
	 */
    UserProfile(long id) {
        this.id = id;
    }
    
    /**
     * get the database id
     * @return the id
     */
    long getId(){
    	return id;
    }
    
    /**
     * Get the choise offer to user on sign on for example
     * @return list of UserProfile the user can choose
     */
    public static List<UserProfile> getAllowedUserChoise(){
    	List<UserProfile> allowedChoise = new ArrayList<UserProfile>();
    	allowedChoise.add(UserProfile.PLAYER);
    	allowedChoise.add(UserProfile.ASSOCIATION_CREATOR);
    	allowedChoise.add(UserProfile.CASINO_CREATOR);
    	allowedChoise.add(UserProfile.TOURNAMENT_DIRECTOR);
    	allowedChoise.add(UserProfile.OTHER_PROFESSIONAL);    	
		return allowedChoise;
    }
};