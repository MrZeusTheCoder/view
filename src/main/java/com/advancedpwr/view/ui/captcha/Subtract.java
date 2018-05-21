package com.advancedpwr.view.ui.captcha;

public class Subtract implements Operator
{
	public String toString()
	{
		return "-";
	}

	public int execute( int a, int b )
	{
		return a - b;
	}
	
	
}
