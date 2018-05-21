package com.advancedpwr.view.table;

public class StyledObject
{
	protected ClassAttribute fieldCssClass = new ClassAttribute();
	
	public Object cssClass()
	{
		return fieldCssClass.cssClass();
	}

	public void setCssClass( String cssClass )
	{
		fieldCssClass.setCssClass( cssClass );
	}
}
