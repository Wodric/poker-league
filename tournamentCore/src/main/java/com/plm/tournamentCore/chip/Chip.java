package com.plm.tournamentCore.chip;

/**
 * This object represent a chip. In poker the chips is the money used to bet.
 * @author Alexandre Lefevre
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
	 * COnstructor with chip value as parameter. 
	 * @param pValue the value to set to the chip as String
	 */
	public Chip(String pValue) {
		super();
		this.value = Integer.valueOf(pValue);
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
	public Chip setValue(int pValue) {
		this.value = pValue;
		return this;
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
	
	@Override
	public boolean equals(Object aChip){
		if( this == aChip) return true;
		if ( !(aChip instanceof Chip) ) return false;
		Chip chipSet = (Chip) aChip;
		if(this.value == chipSet.getValue()) return true;
		return false;
	}
}
