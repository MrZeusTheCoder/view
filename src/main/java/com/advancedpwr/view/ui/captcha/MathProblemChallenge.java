package com.advancedpwr.view.ui.captcha;


public class MathProblemChallenge implements Challenge
{
	public static Operator[] OPERATORS = new Operator[]{ new Add(), new Subtract(), new Multiply() };
	
	protected Integer fieldFirstNumber;
	protected Integer fieldSecondNumber;
	protected Operator fieldOperator;
	
	protected int fieldAnswer;
	
	/* (non-Javadoc)
	 * @see com.advancedpwr.view.ui.captcha.Challenge#getChallenge()
	 */
	public String getChallenge()
	{
		return "" + getFirstNumber() + getOperator() + getSecondNumber() + "=";
	}
	
	/* (non-Javadoc)
	 * @see com.advancedpwr.view.ui.captcha.Challenge#passes()
	 */
	public boolean passes()
	{
		return getAnswer() == calculate();
	}

	protected int calculate()
	{
		return getOperator().execute( getFirstNumber(), getSecondNumber() );
	}
	
	public Operator getOperator()
	{
		if ( fieldOperator == null )
		{
			fieldOperator = selectOperator();
		}
		return fieldOperator;
	}

	protected Operator selectOperator()
	{
		int index = (int) (Math.random() * OPERATORS.length);
		return OPERATORS[index];
	}
	
	public Integer getFirstNumber()
	{
		if ( fieldFirstNumber == null )
		{
			fieldFirstNumber = randomInteger();
		}
		return fieldFirstNumber;
	}
	
	public Integer getSecondNumber()
	{
		if ( fieldSecondNumber == null )
		{
			fieldSecondNumber = randomInteger();
		}

		return fieldSecondNumber;
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

	public int getAnswer()
	{
		return fieldAnswer;
	}

	public void setAnswer( int answer )
	{
		fieldAnswer = answer;
	}
}
