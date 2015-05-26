package com.plm.tournamentCore.blind;

import java.io.Serializable;
import java.util.LinkedList;

import com.plm.tournamentCore.chip.Chip;
import com.plm.tournamentCore.chip.ChipsSet;

/**
 * The blind structure is an object representing the list of level of tournament. 
 * @author Alexandre Lefevre
 */
public class BlindStructure implements Serializable{	
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = -1688520467905029504L;

	/**
	 * id Of blind structure in database
	 */
	private long idBlindStructure;
	
	/**
	 * List of blind level which constitute the structure
	 */
	private LinkedList<BlindLevel> structure;
	
	/**
	 * boolean representing if the structure contains ante or not.
	 * default value : false
	 */
	private boolean ante = false;
	

	public BlindStructure(){
		super();
	}
	
	/**
	 * Build the structure from the information in parameter
	 * @param pMaxPlayerNumber int The number of player expected 
	 * @param pInitialStackSize int the initial size of player stack
	 * @param pTournamentDurationExpected int the duration of the tournament you expect
	 * @param pLevelDurations int duration of level
	 * @param pMinimumBlind int the startup big blind. MUST BE A MULTIPLE OF SMALLEST CHIP
	 * @param pWithAnte boolean allow ante in tournament if true
	 * @param pChipSet the list of chip available in tournament
	 */
	public BlindStructure(int pMaxPlayerNumber, int pInitialStackSize, int pTournamentDurationExpected, 
			int pLevelDurations, BlindLevel pMinimumBlind, boolean pWithAnte, ChipsSet pChipSet){
		this.recalculateStructure(pMaxPlayerNumber,pInitialStackSize,pTournamentDurationExpected,
				pLevelDurations,pMinimumBlind,pWithAnte, pChipSet);
	}
	
	/**
	 * Build the structure from the information in parameter
	 * @param pMaxPlayerNumber int The number of player expected 
	 * @param pInitialStackSize int the initial size of player stack
	 * @param pTournamentDurationExpected int the duration of the tournament you expect
	 * @param pLevelDurations int duration of level
	 * @param pMinimumBlind int the startup big blind. MUST BE A MULTIPLE OF SMALLEST CHIP
	 * @param pWithAnte boolean allow ante in tournament if true
	 * @param pChipSet the list of chip available in tournament
	 */
	public void recalculateStructure(int pMaxPlayerNumber, int pInitialStackSize, int pTournamentDurationExpected, 
			int pLevelDurations, BlindLevel pMinimumBlind, boolean pWithAnte, ChipsSet pChipSet){
		int totalChip = calculateTotalNumberOfChips(pMaxPlayerNumber,pInitialStackSize);
		BlindLevel maxLevelExpected = calculateBigBlindMax(totalChip, pWithAnte,pLevelDurations);
		int totalNumberOfLevel = calculateNumberOfLevels(pTournamentDurationExpected,pLevelDurations);
		this.setAnte(pWithAnte);
		
		//set first level of structure
		pMinimumBlind.setDuration(pLevelDurations);
		totalNumberOfLevel --;
		this.structure = new LinkedList<BlindLevel>();
		this.structure.add(pMinimumBlind);
		
		int blindFactor = maxLevelExpected.getBigBlind() / pMinimumBlind.getBigBlind(); 
		double theoricFactorBetweenLevels = Math.pow(blindFactor, 1/(double)totalNumberOfLevel)*0.95;
		
		BlindLevel currentBlindLevel = pMinimumBlind;
		BlindLevel lastBlindLevelModified = pMinimumBlind;
		// build structure until we are sur to use at least the lowest chips
		while( (currentBlindLevel.getBigBlind() / lastBlindLevelModified.getBigBlind()) < (BlindConstants.DECIMAL_FACTOR / 2)){
			BlindLevel nextBlindLevel= getNextBlindLevel(currentBlindLevel,theoricFactorBetweenLevels);
			if(!validateOrModifyNextLevel(nextBlindLevel,pChipSet.getChipsList().get(0))){
				lastBlindLevelModified = nextBlindLevel;
			}
			currentBlindLevel = nextBlindLevel;
			this.structure.add(currentBlindLevel);
			totalNumberOfLevel --;
			// to avoid heavy drift in blind calculation it is re calculate every 5 levels
			if(totalNumberOfLevel%5 == 0){
				blindFactor = maxLevelExpected.getBigBlind() / currentBlindLevel.getBigBlind(); 
				theoricFactorBetweenLevels = Math.pow(blindFactor, 1/(double)totalNumberOfLevel)*0.95;
			}
		}
		
		// recalculate  the factor after the first phase
		blindFactor = maxLevelExpected.getBigBlind() / currentBlindLevel.getBigBlind(); 
		// *0.93 factor to avoid that automatic round at superior blind accelerate the structure too much
		theoricFactorBetweenLevels = Math.pow(blindFactor, 1/(double)totalNumberOfLevel)*0.95;

		while( totalNumberOfLevel > (-BlindConstants.NUMBER_OF_ADDITIONAL_LEVEL)){
			BlindLevel nextBlindLevel= getNextBlindLevel(currentBlindLevel,theoricFactorBetweenLevels);
			currentBlindLevel = nextBlindLevel;
			this.structure.add(currentBlindLevel);
			totalNumberOfLevel --;
			// to avoid heavy drift in blind calculation it is re calculate every 5 levels
			if(totalNumberOfLevel%5 == 0 && totalNumberOfLevel > 4){
				blindFactor = maxLevelExpected.getBigBlind() / currentBlindLevel.getBigBlind(); 
				theoricFactorBetweenLevels = Math.pow(blindFactor, 1/(double)totalNumberOfLevel)*0.95;
			}
		}
	}


	/**
	 * This method define the next level from current and theorical factor between levels.
	 * It take level duration from level in parameter
	 * @param pCurrentLevel the BlindLevel object of current level
	 * @param pBlindFactorBetweenLevel  double the theorical factor between 2 levels
	 * @return BlindLevel the next blind level
	 */
	protected BlindLevel getNextBlindLevel (BlindLevel pCurrentLevel, double pBlindFactorBetweenLevel){
		int decade = 0 ;
		double blindFactor = pCurrentLevel.getBigBlind();
		while(blindFactor >= BlindConstants.DECIMAL_FACTOR){
			decade ++;
			blindFactor = blindFactor/BlindConstants.DECIMAL_FACTOR;
		}
		
		// Can't jump 2 or more decade in each blind level
		double theoricNewBlind = pBlindFactorBetweenLevel*blindFactor;
		// verify if the factor don't change the blind of decade
		if(theoricNewBlind >= BlindConstants.DECIMAL_FACTOR){
			decade ++;
			theoricNewBlind = theoricNewBlind/BlindConstants.DECIMAL_FACTOR;
		}
		
		int iterator = 0;
		boolean blindFound = false;

		while(!blindFound){

			if(iterator >= BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length){
				// case  8 < theoricNewBlind < 10, round to superior decade 
				blindFound = true;
				decade ++;
				iterator = 0;
			}
			else if(theoricNewBlind > BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[iterator]){
				iterator++;
			}
			else{
				blindFound = true;
			}
			
		}
		
		BlindLevel nextBlindLevel = new BlindLevel(pCurrentLevel.getDuration());
		nextBlindLevel.setBlindsAutomatics((int) (BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[iterator] 
				* Math.pow(BlindConstants.DECIMAL_FACTOR, decade)));
		nextBlindLevel.setAnte(calculateAnteFromBigBlind(BlindConstants.ANTE_AUTHORIZED_MULTIPLE_TAB[iterator],decade));
		
		return nextBlindLevel;
	}
	

	
	/**
	 * Validate or modify the level 
	 * @param pLevel current level to test. If the level is validate it don't change. 
	 * If it's not validate, it will be modify to be valid. In case we can't found adequate level in a decade, throws Exception
	 * @param pLowestChips the lowest chip in chips choice
	 * @throws IllegalArgumentException in case it don't found multiple in a decade
	 * @return true if the level is validate, false otherwise. 
	 */
	protected boolean validateOrModifyNextLevel(BlindLevel pLevel, Chip pLowestChips) 
			throws IllegalArgumentException{
		// verify if the small blind is a multiple of lowest chip
		if( (pLevel.getSmallBlind() % pLowestChips.getValue()) == 0 
				&& (pLevel.getAnte() % pLowestChips.getValue() == 0)){
			return true;
		}
		else{
			// calcul the next compatible blind with lowest chips
			double theoricalLevel = pLevel.getBigBlind();
			int decimal = 0;
			// get an authorized blind
			while(theoricalLevel >= BlindConstants.DECIMAL_FACTOR){
				decimal ++;
				theoricalLevel = theoricalLevel/BlindConstants.DECIMAL_FACTOR;
			}
			
			// get the multiple of the blind 
			int idMultiple = 0;
			while( theoricalLevel != BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[idMultiple] 
					&& idMultiple<BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length ){
				idMultiple ++;
			}
			
			// 1 to length to do not test the current multiple.
			boolean newMultipleFound = false;
			int iterator = 1;
			int currentMultipleId = idMultiple;
			int currentDecimal = decimal;
			while(iterator<BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length && !newMultipleFound){
				// if we arrive at the end of the authorized blind multiple need to start again with increased decimal
				currentMultipleId = ( iterator + idMultiple )  % BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length;
				currentDecimal = ( iterator + idMultiple ) >= BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length ? 
						decimal+1 : decimal;
				double smallBlindToTest = BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] 
						* Math.pow(BlindConstants.DECIMAL_FACTOR,currentDecimal);
				double anteToTest=0;
				if(currentDecimal >= BlindConstants.MINIMUM_BLIND_DECIMAL_FOR_ANTE){
					anteToTest = BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] 
							* Math.pow(BlindConstants.DECIMAL_FACTOR,currentDecimal-1);
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
			if(iterator >= BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length){
				throw new IllegalArgumentException("Can't find blind level multiple on the lowest chip value :" 
						+ pLowestChips.getValue() );
			}
			
			pLevel.setBlindsAutomatics((int) (BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[currentMultipleId] 
					* Math.pow(BlindConstants.DECIMAL_FACTOR, currentDecimal)));
			pLevel.setAnte(pLevel.getAnte() != 0? 
					calculateAnteFromBigBlind(BlindConstants.ANTE_AUTHORIZED_MULTIPLE_TAB[currentMultipleId],currentDecimal) : 0);
			
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
		double theoricalLevel = (double) pTotalChips / (double) BlindConstants.BLIND_AT_TOURNAMENT_END;
		int decimal = 0;
		// get an authorized blind
		while(theoricalLevel >= BlindConstants.DECIMAL_FACTOR){
			decimal ++;
			theoricalLevel = theoricalLevel/BlindConstants.DECIMAL_FACTOR;
		}
		
		boolean isAuthorizedMultibleFound = false;
		int authorizedMultipleIterator = 1; // theorical level must be 1 <= x <= 9.99
		
		while( !isAuthorizedMultibleFound 
				&& (authorizedMultipleIterator < BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length) ){
			if(theoricalLevel <= BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator]){
				isAuthorizedMultibleFound = true;
			}
			else{
				authorizedMultipleIterator++;
			}
		}
		
		// if we don't find a multiple in list 8 < theoricalLevel < 9.99  
		// round to first authorized multiple to next decade 
		if(authorizedMultipleIterator >=  BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length){
			authorizedMultipleIterator = 0;
			decimal ++;
		}
		
		int bigBlind = (int) (BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator] 
				* Math.pow(BlindConstants.DECIMAL_FACTOR, decimal));
		int smallBlind = bigBlind / 2;
		int ante = pWithAnte? calculateAnteFromBigBlind(
				BlindConstants.ANTE_AUTHORIZED_MULTIPLE_TAB[authorizedMultipleIterator],decimal) : 0;
		
		return new BlindLevel(pLevelDuration, smallBlind, bigBlind, ante);
	}
	
	/**
	 * Calculate the ante from the bigblind
	 * @param pBlindMultiple The blind multiple
	 * @param pBlindDecimal Number of decimal for the blind
	 * @return the ante size corresponding to the big blind. If pBlindDecimal < 3 return 0
	 */
	private int calculateAnteFromBigBlind(double pBlindMultiple, int pBlindDecimal){
		if(pBlindDecimal >= BlindConstants.MINIMUM_BLIND_DECIMAL_FOR_ANTE){
			return (int) (pBlindMultiple * Math.pow(BlindConstants.DECIMAL_FACTOR, (pBlindDecimal - 1)));
		}
		else{
			return 0; // if blind are small there is no blind
		}
	}
	
	/**
	 * return a blind structure build with the default settings 
	 * @return blind structure object
	 */
	public static BlindStructure getDefaultBlindStructure(){
		return new BlindStructure(
				BlindConstants.DEFAULT_NUMBER_PLAYER,
				BlindConstants.DEFAULT_INITIAL_STACK_SIZE,
				BlindConstants.DEFAULT_TOURNAMENT_DURATION,
				BlindConstants.DEFAULT_LEVEL_DURATION,
				BlindConstants.DEFAULT_BLIND_LEVEL,
				BlindConstants.DEFAULT_WITH_ANTE,
				BlindConstants.DEFAULT_CHIPSET);
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
	
	/**
	 * it return the parameters which define if the structure will contains ante
	 * @return ante value of ante if true the structure can have ante
	 */
	public boolean isAnte() {
		return ante;
	}

	/**
	 * set the ante value
	 * @param ante it set the ante value, if true the level in structure will have ante
	 */
	public BlindStructure setAnte(boolean ante) {
		this.ante = ante;
		return this;
	}

	/**
	 * enable and set the ante. Return the ante to set it in UI
	 * @return the list of ante in order of increasing blind
	 */
	public void recalculateAnteFromBlinds() {
		this.ante = true;
		for(BlindLevel aBlindLevel : structure){
			
			// calculate the number of decade
			int decade = 0 ;
			double blindFactor = aBlindLevel.getBigBlind();
			while(blindFactor >= BlindConstants.DECIMAL_FACTOR){
				decade ++;
				blindFactor = blindFactor/BlindConstants.DECIMAL_FACTOR;
			}

			int iterator = 0;
			boolean blindFound = false;
			// get the blind factor
			while(!blindFound){

				if(iterator >= BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB.length){
					// case  8 < theoricNewBlind < 10, round to superior decade 
					blindFound = true;
					decade ++;
					iterator = 0;
				}
				else if(blindFactor > BlindConstants.BLIND_AUTHORIZED_MULTIPLE_TAB[iterator]){
					iterator++;
				}
				else{
					blindFound = true;
				}
				
			}	
			// calculate ante from blind decade and factor
			int calculatedAnte = this.calculateAnteFromBigBlind(BlindConstants.ANTE_AUTHORIZED_MULTIPLE_TAB[iterator],decade);
			aBlindLevel.setAnte(calculatedAnte);
		}
	}
	
	/**
	 * disable and remove the ante value. Set all the ante to 0
	 */
	public void removeAnte() {
		this.ante = false;
		for(BlindLevel aBlindLevel : structure){
			aBlindLevel.setAnte(0);
		}	
	}

}
