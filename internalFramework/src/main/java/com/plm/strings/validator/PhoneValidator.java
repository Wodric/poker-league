package com.plm.strings.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validate phone number standard pattern
 * @author Alexandre Lefèvre "Wodric'
 *
 */
public class PhoneValidator {

	private static final String PHONE_PATTERN = 
		"^[0-9]{5,15}$";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param pPhoneToValidate
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validate(String pPhoneToValidate) {

		Matcher matcher = Pattern.compile(PHONE_PATTERN).matcher(pPhoneToValidate);
		return matcher.matches();

	}
}
