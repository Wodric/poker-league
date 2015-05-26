package com.plm.tournamentCore.blind;

import java.io.Serializable;

/**
 * A blind level, is a time in which the blind has a constant value. 
 * Blind evolve between each level but not during. A level can have ante. 
 * Ante is like a mandatory tax to pay each hand.
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class BlindLevel implements Serializable {
	/**
	 * generated serial number
	 */
	private static final long serialVersionUID = 3747420269027589858L;

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
	 * Create a blind level with ante = 0
	 * @param pDuration level duration
	 * @param pSmallBlind cost pay for small blind
	 * @param pBigBlind cost pay for  big blind
	 */
	public BlindLevel(int pDuration, int pSmallBlind, int pBigBlind) {
		super();
		this.duration = pDuration;
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
		this.ante = 0;
	}
	
	/**
	 *  Create a blind level
	 * @param pDuration level duration
	 * @param pSmallBlind cost pay for small blind
	 * @param bigBlind cost pay for  big blind
	 * @param pAnte ante cost
	 */
	public BlindLevel(int pDuration, int pSmallBlind, int pBigBlind, int pAnte) {
		super();
		this.duration = pDuration;
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
		this.ante = pAnte;
	}
	
	/**
	 * create a BlindLevel object. You need to set the blind and ante after initilize with this method.
	 * @param pDuration The duration of the level
	 */
	public BlindLevel(int pDuration) {
		super();
		this.duration = pDuration;
	}

	/**
	 * set small and big blind
	 */
	public BlindLevel setBlinds(int pSmallBlind, int pBigBlind){
		this.smallBlind = pSmallBlind;
		this.bigBlind = pBigBlind;
		return this;
	}
	
	/**
	 * set big blind ad small blind = bb/2 and ante = 0
	 */
	public BlindLevel setBlindsAutomatics(int pBigBlind){
		this.setBlinds(pBigBlind/2,pBigBlind);
		return this;
	}
	
	/**
	 * set small and big blind with ante
	 */
	public BlindLevel setBlinds(int pSmallBlind, int pBigBlind, int pAnte){
		this.setBlinds(pSmallBlind,pBigBlind);
		this.ante = pAnte;
		return this;
	}
	
	/**
	 * set big blind ad small blind = bb/2 and ante = 0
	 */
	public BlindLevel setBlindsAutomatics(int pBigBlind,int pAnte){
		this.setBlinds(pBigBlind/2,pBigBlind);
		this.ante = pAnte;
		return this;
	}

	public int getDuration() {
		return duration;
	}

	public BlindLevel setDuration(int pDuration) {
		this.duration = pDuration;
		return this;
	}

	public int getSmallBlind() {
		return smallBlind;
	}

	public BlindLevel setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
		return this;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public BlindLevel setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
		return this;
	}

	public int getAnte() {
		return ante;
	}

	public BlindLevel setAnte(int ante) {
		this.ante = ante;
		return this;
	}

}
