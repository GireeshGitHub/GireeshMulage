package com.empmgt;

import junit.framework.*;

/**
 * 
 * @author M1024305
 * Unit test class
 *
 */
public class EmpMgtControllerTest {

	int value1;
	int value2;
	// assigning the values
	   protected void setUp(){
	      value1 = 7;
	      value2 = 8;
	   }

	   // test method to add two values
	   public void testAdd(){
	      double result = value1 + value2;
	      assertTrue(result == 15);
	   }
}
