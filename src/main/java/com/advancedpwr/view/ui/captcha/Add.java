package com.advancedpwr.view.ui.captcha;

public class Add implements Operator
{
	public String toString()
	{
		return "+";
	}

	public int execute( int a, int b )
	{
		return a + b;
	}
	
	
}
