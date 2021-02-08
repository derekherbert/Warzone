package com.JUnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest2 
{
	@Test
    public void testSum() {
		assertEquals(0,new App().sum(0,0,0,0));
    }
}
