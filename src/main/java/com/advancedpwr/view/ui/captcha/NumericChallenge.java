/**
 * 
 */
package com.advancedpwr.view.ui.captcha;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 21, 2013
 *
 */
public class NumericChallenge implements Challenge
{
	protected int fieldLength = 5;
	
	protected List<Integer> fieldNumbers;
	
	protected String fieldAnswer;

	/* (non-Javadoc)
	 * @see com.advancedpwr.view.ui.captcha.Challenge#getChallenge()
	 */
	public String getChallenge()
	{
		StringBuffer sb = new StringBuffer();
		for ( Iterator iterator = getNumbers().iterator(); iterator.hasNext(); )
		{
			Integer n = (Integer) iterator.next();
			sb.append( n );
		}
		return sb.toString();
		
	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.view.ui.captcha.Challenge#passes()
	 */
	public boolean passes()
	{
		return getChallenge().equals( getAnswer() );
	}
	
	protected Integer randomInteger()
	{
		int rand = (int)(Math.random() * 10);
		if ( rand == 0 )
		{
			return randomInteger();
		}
		return rand;
	}

	public int getLength()
	{
		return fieldLength;
	}

	public void setLength( int length )
	{
		fieldLength = length;
	}

	public List<Integer> getNumbers()
	{
		if ( fieldNumbers == null )
		{
			fieldNumbers = initializeNumbers();
		}
		return fieldNumbers;
	}

	protected List<Integer> initializeNumbers()
	{
		List<Integer> list = new ArrayList<Integer>();
		for ( int i = 0; i < getLength(); i++ )
		{
			Integer n = randomInteger();
			list.add( n );
		}
		return list;
	}

	public String getAnswer()
	{
		return fieldAnswer;
	}

	public void setAnswer( String answer )
	{
		fieldAnswer = answer;
	}

}
