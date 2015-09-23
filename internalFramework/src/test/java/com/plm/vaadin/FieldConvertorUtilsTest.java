package com.plm.vaadin;

import org.junit.Test;
import static org.junit.Assert.*;

public class FieldConvertorUtilsTest {
	
	@Test
	public void convertNumericFieldToIntTest(){
		int theoricResult = 15000;
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToInt("15,000"));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToInt("15 000"));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToInt("15   000  "));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToInt("15  , 000  "));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToInt("15000"));
	}
	
	@Test
	public void convertNumericFieldToLongTest(){
		long theoricResult = 15000;
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToLong("15,000"));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToLong("15 000"));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToLong("15   000  "));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToLong("15  , 000  "));
		assertEquals(theoricResult,FieldConvertorUtils.convertNumericFieldToLong("15000"));
	}
}
