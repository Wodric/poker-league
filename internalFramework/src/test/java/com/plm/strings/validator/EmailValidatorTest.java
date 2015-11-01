package com.plm.strings.validator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.plm.strings.validator.EmailValidator;

import org.junit.runners.Parameterized;

/**
 * Email validator Testing
 * 
 * @author Alexandre Lefèvre "Wodric"
 * 
 */

@RunWith(value = Parameterized.class)
public class EmailValidatorTest {
	
	private String email;
	private boolean expected;

	public EmailValidatorTest(String email, boolean expected) {
		this.email = email;
		this.expected = expected;
	}

   @Parameters
   public static Collection<Object[]> emailProvider() {
      return Arrays.asList(new Object[][]{
    	  {"plm@yahoo.com",true},
    	  {"plm-100@yahoo.com",true},
    	  {"plm111@plm.com",true},
    	  {"plm-100@plm.net",true},
    	  {"plm.100@plm.com.au",true},
    	  {"plm@1.com",true},
    	  {"plm@gmail.com.com",true},
    	  {"plm+100@gmail.com",true},
    	  {"plm-100@yahoo-test.com",true},
    	  {"plm",false},
    	  {"plm@.com.my",false},
    	  {"plm123@gmail.a",false},
    	  {"plm123@.com",false},
    	  {"plm123@.com.com",false},
    	  {".plm@plm.com",false},
    	  {"plm()*@gmail.com",false},
    	  {"plm@%*.com",false},
    	  {"plm..2002@gmail.com",false},
    	  {"plm.@gmail.com",false},
    	  {"plm@plm@gmail.com",false},
    	  {"plm@gmail.com.1a",false}
      });
	 }

   @Test
	public void validateTest() {
		assertEquals(this.expected,EmailValidator.validate(this.email));

	}
}
