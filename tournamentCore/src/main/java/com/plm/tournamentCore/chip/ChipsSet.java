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
}
