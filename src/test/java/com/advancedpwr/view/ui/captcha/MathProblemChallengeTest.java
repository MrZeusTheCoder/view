package com.advancedpwr.view.ui.captcha;

import junit.framework.TestCase;

public class MathProblemChallengeTest extends TestCase
{

	public void testGetChallenge()
	{
		for ( int i = 0; i < 20; i++ )
		{
			MathProblemChallenge challenge = new MathProblemChallenge();
			System.out.println( challenge.getChallenge() + challenge.calculate() );
			int answer = challenge.calculate();
			challenge.setAnswer( answer );
			assertTrue( challenge.passes() );
		}
		
	}
	
	public void testPasses()
	{
		MathProblemChallenge challenge = new MathProblemChallenge();
		challenge.fieldFirstNumber = 5;
		challenge.fieldSecondNumber = 4;
		challenge.fieldOperator = new Multiply();
		assertEquals( "5*4=", challenge.getChallenge() );
		challenge.setAnswer( 20 );
		assertTrue( challenge.passes() );
		challenge.setAnswer( 9 );
		assertFalse( challenge.passes() );
	}

	public void testPasses_2()
	{
		MathProblemChallenge challenge = new MathProblemChallenge();
		challenge.fieldFirstNumber = 3;
		challenge.fieldSecondNumber = 6;
		challenge.fieldOperator = new Subtract();
		assertEquals( "3-6=", challenge.getChallenge() );
		challenge.setAnswer( -3 );
		assertTrue( challenge.passes() );
		challenge.setAnswer( 9 );
		assertFalse( challenge.passes() );
	}
}
