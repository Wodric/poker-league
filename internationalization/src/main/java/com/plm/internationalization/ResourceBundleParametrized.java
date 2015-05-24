package com.plm.internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * Ressource bundle with parametrized messages. 
 * @author Alexandre Lefèvre "Wodric"
 */
public class ResourceBundleParametrized {
	
	/**
	 * Bundle we will get the messages
	 */
	private final ResourceBundle bundle;
	
	/**
	 * the default message language is English. this string contains 
	 * ISO 639 alpha-2 code for default language
	 */
	public static String DEFAULT_MESSAGE_LANGUAGE = "en";

	/**
	 * the default message country is England. this string contains 
	 * ISO 3166 alpha-2 code for default language
	 */
	public static String DEFAULT_MESSAGE_COUNTRY = "EN";

	/**
	 * This constructor build the final ResourceBundle of the class from the file name and the local
	 * @param pBaseName base name of the file to use
	 * @param pLocale
	 */
	public ResourceBundleParametrized(String pBaseName, Locale pLocale){
		ResourceBundle tempBundle = null;
		try{
			tempBundle = ResourceBundle.getBundle(pBaseName,pLocale);
		}
		catch(MissingResourceException e){
			// Test with default language before throwing exception
			try{
				tempBundle = ResourceBundle.getBundle(pBaseName,new Locale("en", "EN"));
			}
			catch(MissingResourceException e1){
				//String Message = new ResourceBundleParametrized();
				//throw new MissingResourceException();
				System.out.println("error");
			}
		}
		this.bundle = tempBundle;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
    public String getMessage(String key) {
        try {
            return this.bundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
    
    /**
     * 
     * @param key
     * @param params
     * @return
     */
    public String getString(String key, Object... params  ) {
        try {
            return MessageFormat.format(this.bundle.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
    
}
