package com.JUnitTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Simple addition test.
 * @version 1.0
 * @author zhangqian
 */
public class App 
{
	/**
	 * This function can take multiple integers and add them together.
	 * @param integers multiple integers
	 * @return the sum of the accumulations
	 */
	public int sum(int... integers) {
		int total=0;
		for(int n:integers) {
			total+=n;
		}
		return total;
	}
	
    public static void main( String[] args )
    {
    	Result result = JUnitCore.runClasses(AppTest.class); 
		for (Failure failure : result.getFailures()) { 
			System.out.println(failure.toString()); 
		} 
		System.out.println(result.wasSuccessful()); 
    }
}
