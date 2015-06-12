package com.plm.internationalization;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *  parametrized Ressource bundle must be used like ressource bundle
 * The parameters will replace the pattern "{X}" where X is a number. The pattern is 0-based
 * The X it's the index of the parameters you set in method.
 * The first parameter will replace the {0} pattern if it exists. 
 * If there is not enought parameters, the pattern will not be replace
 * @author Alexandre Lefèvre "Wodric"
 */
public class ParametrizedResourceBundle extends ResourceBundle{
	
	private static Logger logger = LoggerFactory.getLogger(ParametrizedResourceBundle.class);
	
	/**
	 * Bundle we will get the messages
	 */
	private final ResourceBundle bundle;
	
	/**
	 * the default message language is English. this string contains 
	 * ISO 639 alpha-2 code for default language
	 */
	public static final String DEFAULT_MESSAGE_LANGUAGE = "en";

	/**
	 * This constructor build the final ResourceBundle of the class from the file name and the local
	 * 
	 */
	private ParametrizedResourceBundle(ResourceBundle pBundle){
		this.bundle = pBundle;
	}
	
	/**
	 * Get the parametrized bundle to getm essage on it
	 * @param pBaseName base name of the file to use
	 * @param pLocale Localization of user. Must contains at least the language code from ISO 639 alpha-2.  
	 * @return  the parametrized bundle to getm essage on it
	 */
	public static ParametrizedResourceBundle getParametrizedBundle(String pBaseName, Locale pLocale){
		ResourceBundle tempBundle = null;
		try{
			tempBundle = ResourceBundle.getBundle(pBaseName,pLocale);
		}
		catch(MissingResourceException e){
			// Test with default language before throwing exception
			try{
				tempBundle = ResourceBundle.getBundle(pBaseName,new Locale("en"));
			}
			catch(MissingResourceException e1){
				// Must be a base name error or local not supported
				logger.error(e1.getMessage());
				throw new MissingResourceException("Base name or local not supported",
						ParametrizedResourceBundle.class.getName(),pBaseName);
			}
		}
		return new ParametrizedResourceBundle(tempBundle);
	}
	
	/**
	 * This method return a message. Don't replace the pattern for parametrized message.
	 * @param key the message key on properties file
	 * @return the message
	 */
    public String getMessage(String key) {
        try {
            return this.bundle.getString(key);
        } catch (MissingResourceException e) {
        	logger.error("Can't find message with key'" + key + "'");
            return '!' + key + '!';
        }
    }
    
    /**
     * This method return a message with the pattern replace by parameters.
     * Parameters are developers responsibility, if there is too much and not enough parameter no exception will be throw
     * But message could be wrong
     * The parameters will replace the pattern "{X}" where X is a number. The pattern is 0-based
     * The X it's the index of the parameters you set in method.
     * The first parameter will replace the {0} pattern if it exists. 
     * If there is not enough parameters, the pattern will not be replace
     * @param key the message key on properties file
     * @param params parameters that will replace the pattern {X} where X is a number
     * @return the message with parameters
     */
    public String getMessage(String key, Object... params  ) {
        try {
            return MessageFormat.format(this.bundle.getString(key), params);
        } catch (MissingResourceException e) {
        	logger.error("Can't find message with key'" + key + "'");
            return '!' + key + '!';
        }
    }

	@Override
	protected Object handleGetObject(String key) {
		return null;
	}

	@Override
	public Enumeration<String> getKeys() {
		return null;
	}
    
}
