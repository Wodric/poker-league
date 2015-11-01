package com.plm.strings.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validate email adresse from email standard pattern
 * @author Alexandre Lefèvre "Wodric'
 *
 */
public class EmailValidator {

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param pMailToValidate
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validate(String pMailToValidate) {

		Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(pMailToValidate);
		return matcher.matches();

	}
}