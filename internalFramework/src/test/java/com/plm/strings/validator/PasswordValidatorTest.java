package com.plm.strings.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Password validator Testing
 * 
 * @author Alexandre Lefèvre "Wodric"
 * 
 */

@RunWith(value = Parameterized.class)
public class PasswordValidatorTest {
	
	private String password;
	private boolean expected;

	public PasswordValidatorTest(String password, boolean expected) {
		this.password = password;
		this.expected = expected;
	}

   @Parameters
   public static Collection<Object[]> passwordProvider() {
      return Arrays.asList(new Object[][]{
    	  {"plmanager1A@",true},
    	  {"&plMOn12$",true},
    	  {"AlE12!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-", true},
    	  {"mY1A@",false},
    	  {"manage12@",false},
    	  {"azertyuiopazeA1@uiopazertyuiopazertyuiopazertyuiopp",false},
    	  {"mkyonG$$",false},
    	  {"POKER12$",false}
      });
	 }

   @Test
	public void validateTest() {
	   assertEquals(this.expected,PasswordValidator.validate(this.password));
	}
}