package com.plm.strings.validator;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validate name standard pattern
 * @author Alexandre Lefèvre "Wodric'
 *
 */
public class NameValidator {

	private static final String NAME_PATTERN = 
		"^[a-zA-Z]{1,50}$";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param pNameToValidate
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validate(String pNameToValidate) {
		String nameValidateNoAccentuate = pNameToValidate == null ? null :
		        Normalizer.normalize(pNameToValidate, Form.NFD)
		            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		Matcher matcher = Pattern.compile(NAME_PATTERN).matcher(nameValidateNoAccentuate);
		return matcher.matches();
	}
}
