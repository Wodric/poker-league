package com.plm.tournament.structures.blinds.beans;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.plm.tournamentCore.blind.BlindLevel;
import com.plm.tournamentCore.chip.ChipsSet;

/**
 * Object which represent the blind structure parameters. It permit to bind UI and filter the value via validator
 * @author Alexandre Lefèvre "Wodric"
 */
public class BlindStructureParameters implements Serializable{

	/**
	 * Generated UID for serialization
	 */
	private static final long serialVersionUID = 8651671332107378426L;

	/**
	 *  The number of player expected with constrains validation
	 */
    @Max(value=MAX_NUMBER_PLAYER, message="Tournament must have between 2 and 50 000 players")
    @Min(value=MIN_NUMBER_PLAYER, message="Tournament must have between 2 and 50 000 players")
    @NotNull(message="Can't be empty,set a numeric value")
	private int maxPlayerNumber;
    /**
     * Parameter name for max player number
     */
    public static String PARAMETER_NAME_MAX_PLAYER_NUMBER = "maxPlayerNumber";
	
	/**
	 *  the initial size of player stack with constrains validation
	 */
    @Max(value=MAX_INITIAL_STACK_SIZE, message="Stack size must be between 1 and 50 000 000 chips")
    @Min(value=MIN_INITIAL_STACK_SIZE, message="Stack size must be between 1 and 50 000 000 chips")
    @NotNull(message="Can't be empty,set a numeric value")
    private int initialStackSize;
    /**
     * Parameter name for initial stack size
     */
    public static String PARAMETER_NAME_INITIAL_STACK_SIZE = "initialStackSize";
	
    /**
     *  the duration of the tournament you expect with constrains validation
     */
    @Max(value=MAX_TOURNAMENT_DURATION, message="Tournament duration must last between 30 and 60 000 minutes")
    @Min(value=MIN_TOURNAMENT_DURATION, message="Tournament duration must last between 30 and 60 000 minutes")
    @NotNull(message="Can't be empty,set a numeric value")
    private int tournamentDurationExpected;
    /**
     * Parameter name for tournament duration expected
     */
    public static String PARAMETER_NAME_TOURNAMENT_DURATION_EXPECTED = "tournamentDurationExpected";
	
    /**
     * duration of level with constrains validation
     */
    @Max(value=MAX_LEVEL_DURATION, message="Level duration must last between 5 and 600 minutes")
    @Min(value=MIN_LEVEL_DURATION, message="Level duration must last between 5 and 600 minutes")
    @NotNull(message="Can't be empty,set a numeric value")
    private int levelDuration;
    /**
     * Parameter name for level duration
     */
    public static String PARAMETER_NAME_LEVEL_DURATION = "levelDuration";
    
    /**
     *  the startup big blind. MUST BE A MULTIPLE OF SMALLEST CHIP
     */
    @Min(value=MIN_SMALL_BLIND_VALUE,message="Minimum small blind value between 1 and 5 000 000")
    @Max(value=MAX_SMALL_BLIND_VALUE,message="Minimum small blind value between 1 and 5 000 000")
	private int minimumSmallBlindValue;


	/**
     * Parameter name for level duration
     */
    public static String PARAMETER_NAME_MINIMUM_SMALL_BLIND_VALUE = "minimumSmallBlindValue";
	
	/**
	 *  allow ante in tournament if true
	 */
	private boolean withAnte;
    /**
     * Parameter name for with ante
     */
    public static String PARAMETER_NAME_WITH_ANTE = "withAnte";
	
	/**
	 * the list of chip available in tournament
	 */
	private ChipsSet chipSet;
    /**
     * Parameter name for with ante
     */
    public static String PARAMETER_NAME_CHIP_SET = "chipSet";
	
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
	
	/**
	 * 
	 * @return the number of player number
	 */
    public int getMaxPlayerNumber() {
		return this.maxPlayerNumber;
	}

	/**
     * 
     * @param pMaxPlayerNumber the new number of player to set
     */
	public void setMaxPlayerNumber(int pMaxPlayerNumber) {
		this.maxPlayerNumber = pMaxPlayerNumber;
	}
	
	/**
	 * 
	 * @return the initial stack size
	 */
	public int getInitialStackSize() {
		return this.initialStackSize;
	}
	
	/**
	 * 
	 * @param pInitialStackSize the new stack size
	 */
	public void setInitialStackSize(int pInitialStackSize) {
		this.initialStackSize = pInitialStackSize;
	}
	
	/**
	 * 
	 * @return get the tournament duration
	 */
	public int getTournamentDurationExpected() {
		return this.tournamentDurationExpected;
	}
	
	/**
	 * 
	 * @param pTournamentDurationExpected set the new tournament duration
	 */
	public void setTournamentDurationExpected(int pTournamentDurationExpected) {
		this.tournamentDurationExpected = pTournamentDurationExpected;
	}
	
	/**
	 * 
	 * @return the level duration
	 */
	public int getLevelDuration() {
		return this.levelDuration;
	}
	
	/**
	 * 
	 * @param pLevelDurations set the new level durations
	 */
	public void setLevelDuration(int pLevelDuration) {
		this.levelDuration = pLevelDuration;
	}
	
	/**
	 * 
	 * @return true if the true will have ante
	 */
	public boolean isWithAnte() {
		return this.withAnte;
	}
	
	/**
	 * 
	 * @param pWithAnte the true if you want to have ante on structure
	 */
	public void setWithAnte(boolean pWithAnte) {
		this.withAnte = pWithAnte;
	}
	
	/**
	 * 
	 * @return get the chip set
	 */
	public ChipsSet getChipSet() {
		return this.chipSet;
	}
	
	/**
	 * 
	 * @param pChipSet set the new chip set
	 */
	public void setChipSet(ChipsSet pChipSet) {
		this.chipSet = pChipSet;
	}
	
	/**
	 * 
	 * @return the minimum small blind value
	 */
    public int getMinimumSmallBlindValue() {
		return minimumSmallBlindValue;
	}

    /**
     * 
     * @param pMinimumSmallBlindValue set this value as minimum small blind, maximum small blind will be calculate chips
     */
	public void setMinimumSmallBlindValue(int pMinimumSmallBlindValue) {
		this.minimumSmallBlindValue = pMinimumSmallBlindValue;
	}
}
