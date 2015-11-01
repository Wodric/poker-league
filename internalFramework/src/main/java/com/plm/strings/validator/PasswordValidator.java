package com.plm.strings.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validate phone number standard pattern
 * @author Alexandre Lefèvre "Wodric'
 *
 */
public class PasswordValidator {

	/**
	 * Password pattern allorw password with :
	 *  8 character and more which contains at least 1 number [0-9], 
	 *  1 lowercase characters [a-z], 1 uppercase character [A-Z],
	 *  1 symbolics character ["!"#$%&'()*+,./:;<=>?@\^_`{|}~-]
	 */
	private static final String PASSWORD_PATTERN = 
              "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-]).{8,50})";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param pPasswordToValidate
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validate(String pPasswordToValidate) {
		Matcher matcher = Pattern.compile(PASSWORD_PATTERN).matcher(pPasswordToValidate);
		return matcher.matches();

	}
}