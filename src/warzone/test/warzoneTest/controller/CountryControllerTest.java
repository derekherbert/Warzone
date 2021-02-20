package warzoneTest.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import warzone.controller.CountryController;
import warzone.service.ControllerFactory;

public class CountryControllerTest {
	CountryController d_countryController = ControllerFactory.getControllerFactory().getCountryController();
	String l_parameters = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testAddCountry1() {
		l_parameters = "123 111";
		assertTrue(d_countryController.addCountry(l_parameters));
	}
	
	@Test
	public void testAddCountry2() {
		l_parameters = "aaa";
		assertFalse(d_countryController.addCountry(l_parameters));
	}

	@Test
	public void testAddCountry3() {
		l_parameters = "111 aaa";
		assertFalse(d_countryController.addCountry(l_parameters));
	}
	
	@Test
	public void testAddCountry4() {
		l_parameters = "aaa sss aaa";
		assertFalse(d_countryController.addCountry(l_parameters));
	}
	
	@Test
	public void testRemoveCountry1() {
		l_parameters = "123";
		assertFalse(d_countryController.removeCountry(l_parameters));
	}
	
	@Test
	public void testRemoveCountry2() {
		l_parameters = "aaa";
		assertFalse(d_countryController.removeCountry(l_parameters));
	}

	@Test
	public void testRemoveCountry3() {
		l_parameters = "aaa aaa";
		assertFalse(d_countryController.removeCountry(l_parameters));
	}
	
	@Test
	public void testRemoveCountry4() {
		l_parameters = "";
		assertFalse(d_countryController.removeCountry(l_parameters));
	}
}
