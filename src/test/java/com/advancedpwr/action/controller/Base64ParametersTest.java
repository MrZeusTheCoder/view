package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Oct 22, 2010
*/
public class Base64ParametersTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testDecode()
	{
		String params = "nav_action=init.jsp&organizationId=a1PQ0000000AclbMAC&promotion_code=SE289-SCN";
		
		String encoded = DatatypeConverter.printBase64Binary( params.getBytes() );
		System.out.println( encoded );
		Map map = new HashMap();
		map.put(  "k", encoded );
		Base64Parameters decoder = new Base64Parameters( map );
		assertTrue( decoder.exists() );
		Map decoded = decoder.getParameters();
		assertEquals( "init.jsp", decoded.get( "nav_action" ) );
		assertEquals( "a1PQ0000000AclbMAC", decoded.get( "organizationId" ) );
		assertEquals( "SE289-SCN", decoded.get( "promotion_code" ) );
	}

}
