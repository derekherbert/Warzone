package com.JUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest 
{
	@BeforeClass
	public static void MethodBeforeClass() throws Exception{
		System.out.println("beforeClasss.");
	}
 
	@AfterClass
	public static void MethodAfterClass() throws Exception {
		System.out.println("afterClasss.");
	}
 
	@Before
	public void setUp() throws Exception {
		System.out.println("brfore.");
	}
 
	@After
	public void tearDown() throws Exception {
		System.out.println("after.");
	}

	@Test
    public void testSum() {
		assertEquals(9,new App().sum(3, 3, 2, 1));
    }
}
