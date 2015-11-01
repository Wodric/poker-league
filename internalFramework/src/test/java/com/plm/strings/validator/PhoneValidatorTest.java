package com.plm.strings.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.plm.strings.validator.PhoneValidator;

import org.junit.runners.Parameterized;

/**
 * Phone number validator Testing
 * 
 * @author Alexandre Lefèvre "Wodric"
 * 
 */

@RunWith(value = Parameterized.class)
public class PhoneValidatorTest {
	
	private String number;
	private boolean expected;

	public PhoneValidatorTest(String number, boolean expected) {
		this.number = number;
		this.expected = expected;
	}

   @Parameters
   public static Collection<Object[]> phoneProvider() {
      return Arrays.asList(new Object[][]{
    	  {"24561354",true},
    	  {"55555",true},
    	  {"151515151515151",true},
    	  {"1234",false},
    	  {"1616161616161616",false},
    	  {"1azfv546",false},
    	  {"azeg",false},
      });
	 }

   @Test
	public void validateTest() {
		assertEquals(this.expected,PhoneValidator.validate(this.number));

	}
}
