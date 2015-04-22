package com.plm.tournamentCore.ChipSet;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.plm.tournamentCore.chip.Chip;
import com.plm.tournamentCore.chip.ChipsSet;

public class ChipsSetTest {
	
	@Test	
	public void chipsSetConstrcutorIntegerTest() {
		int lowestValue = 10;
		int middleValue = 25;
		int highValue = 100;
		
		ArrayList<Integer> listOfChips = new ArrayList<Integer>();
		listOfChips.add(highValue);
		listOfChips.add(middleValue);
		listOfChips.add(lowestValue);
		// creater liste of chip with value 100,25,10
		ChipsSet chipsSet = new ChipsSet(listOfChips);
		List<Chip> orderedChip = chipsSet.getChipsList();
		// object must be ordered, and must contains 10,25,100
		assertEquals(lowestValue,orderedChip.get(0).getValue());
		assertEquals(middleValue,orderedChip.get(1).getValue());
		assertEquals(highValue,orderedChip.get(2).getValue());
	}
	
	@Test	
	public void chipsSetConstructorChipTest() {
		int lowestValue = 10;
		int middleValue = 25;
		int highValue = 100;
		
		List<Chip> listOfChips = new ArrayList<Chip>();
		listOfChips.add(new Chip(highValue));
		listOfChips.add(new Chip(middleValue));
		listOfChips.add(new Chip(lowestValue));
		// creater liste of chip with value 100,25,10
		ChipsSet chipsSet = new ChipsSet(listOfChips);
		List<Chip> orderedChip = chipsSet.getChipsList();
		// object must be ordered, and must contains 10,25,100
		assertEquals(lowestValue,orderedChip.get(0).getValue());
		assertEquals(middleValue,orderedChip.get(1).getValue());
		assertEquals(highValue,orderedChip.get(2).getValue());
	}
	
	@Test	
	public void chipsSetSetChipsSetTest() {
		int lowestValue = 10;
		int middleValue = 25;
		int highValue = 100;
		
		List<Chip> listOfChipsEmpty = new ArrayList<Chip>();
		// init with empty array
		ChipsSet chipsSet = new ChipsSet(listOfChipsEmpty);
		
		
		List<Chip> listOfChips = new ArrayList<Chip>();
		listOfChips.add(new Chip(highValue));
		listOfChips.add(new Chip(middleValue));
		listOfChips.add(new Chip(lowestValue));
		chipsSet.setChipsList(listOfChips);
		// creater liste of chip with value 100,25,10
		
		
		List<Chip> orderedChip = chipsSet.getChipsList();
		// object must be ordered, and must contains 10,25,100
		assertEquals(lowestValue,orderedChip.get(0).getValue());
		assertEquals(middleValue,orderedChip.get(1).getValue());
		assertEquals(highValue,orderedChip.get(2).getValue());
	}
	
	@Test (expected = NullPointerException.class)
	public void chipsSetConstructorWithNullListTest() {
		int lowestValue = 10;
		int middleValue = 25;
		int highValue = 100;
		
		List<Chip> listOfChipsEmpty = null;
		// init with empty array
		ChipsSet chipsSet = new ChipsSet(listOfChipsEmpty);
		
		
		List<Chip> listOfChips = new ArrayList<Chip>();
		listOfChips.add(new Chip(highValue));
		listOfChips.add(new Chip(middleValue));
		listOfChips.add(new Chip(lowestValue));
		chipsSet.setChipsList(listOfChips);
		// creater liste of chip with value 100,25,10
		
		
		List<Chip> orderedChip = chipsSet.getChipsList();
		// object must be ordered, and must contains 10,25,100
		assertEquals(lowestValue,orderedChip.get(0).getValue());
		assertEquals(middleValue,orderedChip.get(1).getValue());
		assertEquals(highValue,orderedChip.get(2).getValue());
	}
	
	@Test
	public void chipSetToString(){
		int lowestValue = 10;
		int middleValue = 25;
		int highValue = 100;
		
		String toStringExpected = "10-25-100";
		
		List<Chip> listOfChipsEmpty = new ArrayList<Chip>();
		// init with empty array
		ChipsSet chipsSet = new ChipsSet(listOfChipsEmpty);
		
		
		List<Chip> listOfChips = new ArrayList<Chip>();
		listOfChips.add(new Chip(highValue));
		listOfChips.add(new Chip(middleValue));
		listOfChips.add(new Chip(lowestValue));
		chipsSet.setChipsList(listOfChips);
		
		assertEquals(toStringExpected, chipsSet.toString());
		
		listOfChips = new ArrayList<Chip>();
		listOfChips.add(new Chip(highValue));
		chipsSet.setChipsList(listOfChips);
		toStringExpected = "100";
		
		assertEquals(toStringExpected, chipsSet.toString());
		
		chipsSet.setChipsList(new ArrayList<Chip>());
		toStringExpected = "";
		assertEquals(toStringExpected, chipsSet.toString());
	}
}
