package com.plm.strings.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Name validator Testing
 * 
 * @author Alexandre Lefèvre "Wodric"
 * 
 */

@RunWith(value = Parameterized.class)
public class NameValidatorTest {
	
	private String name;
	private boolean expected;

	public NameValidatorTest(String name, boolean expected) {
		this.name = name;
		this.expected = expected;
	}

   @Parameters
   public static Collection<Object[]> nameProvider() {
      return Arrays.asList(new Object[][]{
    	  {"test",true},
    	  {"lefèvre",true},
    	  {"éèàâaä",true},
    	  {"Añ",true},
    	  {"",false},
    	  {"azertyuiopazertyuiopazertyuiopazertyuiopazertyuiopp",false},
    	  {"132456",false},
    	  {"azert1yuiop",false}
      });
	 }

   @Test
	public void validateTest() {
		assertEquals(this.expected,NameValidator.validate(this.name));
	}
}
