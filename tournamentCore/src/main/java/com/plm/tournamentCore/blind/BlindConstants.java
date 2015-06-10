package com.plm.tournamentCore.blind;

import com.plm.tournamentCore.chip.ChipsSet;

public class BlindConstants {
	
	/**
	 * Factor to add or remove a decimal for a number
	 */
	public static final int DECIMAL_FACTOR = 10;
	
	/**
	 * The minimum value for big blind with ante
	 */
	public static final int MINIMUM_BLIND_DECIMAL_FOR_ANTE = 3;
	
	/**
	 * Set duration to 0 when it's not set by user
	 */
	public static final int NO_DURATION = 0;
	/**
	 * to define if structure uses ante= true
	 */
	public static final boolean WITH_ANTE = true; 
	/**
	 * to define if structure doesn't use ante = false
	 */
	public static final boolean WITHOUT_ANTE = false; 
	/**
	 * This variable define the list of blind authorized
	 */
	public static final double[] BLIND_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1.2,1.4,1.5,1.6,1.8,2,2.4,3,4,5,6,8};
	
	/**
	 * This variable define the list of  authorized
	 */
	public static final double[] ANTE_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1,1,2,2,2,2,3,3,4,5,6,10};
	
	/**
	 * This variable define the average stack at the tournament end
	 */
	public static final int BLIND_AT_TOURNAMENT_END = 60;
	
	/**
	 * NUmber of level to add after the end of theorical level
	 */
	public static final int NUMBER_OF_ADDITIONAL_LEVEL = 5;
	
	/**
	 * the default value of has ante
	 */
	public static final boolean DEFAULT_WITH_ANTE = false;
	/**
	 * The default tournament level to set in algorithm
	 */
	public static final int DEFAULT_LEVEL_DURATION = 20;
	
	/**
	 * Minimum level duration in minutes
	 */
	public static final int MIN_LEVEL_DURATION = 5;
	
	/**
	 * Maximum level duration in minutes
	 */
	public static final int MAX_LEVEL_DURATION = 600;
	
	/**
	 * The default tournament duration to set in algorithm
	 */
	public static final int DEFAULT_TOURNAMENT_DURATION = 240;
	
	/**
	 * Minimum tournament duration in minutes
	 */
	public static final int MIN_TOURNAMENT_DURATION = 30;
	
	/**
	 * Maximum tournament duration in minutes
	 */
	public static final int MAX_TOURNAMENT_DURATION = 60000;
	
	/**
	 * The defaul stack size to set in algorithm
	 */
	public static final int DEFAULT_INITIAL_STACK_SIZE = 15000;
	
	/**
	 * Minimum stack size
	 */
	public static final int MIN_INITIAL_STACK_SIZE = 1;
	
	/**
	 * Maximum stack size
	 */
	public static final int MAX_INITIAL_STACK_SIZE = 50000000;
	
	/**
	 * The default small blind value to set in algorithm
	 */
	public static final int DEFAULT_SMALL_BLIND_VALUE = 25;
	
	/**
	 * minimum small blind size 
	 */
	public static final int MIN_SMALL_BLIND_VALUE = 1;
	
	/**
	 * Maximum  small blind size 
	 */
	public static final int MAX_SMALL_BLIND_VALUE = 5000000;
	
	/**
	 * The default stack size to set in algorithm
	 */
	public static final BlindLevel DEFAULT_BLIND_LEVEL = new BlindLevel(
			DEFAULT_LEVEL_DURATION, DEFAULT_SMALL_BLIND_VALUE, DEFAULT_SMALL_BLIND_VALUE*2);
	
	/**
	 * The default number of player to set in algorithm
	 */
	public static final int DEFAULT_NUMBER_PLAYER = 8;
	
	/**
	 * Minimum player allowed
	 */
	public static final int MIN_NUMBER_PLAYER = 2;
	
	/**
	 * Maximum player allowed
	 */
	public static final int MAX_NUMBER_PLAYER = 50000;
	
	/**
	 * The default stack size to set in algorithm
	 */
	public static final ChipsSet DEFAULT_CHIPSET = 
			ChipsSet.getDefaultChipsSets().get(ChipsSet.DEFAULT_CHIPS_SET_INDEX);

}
