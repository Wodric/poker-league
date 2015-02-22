package com.plm.tournamentCore.chip;

/**
 * This object represent a chip. In poker the chips is the money used to bet.
 * @author Alexandre Lefèvre aka Wodric
 *
 */
public class Chip implements Comparable<Chip>{
	
	/**
	 * The value of the chip
	 */
	private int value;

	/**
	 * COnstructor with chip value as parameter. 
	 * @param pValue the value to set to the chip
	 */
	public Chip(int pValue) {
		super();
		this.value = pValue;
	}
	
	/**
	 * Return the value of the chip
	 * @return integer corresponding to the value of the chip
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the value or replace the value of the chip
	 * @param pValue integer representing the value of the chip
	 */
	public void setValue(int pValue) {
		this.value = pValue;
	}

	/**
	 * Implementation of comparable interface
	 * @param anotherChip the chip to compare with current object
	 * @return a negative integer, zero, or a positive integer as this chip value is less than, equal to, or greater than the specified object.
	 */
	public int compareTo(Chip anotherChip) {
		if(this.getValue() > anotherChip.getValue()){
			return 1;
		}
		else if(this.getValue() == anotherChip.getValue()){
			return 0;
		}
		else{
			return -1; 
		}
	}
}
