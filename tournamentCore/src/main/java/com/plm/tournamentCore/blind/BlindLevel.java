package com.plm.tournamentCore.blind;

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

	/**
	 * set small and big blind
	 */
	public void setBlinds(int pSmallBlind, int pBigBlind){
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
		this.ante = 0;
	}
	
	/**
	 * set small and big blind with ante
	 */
	public void setBlinds(int pSmallBlind, int pBigBlind, int pAnte){
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
		this.ante = pAnte;
	}

}
