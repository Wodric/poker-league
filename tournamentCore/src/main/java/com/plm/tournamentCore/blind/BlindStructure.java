package com.plm.tournamentCore.blind;

import java.util.LinkedList;

import javax.xml.parsers.FactoryConfigurationError;

public class BlindStructure {
	/**
	 * This variable define the list of blind authorized
	 */
	public static final double[] BLIND_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1.1,1.2,1.3,1.4,1.5,1.6,1.8,2,2.4,3,4,5,6,8};
	
	/**
	 * This variable define the list of  authorized
	 */
	public static final double[] ANTE_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1,1,1,2,2,2,2,2,3,3,4,5,6,8};
	
	/**
	 * This variable define the average stack at the tournament end
	 */
	public static final int BLIND_AT_TOURNAMENT_END = 60;
	
	/**
	 * id Of blind structure in database
	 */
	private long idBlindStructure;
	
	/**
	 * List of blind level which constitute the structure
	 */
	private LinkedList<BlindLevel> structure;
	
	/**
	 * Factor to add or remove a decimal for a number
	 */
	private static int DECIMAL_FACTOR = 10;
	
	/**
	 * The minimum value for big blind with ante
	 */
	private static int MINIMUM_BLIND_DECIMAL_FOR_ANTE = 3;
	
	/**
	 * Set duration to 0 when it's not set by user
	 */
	private static int NO_DURATION = 0;
	
	/**
	 * Calculate the number of level needed to 
	 * @param pTournamentDurationExpected 
	 * @param pLevelDurations the duration of a level, consdier as constant
	 * @return the number of level needed to complete tournament
	 */
	private int calculateNumberOfLevels(int pTournamentDurationExpected, int pLevelDurations){
		return (int) Math.ceil(pTournamentDurationExpected / pLevelDurations);
	}
	
	/**
	 * calculate number Total Of Chips In the Tournament
	 * @param pMaxPlayerNumber representing the maximum number of player expected in tournament
	 * @param pInitialStackSize the initial stack size
	 * @return the number of chips
	 */
	private int calculateTotalNumberOfChips(int pMaxPlayerNumber, int pInitialStackSize){
		return pMaxPlayerNumber*pInitialStackSize;
	}
	
	
	/**
	 * Define the last big blind at schedule tournament end
	 * @param pTotalChips total number of chips in tournament
	 * @param pWithAnte boolean to define if there is ante to set
	 * @return a BlindLevel Object which contains small, big blind, ante and duration
	 */
	protected BlindLevel calculateBigBlindMax(int pTotalChips, boolean pWithAnte){
		return this.calculateBigBlindMax(pTotalChips, pWithAnte, NO_DURATION);
	}
	

	/**
	 * Define the last big blind at schedule tournament end
	 * @param pTotalChips total number of chips in tournament
	 * @param pWithAnte boolean to define if there is ante to set
	 * @param pLevelDuration the duration of the levels in structure
	 * @return a BlindLevel Object which contains small, big blind, ante and duration
	 */
	protected BlindLevel calculateBigBlindMax(int pTotalChips, boolean pWithAnte, int pLevelDuration){
		int theoricalLevel = pTotalChips / BLIND_AT_TOURNAMENT_END;
		int decimal = 0;
		// get an authorized blind
		while(theoricalLevel >= DECIMAL_FACTOR){
			decimal ++;
			theoricalLevel = theoricalLevel/DECIMAL_FACTOR;
		}
		
		boolean isAuthorizedMultibleFound = false;
		int authorizedMultipleIterator = 1; // theorical level must be 1 <= x <= 9.99
		
		while( !isAuthorizedMultibleFound && (authorizedMultipleIterator < BLIND_AUTHORIZED_MULTIPLE_TAB.length) ){
			if(theoricalLevel <= BLIND_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator]){
				isAuthorizedMultibleFound = true;
			}
			else{
				authorizedMultipleIterator++;
			}
		}
		
		// if we don't find a multiple in list 8 < theoricalLevel < 9.99  
		// round to first authorized multiple to next decade 
		if(authorizedMultipleIterator >=  BLIND_AUTHORIZED_MULTIPLE_TAB.length){
			authorizedMultipleIterator = 0;
			decimal ++;
		}
		
		int bigBlind = (int) (BLIND_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator] * Math.pow(DECIMAL_FACTOR, decimal));
		int smallBlind = bigBlind / 2;
		int ante = pWithAnte? calculateAnteFromBigBlind(BLIND_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator],DECIMAL_FACTOR) : 0;
		
		return new BlindLevel(pLevelDuration, smallBlind, bigBlind, ante);
	}
	
	/**
	 * Calculate the ante from the bigblind
	 * @param pBlindMultiple The blind multiple
	 * @param pBlindDecimal Number of decimal for the blind
	 * @return the ante size corresponding to the big blind. If pBlindDecimal < 3 return 0
	 */
	private int calculateAnteFromBigBlind(double pBlindMultiple, int pBlindDecimal){
		if(pBlindDecimal >= MINIMUM_BLIND_DECIMAL_FOR_ANTE){
			return (int) (pBlindMultiple * Math.pow(DECIMAL_FACTOR, (pBlindDecimal - 1)));
		}
		else{
			return 0; // if blind are small there is no blind
		}
	}

}
