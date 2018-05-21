/**
 * 
 */
package com.advancedpwr.action.controller;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Feb 3, 2012
 *
 */
public class CurrentPageAction extends LiveAction
{
	protected AbstractController fieldController;
	
	
	/* (non-Javadoc)
	 * @see com.advancedpwr.action.controller.LiveAction#execute()
	 */
	public void execute()
	{
		getController().current();
	}


	public AbstractController getController()
	{
		return fieldController;
	}


	public void setController( AbstractController controller )
	{
		fieldController = controller;
	}

}
