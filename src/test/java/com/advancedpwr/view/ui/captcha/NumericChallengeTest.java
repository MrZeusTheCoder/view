/**
 * 
 */
package com.advancedpwr.view.ui.captcha;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 21, 2013
 *
 */
public class NumericChallengeTest extends TestCase
{

	public void testGetChallenge()
	{
		for ( int i = 0; i < 20; i++ )
		{
			NumericChallenge challenge = new NumericChallenge();
			challenge.setLength( 5 );
			System.out.println( challenge.getChallenge()  );
			String answer = challenge.getChallenge();
			challenge.setAnswer( answer );
			assertTrue( challenge.passes() );
		}
		
	}
	

}
