/**
 * 
 */
package com.advancedpwr.view.layout;

import com.advancedpwr.action.controller.AbstractController;
import com.advancedpwr.view.tags.CSSLinkTag;
import com.advancedpwr.view.tags.StyleTag;



/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 6, 2012
 *
 */
public class BasicLayout extends SimpleLayout
{
	protected AbstractController fieldController;
	
	public Object css()
	{
		StyleTag tag = new StyleTag();
		return tag;
	}
	
	public String hash()
	{
		return getView().getClass().getName();
	}
	
	public Object cssLink()
	{
		CSSLinkTag tag = new CSSLinkTag();
		return tag;
	}

	public Object getCurrentAction()
	{
		return getController().getActionMap().getCurrentAction().key();
	}

	public AbstractController getController()
	{
		return fieldController;
	}

	public void setController( AbstractController controller )
	{
		fieldController = controller;
	}

	public String getContextPath()
	{
		return getController().getRequest().getContextPath();
	}

	

	
}
