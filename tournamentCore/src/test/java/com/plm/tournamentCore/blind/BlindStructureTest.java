package com.plm.tournamentCore.blind;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.plm.tournamentCore.chip.Chip;

public class BlindStructureTest {

	@Test
	public void calculateBigBlindMaxTestHighLimitWithoutAnte() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 600;
		// Test superior limit, blind level must be round to next decade
		// ante disable (0) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber-1, BlindStructure.WITHOUT_ANTE, 0); 
		// round to superior
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		//no ante expected
		assertEquals(0,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestWithoutAnteRound() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 1200;
		// Test blind level calculation, must be round to next level
		// ante disable (0) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber-1, BlindStructure.WITHOUT_ANTE, 0); 
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		//no ante expected
		assertEquals(0,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestWithoutAnteRoundNextLevel() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 1200;
		// Test blind level calculation, must be round to next level even if 1 chips more than limit
		// ante disable (0) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber+1, BlindStructure.WITHOUT_ANTE, 0); 
		// Round to next level of 10/20 => 12/24
		assertEquals(24, maxBlindLevel.getBigBlind());
		assertEquals(12, maxBlindLevel.getSmallBlind());
		//no ante expected
		assertEquals(0,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestExactWithoutAnte() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 1200;
		// Test blind level calculation, must me exact
		// ante disable (0) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber, BlindStructure.WITHOUT_ANTE, 0); 
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		//no ante expected
		assertEquals(0,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestHighLimitWithAnte() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 60000;
		// Test blind level calculation
		// ante enable (BB/10) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber-1, BlindStructure.WITH_ANTE, 0); 
		// round to superrior
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		assertEquals(chipsNumber/600,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestWithAnteRound() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 120000;
		// Test blind level calculation
		// ante enable (BB/10) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber-1, BlindStructure.WITH_ANTE, 0); 
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		assertEquals(chipsNumber/600,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestWithAnteRoundNextLevel() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 120000;
		// Test blind level calculation
		// ante enable (BB/10) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber+1, BlindStructure.WITH_ANTE, 0); 
		// Round to next level of 10/20/2 => 12/24/3
		assertEquals(2400, maxBlindLevel.getBigBlind());
		assertEquals(1200, maxBlindLevel.getSmallBlind());
		assertEquals(300,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestExactWithAnte() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 120000;
		// Test blind level calculation
		// ante enable (BB/10) 
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber, BlindStructure.WITH_ANTE, 0); 
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		assertEquals(chipsNumber/600,maxBlindLevel.getAnte());
	}
	
	@Test
	public void calculateBigBlindMaxTestExactWithAnteZero() {
		BlindStructure structure = new BlindStructure();
		int chipsNumber = 12000;
		// Test blind level calculation
		// ante enable (BB/10) ,  big blind must be at 1000
		BlindLevel maxBlindLevel = structure.calculateBigBlindMax(chipsNumber, BlindStructure.WITH_ANTE, 0); 
		assertEquals(chipsNumber/60, maxBlindLevel.getBigBlind());
		assertEquals(chipsNumber/120, maxBlindLevel.getSmallBlind());
		// Blind not high enough to set ante
		assertEquals(0,maxBlindLevel.getAnte());
	}
	
	@Test
	public void validateNextLevelTest(){
		short duration = 30;
		BlindLevel levelToValidate = new BlindLevel(duration, 25, 50);

		boolean isvalidate = new BlindStructure().validateOrModifyNextLevel(levelToValidate, new Chip(25));
		assertEquals(true, isvalidate);
		
		levelToValidate = new BlindLevel(duration, 75, 150);

		isvalidate = new BlindStructure().validateOrModifyNextLevel(levelToValidate, new Chip(25));
		assertEquals(true, isvalidate);
	}
	
	@Test
	public void modifyNextLevelTest(){
		short duration = 30;
		BlindLevel levelToValidate = new BlindLevel(duration, 30, 60);
		// without ante, minimum chip 25 and level 30/60
		boolean isvalidate = new BlindStructure().validateOrModifyNextLevel(levelToValidate, new Chip(25));
		assertEquals(false, isvalidate);
		//level calculated must be 50/100 ante 0
		assertEquals(100,levelToValidate.getBigBlind());
		assertEquals(50,levelToValidate.getSmallBlind());
		assertEquals(0,levelToValidate.getAnte());
		assertEquals(duration,levelToValidate.getDuration());
		
		
		levelToValidate = new BlindLevel(duration, 1000, 2000, 200);
		// with ante, minimum chip 500 and level 1000/2000
		isvalidate = new BlindStructure().validateOrModifyNextLevel(levelToValidate, new Chip(500));
		assertEquals(false, isvalidate);
		//level calculated must be 2500/5000 ante 500
		assertEquals(5000,levelToValidate.getBigBlind());
		assertEquals(2500,levelToValidate.getSmallBlind());
		assertEquals(500,levelToValidate.getAnte());
		assertEquals(duration,levelToValidate.getDuration());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void modifyNextLevelTestNoResultCompatible(){
		short duration = 30;
		BlindLevel levelToValidate = new BlindLevel((short)duration, 30, 60);
		// no result possible throw IllegalArgumentException
		new BlindStructure().validateOrModifyNextLevel(levelToValidate, new Chip(27));
	}
	

}
