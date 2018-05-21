package com.advancedpwr.view.ui.captcha;

import com.advancedpwr.action.controller.AbstractController;
import com.advancedpwr.view.layout.BasicLayout;
import com.advancedpwr.view.layout.Layout;

public class MyController extends AbstractController
{
	public MyController()
	{
		setLayout( createLayout() );
	}
	
	protected Layout createLayout()
	{
		return new BasicLayout();
	}


	public void firstPage()
	{
		FirstPageView firstPage = new FirstPageView();
		firstPage.setController( this );
		renderHtml( firstPage );
	}
}
