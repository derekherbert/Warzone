package warzoneTest.controller;

import org.junit.Test;

import warzone.controller.CommonController;

public class CommonControllerTest {
	@Test
	public void welcomeTest() {
		(new CommonController()).welcome("test");
	}
	@Test
	public void standbyTest() {
		(new CommonController()).standby();
	}
	@Test
	public void testSwitch() {
		switch("abc") {
			case "abc" :
				System.out.println("abc");
				return;
			case "aaaa" :
				System.out.println("aa");
				break;
			default:
			{
				System.out.println("123");
			}
		}
		
			
	}
}