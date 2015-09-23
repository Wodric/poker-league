package com.plm.vaadin;

/**
 * Util class to manage vaadin field conversion
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class FieldConvertorUtils {
	/**
	 * The java space char
	 */
	public static final String JAVA_SPACE_CHAR = "\\p{javaSpaceChar}";
	
	/**
	 * Separator for thousand,millions ....
	 */
	public static final String NUMBER_SEPARATOR = ",";
	
	/**
	 * This method convert the string from a field to a number
	 * @param pFieldValue the string value from field, must represent integer number 
	 * @return parameter value convert as an Integer
	 * @throws NumberFormatException in case the string as not anticipated or not number characters 
	 */
	public static int convertNumericFieldToInt(String pFieldValue){
		String transformedString = pFieldValue;
		transformedString= transformedString.replaceAll(JAVA_SPACE_CHAR,"");
		if(transformedString.contains(NUMBER_SEPARATOR)){
			transformedString = transformedString.replaceAll(NUMBER_SEPARATOR,"");
		}
		return Integer.valueOf(transformedString);
	}
	
	/**
	 * This method convert the string from a field to a number
	 * @param pFieldValue the string value from field, must represent integer number 
	 * @return parameter value convert as an Long
	 * @throws NumberFormatException in case the string as not anticipated or not number characters 
	 */
	public static long convertNumericFieldToLong(String pFieldValue){
		String transformedString = pFieldValue;
		transformedString= transformedString.replaceAll(JAVA_SPACE_CHAR,"");
		if(transformedString.contains(NUMBER_SEPARATOR)){
			transformedString = transformedString.replaceAll(NUMBER_SEPARATOR,"");
		}
		return Long.valueOf(transformedString);
	}

}
