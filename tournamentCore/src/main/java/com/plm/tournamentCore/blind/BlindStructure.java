package com.plm.tournamentCore.blind;

import java.util.LinkedList;

public class BlindStructure {
	/**
	 * This variable define the list au authorized
	 */
	private double[] BLIND_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1.1,1.2,1.3,1.4,1.5,1.6,1.8,2,2.5,3,4,5,6,8};
	
	/**
	 * id Of blind structure in database
	 */
	private long idBlindStructure;
	
	/**
	 * List of blind level which constitute the structure
	 */
	private LinkedList<BlindLevel> structure;
	
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

}
