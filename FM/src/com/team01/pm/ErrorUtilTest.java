package com.team01.pm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorUtilTest {

	private ErrorUtil EU = new ErrorUtil();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReport_an_error() {
		assertEquals(true, EU.report_an_error(1));
		assertEquals(true, EU.report_an_error(2));
		assertEquals(true, EU.report_an_error(3));
		assertEquals(true, EU.report_an_error(4));
	}

}
