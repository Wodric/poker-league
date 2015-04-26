package com.plm.tournamentCore.ChipSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.plm.tournamentCore.chip.Chip;

public class ChipTest {

	@Test
	public void compareTest() {
		int value = 25;
		Chip aChip = new Chip(value);
		
		//  aChip > Chip => 1
		assertEquals(1, aChip.compareTo(new Chip(value/2)));
		//  aChip < Chip => -1
		assertEquals(-1,aChip.compareTo(new Chip(value*2)));
		//  aChip == Chip => 0
		assertEquals(0,aChip.compareTo(new Chip(value)));
	}
	
	@Test
	public void equalsTest() {
		int value = 25;
		Chip aChip = new Chip(value);
		
		assertFalse(aChip.equals(new Chip("0")));
		assertFalse(aChip.equals(new Chip(value*2)));
		assertTrue(aChip.equals(new Chip(value)));
		assertTrue(aChip.equals(aChip));
		assertFalse(aChip.equals(""));	
	}

}
