package com.plm.tournamentCore.blind;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	// The number of player expected 
    @Max(MAX_NUMBER_PLAYER)
    @Min(MIN_NUMBER_PLAYER)
    @NotNull(message="Can't be empty,set a numeric value")
	private int maxPlayerNumber;
	
	// the initial size of player stack
    @Max(MAX_INITIAL_STACK_SIZE)
    @Min(MIN_INITIAL_STACK_SIZE)
    @NotNull(message="Can't be empty,set a numeric value")
    private int initialStackSize;
	
    // the duration of the tournament you expect
    @Max(MAX_TOURNAMENT_DURATION)
    @Min(MIN_TOURNAMENT_DURATION)
    @NotNull(message="Can't be empty,set a numeric value")
    private int tournamentDurationExpected;
	
    //duration of level
    @Max(value=MAX_LEVEL_DURATION, message="<= 600 min svp")
    @Min(value=MIN_LEVEL_DURATION, message=">= 5 min please")
    @NotNull(message="Can't be empty,set a numeric value")
    private int levelDurations;
	
    // the startup big blind. MUST BE A MULTIPLE OF SMALLEST CHIP
	private BlindLevel minimumBlind;
	
	// allow ante in tournament if true
	private boolean withAnte;
	
	//the list of chip available in tournament
	private ChipsSet chipSet;
	
	
	/**
	 * the default value of has ante
	 */
	public static final boolean DEFAULT_WITH_DURATION = false;
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
	public int getLevelDurations() {
		return this.levelDurations;
	}
	
	/**
	 * 
	 * @param pLevelDurations set the new level durations
	 */
	public void setLevelDurations(int pLevelDurations) {
		this.levelDurations = pLevelDurations;
	}
	
	/**
	 * 
	 * @return the minimum blind
	 */
	public BlindLevel getMinimumBlind() {
		return minimumBlind;
	}
	
	/**
	 * 
	 * @param pMinimumBlind set the new minimum blind
	 */
	public void setMinimumBlind(BlindLevel pMinimumBlind) {
		this.minimumBlind = pMinimumBlind;
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
	
	
}
