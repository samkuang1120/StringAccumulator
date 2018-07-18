package com.epam.exercise.accumulator;

import junit.framework.TestCase;

public class AccumulatorTest extends TestCase {

	public AccumulatorTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Case 1: Empty numbers
	 * 
	 * @throws Exception
	 */
	public void test1() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "";
		assertEquals(0, strAcc.add(numbers));
	}
	
	/**
	 * Case 2: using default delimiter with one number
	 * 
	 * @throws Exception
	 */
	public void test2() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "1";
		assertEquals(1, strAcc.add(numbers));
	}
	
	/**
	 * Case 3: using default delimiter with multiple numbers
	 * 
	 * @throws Exception
	 */
	public void test3() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "1,2,3";
		assertEquals(6, strAcc.add(numbers));
	}

	/**
	 * Case 4: using default delimiter with multiple numbers
	 * 
	 * @throws Exception
	 */
	public void test4() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "1\n2,3";
		assertEquals(6, strAcc.add(numbers));
	}
	
	/**
	 * Case 5: using different delimiter
	 * 
	 * @throws Exception
	 */
	public void test5() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "//;\n1;2;4";
		assertEquals(7, strAcc.add(numbers));
	}
	
	/**
	 * Case 6: using different delimiter and negative values
	 * 
	 * @throws Exception
	 */
	public void test6() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "//;\n1;-2;-4;6,-7";
		try {
			strAcc.add(numbers);
			assertTrue(false);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertEquals("negatives not allowed -2,-4,-7,", e.getMessage());
		}
	}
	
	/**
	 * Case 6: using numbers with greater than 1000
	 * 
	 * @throws Exception
	 */
	public void test7() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "1,1000,1001";
		assertEquals(1001, strAcc.add(numbers));
		
	}
	
	/**
	 * Case 8: using delimiter with any length 
	 * 
	 * @throws Exception
	 */
	public void test8() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "//*#*\n1*#*2*#*3*#*4";
		assertEquals(10, strAcc.add(numbers));
		
	}
	
	/**
	 * Case 9: using multiple delimiter with any length 
	 * 
	 * @throws Exception
	 */
	public void test9() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "//*#*|#\n1#2*#*3#5";
		assertEquals(11, strAcc.add(numbers));
		
	}
	
	/**
	 * Case 10: using multiple delimiter with length longer than one character 
	 * 
	 * @throws Exception
	 */
	public void test10() throws Exception {
		StringAccumulator strAcc = new StringAccumulator();
		String numbers = "//*#*|#!@\n1#!@2*#*3#!@5*#*6";
		assertEquals(17, strAcc.add(numbers));
		
	}
}
