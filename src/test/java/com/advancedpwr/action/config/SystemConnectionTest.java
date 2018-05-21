package com.advancedpwr.action.config;

import java.io.File;

import junit.framework.TestCase;

public class SystemConnectionTest extends TestCase
{
	public void testDirectory()
	{
		SystemConnection needFullCoverage = new SystemConnection();
		
		SystemConnection.setDev();
		assertEquals( base() + "dev", SystemConnection.configDirectory() );

		SystemConnection.setTest();
		assertEquals( base() + "test", SystemConnection.configDirectory() );

		SystemConnection.setProd();
		assertEquals( base() + "prod", SystemConnection.configDirectory() );
		
		SystemConnection.clear();
		assertEquals( base() + "local", SystemConnection.configDirectory() );
	}
	
	protected String base()
	{
		File baseDir = new File("");
		return baseDir.getAbsolutePath() + File.separator + "properties" + File.separator;
	}
}
