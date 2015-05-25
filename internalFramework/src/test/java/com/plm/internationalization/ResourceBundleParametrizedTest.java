package com.plm.internationalization;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.MissingResourceException;

import org.junit.Test;

public class ResourceBundleParametrizedTest {

	/**
	 * The testing base name for testing
	 */
	private static final String testingBaseName = "testing";

	/**
	 * label in messages file for testing label
	 */
	private static final String labelTest = "label.test";

	private static final String labelTestResultFr = "Je test";

	private static final String labelTestResultEn = "I'm testing";

	/**
	 * label in messages file for testing label with parameters
	 */
	private static final String labelTestParametrized = "label.test.parametrized";
	private static final String labelTestResultParametrizedFrNoReplacement =  "Il y a {0} lapin et {1} poulets";
	private static final String labelTestResultParametrizedFrReplace0 =  "Il y a 10 lapin et {1} poulets";
	private static final String labelTestResultParametrizedFrReplace01 =  "Il y a 10 lapin et 20 poulets";
	/**
	 * label in messages file for testing label with reverse parameters (second
	 * is display in first)
	 */
	private static final String labelTestParametrizedReverse = "label.test.parametrized.reverse";
	private static final String labelTestResultParametrizedFrReplace01Reverse =  "Il y a 20 lapin et 10 poulets";

	@Test
	public void constructorAffirmativeTest() {
		ResourceBundleParametrized bundleFr = new ResourceBundleParametrized(
				testingBaseName, new Locale("fr"));
		assertEquals(labelTestResultFr, bundleFr.getMessage(labelTest));


		ResourceBundleParametrized bundleEn = new ResourceBundleParametrized(
				testingBaseName, new Locale("en"));
		assertEquals(labelTestResultEn, bundleEn.getMessage(labelTest));
	}

	@Test(expected = MissingResourceException.class)
	public void constructorNegativeTest() {
		new ResourceBundleParametrized(
				testingBaseName + "n", new Locale("fr"));
	}

	@Test
	public void getMessageTest() {
		ResourceBundleParametrized bundleFr = new ResourceBundleParametrized(
				testingBaseName, new Locale("fr"));
		assertEquals(labelTestResultFr, bundleFr.getMessage(labelTest));
	}
	
	@Test
	public void getMessageNegativeTest() {
		ResourceBundleParametrized bundleFr = new ResourceBundleParametrized(
				testingBaseName, new Locale("fr"));
		assertEquals("!"+labelTest+"n"+"!", bundleFr.getMessage(labelTest+"n"));
	}


	@Test
	public void getParametrizedMessageTest() {
		ResourceBundleParametrized bundleFr = new ResourceBundleParametrized(
				testingBaseName, new Locale("fr"));
		assertEquals(labelTestResultParametrizedFrNoReplacement, 
				bundleFr.getMessage(labelTestParametrized));
		assertEquals(labelTestResultParametrizedFrReplace0, 
				bundleFr.getMessage(labelTestParametrized,10));
		assertEquals(labelTestResultParametrizedFrReplace01, 
				bundleFr.getMessage(labelTestParametrized,10,20));
		assertEquals(labelTestResultParametrizedFrReplace01, 
				bundleFr.getMessage(labelTestParametrized,10,20,30));
		assertEquals(labelTestResultParametrizedFrReplace01Reverse, 
				bundleFr.getMessage(labelTestParametrizedReverse,10,20,30));
	}

}
