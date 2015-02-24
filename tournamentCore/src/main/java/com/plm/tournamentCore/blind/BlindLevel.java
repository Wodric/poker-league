package com.plm.tournamentCore.blind;

/**
 * A blind level, is a time in which the blind has a constant value. 
 * Blind evolve between each level but not during. A level can have ante. 
 * Ante is like a mandatory tax to pay each hand.
 * @author alexandre
 *
 */
public class BlindLevel {
	/**
	 * The duration of the blind level
	 */
	private int duration;
	
	/**
	 * Cost of small blind
	 */
	private int smallBlind;
	
	/**
	 * Cost of bing blind
	 */
	private int bigBlind;
	
	/**
	 * Cost of bing blind
	 */
	private int ante;
	
	/**
	 * Create a blind level
	 * @param duration level duration
	 * @param smallBlind cost pay for small blind
	 * @param bigBlind cost pay for  small blind
	 */
	public BlindLevel(short duration, int smallBlind, int bigBlind) {
		super();
		this.duration = duration;
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
		this.ante = 0;
	}
	
	public BlindLevel(int duration, int smallBlind, int bigBlind, int ante) {
		super();
		this.duration = duration;
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
		this.ante = ante;
	}
	
	/**
	 * set small and big blind
	 */
	public void setBlinds(int pSmallBlind, int pBigBlind){
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
	}
	
	/**
	 * set big blind ad small blind = bb/2 and ante = 0
	 */
	public void setBlindsAutomatics(int pBigBlind){
		this.setBlinds(pBigBlind/2,pBigBlind);
	}
	
	/**
	 * set small and big blind with ante
	 */
	public void setBlinds(int pSmallBlind, int pBigBlind, int pAnte){
		this.setBlinds(pSmallBlind,pBigBlind);
		this.ante = pAnte;
	}
	
	/**
	 * set big blind ad small blind = bb/2 and ante = 0
	 */
	public void setBlindsAutomatics(int pBigBlind,int pAnte){
		this.setBlinds(pBigBlind/2,pBigBlind);
		this.ante = 0;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int pDuration) {
		this.duration = pDuration;
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	public int getAnte() {
		return ante;
	}

	public void setAnte(int ante) {
		this.ante = ante;
	}

}
