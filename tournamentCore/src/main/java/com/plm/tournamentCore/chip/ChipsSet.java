package com.plm.tournamentCore.chip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This object represent a chip set. In poker the chips is the money used to bet.
 * Chips set is chip of different value to avoid that player has too much chip and make bet/count/play easier.
 * @author Alexandre Lefevre
 *
 */
public class ChipsSet {
	
	/**
	 * The chipSet is a chip list ordered per chip value
	 */
	private List<Chip> chipsList;

	/**
	 * Create a chip set from the chip it will contains
	 * @param pChipsList List<Integer> containing the list of chip value
	 */
	public ChipsSet(List<Chip> pChipsList) {
		super();
		Collections.sort(pChipsList);
		this.chipsList = pChipsList;
	}
	
	/**
	 * Create a chip set from the value of chip it will contains
	 * @param pChipsValuesList ArrayList<Integer> containing the list of chip value
	 */
	public ChipsSet(ArrayList<Integer> pChipsValuesList) {
		super();
		Collections.sort(pChipsValuesList);
		this.chipsList = new ArrayList<Chip>();
		for(Integer aChipValue : pChipsValuesList){
			this.chipsList.add(new Chip(aChipValue));
		}
	}

	/**
	 * Get the list of chip on the chip set
	 * @return List<Chip>  which constitute the chip set
	 */
	public List<Chip> getChipsList() {
		return chipsList;
	}

	/**
	 * New chips set values
	 * @param chipsList new chip
	 */
	public void setChipsList(List<Chip> pChipsList) {
		Collections.sort(pChipsList);
		this.chipsList = pChipsList;
	}
	
	/**
	 * New chips set values
	 * @param chipsList new chip
	 */
	public void setChipsListIntegers(List<Integer> pChipsValuesList) {
		Collections.sort(pChipsValuesList);
		this.chipsList = new ArrayList<Chip>();
		for(Integer aChipValue : pChipsValuesList){
			this.chipsList.add(new Chip(aChipValue));
		}
	}
	
	/**
	 * Get the default chip sets from database
	 * TODO Get from database 
	 * @return these default chips set
	 */
	public static List<ChipsSet> getDefaultChipsSets(){
		List<ChipsSet> defaultSets = new ArrayList<ChipsSet>();
		
		ArrayList<Integer> chipsValue = new ArrayList<Integer>();
		chipsValue.add(25);
		chipsValue.add(100);
		chipsValue.add(500);
		chipsValue.add(1000);
		chipsValue.add(5000);
		chipsValue.add(25000);
		chipsValue.add(100000);
		ChipsSet chipsSet1 = new ChipsSet(chipsValue);
		
		chipsValue = new ArrayList<Integer>();
		chipsValue.add(25);
		chipsValue.add(100);
		chipsValue.add(500);
		chipsValue.add(1000);
		chipsValue.add(5000);
		chipsValue.add(10000);
		chipsValue.add(50000);
		ChipsSet chipsSet2 = new ChipsSet(chipsValue);
		
		chipsValue = new ArrayList<Integer>();
		chipsValue.add(5);
		chipsValue.add(25);
		chipsValue.add(100);
		chipsValue.add(500);
		chipsValue.add(1000);
		ChipsSet chipsSet3 = new ChipsSet(chipsValue);
		
		chipsValue = new ArrayList<Integer>();
		chipsValue.add(1);
		chipsValue.add(5);
		chipsValue.add(25);
		chipsValue.add(100);
		chipsValue.add(500);
		ChipsSet chipsSet4 = new ChipsSet(chipsValue);
		
		
		
		defaultSets.add(chipsSet1);
		defaultSets.add(chipsSet2);
		defaultSets.add(chipsSet3);
		defaultSets.add(chipsSet4);
		
		return defaultSets;
	}
	
	
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		StringBuilder toStringBuilder = new StringBuilder(); 
		if(chipsList != null && chipsList.size() != 0){
			int index = 0;
			for(Chip aChip: chipsList){
				toStringBuilder.append(aChip.getValue());
				if(index != (chipsList.size()-1)){
					toStringBuilder.append("-");
				}
				index++;
			}
		}
		return toStringBuilder.toString();
	}
	
}
