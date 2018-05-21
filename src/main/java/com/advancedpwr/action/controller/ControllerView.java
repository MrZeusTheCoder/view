package com.advancedpwr.action.controller;


public class ControllerView<T extends AbstractController>
{
	protected T fieldController;

	public T getController()
	{
		return fieldController;
	}

	public ControllerView setController( T controller )
	{
		fieldController = controller;
		return this;
	}
}
