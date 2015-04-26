package com.plm.tournamentCore.ChipSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Test
	public void setChipSetFromIntegerArrayListTest(){
		ArrayList<Integer> listChipValue = new ArrayList<Integer>();
		Integer lowestValue = 10;
		Integer middleValue = 25;
		Integer highValue = 100;
		
		ChipsSet aSet = new ChipsSet(listChipValue);
		assertEquals(listChipValue.size(),aSet.getChipsList().size());
		
		listChipValue.add(highValue);
		listChipValue.add(lowestValue);
		listChipValue.add(middleValue);
		
		aSet.setChipsListIntegers(listChipValue);
		assertEquals(listChipValue.size(),aSet.getChipsList().size());
		
		assertEquals(lowestValue,(Integer) aSet.getChipsList().get(0).getValue());
		assertEquals(middleValue,(Integer) aSet.getChipsList().get(1).getValue());
		assertEquals(highValue,(Integer) aSet.getChipsList().get(2).getValue());
	}
	
	@Test
	public void chipsSetConstructorStringTest(){
		List<ChipsSet> defaultsOnes = ChipsSet.getDefaultChipsSets();
		List<String> defaultsOnesStrings = defaultsOnes.stream()
				.map(one -> one.toString())
				.collect(Collectors.toList());
		
		int i = 0;
		// verify that each of default chipset are equals to itself using string constructor and to string method
		for(String oneString : defaultsOnesStrings){
			assertTrue(defaultsOnes.get(i).equals(new ChipsSet(oneString)));
			i++;
		}
		
		ArrayList<Integer> listChipValue = new ArrayList<Integer>();
		Integer lowestValue = 10;
		Integer middleValue = 27;
		Integer highValue = 100;
		
		listChipValue.add(highValue);
		listChipValue.add(lowestValue);
		listChipValue.add(middleValue);
		ChipsSet aSet = new ChipsSet(listChipValue);
		aSet.setChipsListIntegers(listChipValue);
		// Verify it work with spaces
		assertEquals(aSet, new ChipsSet("10-27-100"));
		assertEquals(aSet, new ChipsSet("10 - 27 - 100"));		
	}
	
	@Test (expected = NumberFormatException.class)
	public void chipsSetConstructorStringTestException(){
		ArrayList<Integer> listChipValue = new ArrayList<Integer>();
		Integer lowestValue = 10;
		Integer middleValue = 27;
		Integer highValue = 100;
		
		listChipValue.add(highValue);
		listChipValue.add(lowestValue);
		listChipValue.add(middleValue);
		ChipsSet aSet = new ChipsSet(listChipValue);
		aSet.setChipsListIntegers(listChipValue);
		// Verify it work with spaces

		assertEquals(aSet, new ChipsSet("10 - test - 100"));		
	}
	
	@Test
	public void chipsSetEqualsTest(){
		List<ChipsSet> defaultsOnes = ChipsSet.getDefaultChipsSets();
		// verify equals to himself
		assertTrue(defaultsOnes.get(0).equals(defaultsOnes.get(0)));
		
		// verify equals to copy of himself
		ArrayList<Chip> listChip = new ArrayList<Chip>();
		listChip.addAll(defaultsOnes.get(0).getChipsList());
		ChipsSet copy = new ChipsSet(listChip);
		assertTrue(defaultsOnes.get(0).equals(copy));
		
		// verify no chip equals
		ArrayList<Integer> listChipValue = new ArrayList<Integer>();
		Integer lowestValue = 10;
		Integer middleValue = 27;
		Integer highValue = 100;
		
		listChipValue.add(highValue);
		listChipValue.add(lowestValue);
		listChipValue.add(middleValue);
		ChipsSet aSet = new ChipsSet(listChipValue);
		aSet.setChipsListIntegers(listChipValue);
		
		assertFalse(defaultsOnes.get(0).equals(aSet));
		// erify not equals to other object
		assertFalse(defaultsOnes.get(0).equals(""));	
	}
	
}
