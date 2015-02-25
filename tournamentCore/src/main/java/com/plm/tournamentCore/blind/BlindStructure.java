package com.plm.tournamentCore.blind;

import java.util.LinkedList;

import com.plm.tournamentCore.chip.Chip;
import com.plm.tournamentCore.chip.ChipsSet;

/**
 * The blind structure is an object representing the list of level of tournament. 
 * @author Alexandre Lefevre
 */
public class BlindStructure {	
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
	public static int DECIMAL_FACTOR = 10;
	
	/**
	 * The minimum value for big blind with ante
	 */
	public static int MINIMUM_BLIND_DECIMAL_FOR_ANTE = 3;
	
	/**
	 * Set duration to 0 when it's not set by user
	 */
	public static int NO_DURATION = 0;
	/**
	 * to define if structure uses ante= true
	 */
	public static boolean WITH_ANTE = true; 
	/**
	 * to define if structure doesn't use ante = false
	 */
	public static boolean WITHOUT_ANTE = false; 
	/**
	 * This variable define the list of blind authorized
	 */
	public static final double[] BLIND_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1.2,1.4,1.6,1.8,2,2.4,3,4,5,6,8};
	
	/**
	 * This variable define the list of  authorized
	 */
	public static final double[] ANTE_AUTHORIZED_MULTIPLE_TAB = new double[] {1,1,1,2,2,2,3,3,4,5,6,10};
	
	/**
	 * This variable define the average stack at the tournament end
	 */
	public static final int BLIND_AT_TOURNAMENT_END = 60;
	

//	/**
//	 * 
//	 * @param pMaxPlayerNumber
//	 * @param pInitialStackSize
//	 * @param pTournamentDurationExpected
//	 * @param pLevelDurations
//	 * @param pMinimumBlind
//	 * @param pWithAnte
//	 */
//	public BlindStructure(int pMaxPlayerNumber, int pInitialStackSize, int pTournamentDurationExpected, 
//			int pLevelDurations, BlindLevel pMinimumBlind, boolean pWithAnte, ChipsSet pChipSet){
//		int totalChip = calculateTotalNumberOfChips(pMaxPlayerNumber,pInitialStackSize);
//		BlindLevel maxLevelExpected = calculateBigBlindMax(totalChip, WITH_ANTE,pLevelDurations);
//		int totalNumberOfLevel = calculateNumberOfLevels(pTournamentDurationExpected,pLevelDurations);
//		
//		//
//		/**
//		 * ALGO : Calculer le totalNumberOfLevel,blindFactor et theoricFactorBetweenLevels
//		 * A partir de ces éléments calculer le prochian niveau de blind théoric en arrondissant au supérieur
//		 * Tant qu'il y a des modifications faites par la validation faire évoluer la structure sans redondance.
//		 * Quand in n'y a plus de correction depuis un facteur de 10 au minimum mettre la répétition en route. 
//		 * Finir par rapport au nombre de level plus en rajouter 4 après
//		 */
//		int blindFactor = maxLevelExpected.getBigBlind() / pMinimumBlind.getBigBlind(); 
//		double theoricFactorBetweenLevels = Math.pow(blindFactor, 1/(double)totalNumberOfLevel);
//		
//		
//		
//		int decade = 0 ;
//		while(blindFactor >= DECIMAL_FACTOR){
//			decade ++;
//			blindFactor = blindFactor/DECIMAL_FACTOR;
//		}
//
//	
//	}
	
	public BlindStructure(){
		super();
	}
	
	/**
	 * Validate or modify the level 
	 * @param pLevel current level to test. If the level is validate it don't change. 
	 * If it's not validate, it will be modify to be valid. In case we can't found adequate level in a decade, throws Exception
	 * @param pLowestChips the lowest chip in chips choice
	 * @throws IllegalArgumentException in case it don't found multiple in a decade
	 * @return true if the level is validate, false otherwise. 
	 */
	protected boolean validateOrModifyNextLevel(BlindLevel pLevel, Chip pLowestChips) throws IllegalArgumentException{
		// verify if the small blind is a multiple of lowest chip
		if( (pLevel.getSmallBlind() % pLowestChips.getValue()) == 0 && (pLevel.getAnte() % pLowestChips.getValue() == 0)){
			return true;
		}
		else{
			// calcul the next compatible blind with lowest chips
			double theoricalLevel = pLevel.getBigBlind();
			int decimal = 0;
			// get an authorized blind
			while(theoricalLevel >= DECIMAL_FACTOR){
				decimal ++;
				theoricalLevel = theoricalLevel/DECIMAL_FACTOR;
			}
			
			// get the multiple of the blind 
			int idMultiple = 0;
			while( theoricalLevel != BLIND_AUTHORIZED_MULTIPLE_TAB[idMultiple] 
					&& idMultiple<BLIND_AUTHORIZED_MULTIPLE_TAB.length ){
				idMultiple ++;
			}
			
			// 1 to length to do not test the current multiple.
			boolean newMultipleFound = false;
			int iterator = 1;
			int currentMultipleId = idMultiple;
			int currentDecimal = decimal;
			while(iterator<BLIND_AUTHORIZED_MULTIPLE_TAB.length && !newMultipleFound){
				// if we arrive at the end of the authorized blind multiple need to start again with increased decimal
				currentMultipleId = ( iterator + idMultiple )  % BLIND_AUTHORIZED_MULTIPLE_TAB.length;
				currentDecimal = ( iterator + idMultiple ) >= BLIND_AUTHORIZED_MULTIPLE_TAB.length ? 
						decimal+1 : decimal;
				double smallBlindToTest = BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] * Math.pow(DECIMAL_FACTOR,currentDecimal);
				double anteToTest=0;
				if(currentDecimal >= MINIMUM_BLIND_DECIMAL_FOR_ANTE){
					anteToTest = BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] * Math.pow(DECIMAL_FACTOR,currentDecimal-1);
				}

				
				// if the small blind and ante can be paid with these new value get them
				/*
				 * > 1 to avoid round problem of double type. Can't have part of chips, so it consider it as 0
				 */
				if((smallBlindToTest % pLowestChips.getValue()) < 1 && (anteToTest % pLowestChips.getValue() < 1)){
					newMultipleFound = true;
				}
				else{
					iterator ++;
				}
			}
			 
			// Consider that if it make all the turn of possible blind structure, lowest chip value is inadequate
			if(iterator >= BLIND_AUTHORIZED_MULTIPLE_TAB.length){
				throw new IllegalArgumentException("Can't find blind level multiple on the lowest chip value :" + pLowestChips.getValue() );
			}
			
			pLevel.setBlindsAutomatics((int) (BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] * Math.pow(DECIMAL_FACTOR, currentDecimal)));
			pLevel.setAnte(pLevel.getAnte() != 0? 
					calculateAnteFromBigBlind(ANTE_AUTHORIZED_MULTIPLE_TAB[currentMultipleId],currentDecimal) : 0);
			
			return false;
			
		}		
	}
	/**
	 * Calculate the number of level needed to 
	 * @param pTournamentDurationExpected 
	 * @param pLevelDurations the duration of a level, consider as constant
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
	 * @param pLevelDuration the duration of the levels in structure
	 * @return a BlindLevel Object which contains small, big blind, ante and duration
	 */
	protected BlindLevel calculateBigBlindMax(int pTotalChips, boolean pWithAnte, int pLevelDuration){
		// cast to double necesarry s
		double theoricalLevel = (double) pTotalChips / (double) BLIND_AT_TOURNAMENT_END;
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
		int ante = pWithAnte? calculateAnteFromBigBlind(ANTE_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator],decimal) : 0;
		
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

	/**
	 * Id of blind structure
	 * @return return the id of the structure
	 */
	public long getIdBlindStructure() {
		return idBlindStructure;
	}
	/**
	 * Get the structure 
	 * @return LinkedList<BlindLevel> object represent a ordered list of blind levels
	 */
	public LinkedList<BlindLevel> getStructure() {
		return structure;
	}

}
