package com.plm.tournamentCore.blind;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlindLevelTest {

	@Test
	public void setBlindsAutomaticsTest() {
		int bigBlind = 150;
		int ante = 25;
		BlindLevel level = new BlindLevel(0);
		level.setBlindsAutomatics(bigBlind, ante);
		
		assertEquals(bigBlind,level.getBigBlind());
		assertEquals(bigBlind/2,level.getSmallBlind());
		assertEquals(ante,level.getAnte());
		
	}

}
