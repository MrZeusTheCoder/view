package com.advancedpwr.action.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancedpwr.view.layout.Layout;
import com.advancedpwr.view.render.ExceptionHandler;
import com.advancedpwr.view.render.RenderingListener;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jul 2, 2010
*  
*  
*/
public abstract class AbstractController implements Serializable, ActionPerformer, ContextReceiver
{
	protected static Logger fieldLogger;

	protected Logger getLogger()
	{
		if ( fieldLogger == null )
		{
			fieldLogger = LoggerFactory.getLogger( getClass() );
		}
		return fieldLogger;
	}
	
	protected PageContext fieldPageContext;

	protected ActionMap fieldActionMap;

	protected ViewManager fieldViewManager;


	public PageContext getPageContext()
	{
		return fieldPageContext;
	}

	public void setPageContext( PageContext pageContext )
	{
		fieldPageContext = pageContext;
		getViewManager().setPageContext( pageContext );
		getDynamicViewListener().setSession( pageContext.getSession() );
		getPathSupportListener().setApplicationPath( getRequest().getContextPath() );
	}

	public void forward( String inPath )
	{

		try
		{
			getLogger().debug( this + " forward for path " + inPath );
			getPageContext().forward( inPath );
		}
		catch ( Exception e )
		{
			getLogger().error( "error attempting to forward to " + inPath );
			//			handleException( e );
		}
	}

	public void redirect( String inPath )
	{
		try
		{
			getResponse().sendRedirect( inPath );
		}
		catch ( Exception e )
		{
			handleException( e );
		}
	}

	protected void handleException( Exception e )
	{
		getViewManager().handleException( e );
	}

	public HttpServletResponse getResponse()
	{
		return (HttpServletResponse) getPageContext().getResponse();
	}

	public Scraper scrape( Object inObject )
	{
		Scraper scraper = createScraper();
		scraper.setObject( inObject );
		scraper.scrape( getRequest().getParameterMap() );
		return scraper;
	}

	protected Scraper createScraper()
	{
		return new Scraper();
	}

	public HttpServletRequest getRequest()
	{
		return (HttpServletRequest) getPageContext().getRequest();
	}

	public boolean doAction()
	{
		if ( !preAction() )
		{
			return false;
		}
		Action action = new Action();
		scrape( action );
		if ( !action.hasAction() )
		{
			String appPath = getRequest().getContextPath() + getRequest().getServletPath();
			String actionPath = getRequest().getRequestURI().replaceFirst( appPath, "" );
			actionPath = getRequest().getServletPath().replaceFirst( "/", "" );
			if ( actionPath.length() > 0 )
			{
				action.setACTION( actionPath );
			}
		}
		MethodAction methodAction = toLiveAction( action );
		if ( methodAction.hasAction() )
		{
			getActionMap().addAction( methodAction );
		}
		boolean didSomething = executeLiveAction( action );
		boolean post = postAction();
		return didSomething || post;
	}
	
	protected boolean preAction()
	{
		return true;
	}
	
	protected boolean postAction()
	{
		return false;
	}

	protected boolean executeLiveAction( Action action )
	{
		try
		{
			return getActionMap().executeLiveAction( action );
		}
		catch ( Exception e )
		{
			handleException( e );
		}
		return false;
	}

	protected MethodAction toLiveAction( Action action )
	{
		return new MethodAction( this, action );
	}

	public LiveAction defaultAction()
	{
		CurrentPageAction action = new CurrentPageAction();
		action.setController( this );
		return action;
	}

	public ActionMap getActionMap()
	{
		if ( fieldActionMap == null )
		{
			fieldActionMap = createActionMap();
		}
		return fieldActionMap;
	}

	protected ActionMap createActionMap()
	{
		ActionMap map = new ActionMap();
		configureActionMap( map );
		return map;
	}

	protected void configureActionMap( ActionMap inMap )
	{
		CurrentPageAction action = new CurrentPageAction();
		action.setController( this );
		inMap.setCurrentAction( action );
	}

	public void setActionMap( ActionMap actionMap )
	{
		fieldActionMap = actionMap;
	}

	public ViewManager getViewManager()
	{
		if ( fieldViewManager == null )
		{
			fieldViewManager = new ViewManager();
		}
		fieldViewManager.setRenderingListener( getListener() );
		return fieldViewManager;
	}

	protected RenderingListener fieldListener;

	protected DynamicViewListener fieldDynamicViewListener;

	public RenderingListener getListener()
	{
		if ( fieldListener == null )
		{
			fieldListener = createListener();
		}
		return fieldListener;
	}

	protected RenderingListener createListener()
	{
		CompositeListener listener = new CompositeListener();
		listener.add( getActionMap() );
		listener.add( getDynamicViewListener() );
		listener.add( getPathSupportListener() );
		return listener;
	}

	public DynamicViewListener getDynamicViewListener()
	{
		if ( fieldDynamicViewListener == null )
		{
			fieldDynamicViewListener = new DynamicViewListener();
		}
		return fieldDynamicViewListener;
	}

	protected PathSupportListener fieldPathSupportListener;

	public PathSupportListener getPathSupportListener()
	{
		if ( fieldPathSupportListener == null )
		{
			fieldPathSupportListener = new PathSupportListener();
		}
		return fieldPathSupportListener;
	}

	public void setViewManager( ViewManager viewManager )
	{
		fieldViewManager = viewManager;
		fieldViewManager.setRenderingListener( getActionMap() );
	}

	public void renderPage( ControllerView view )
	{
		view.setController( this );
		renderHtml( view );
	}

	public void renderHtml( Object inView )
	{
		getViewManager().renderHtml( inView );
	}

	public void previous()
	{
		getViewManager().previous();
	}

	public void current()
	{
		getViewManager().current();
	}

	public void renderJavascript( Object view )
	{
		getViewManager().renderJavascript( view );
	}

	public void setLayout( Layout layout )
	{
		getViewManager().setLayout( layout );
	}

	public void setExceptionHandler( ExceptionHandler exceptionHandler )
	{
		getViewManager().setExceptionHandler( exceptionHandler );
	}
	
	public <T> T sessionInstance( Class<T> inClass)
	{
		return new SessionInstanceFactory().getSessionInstance( getRequest().getSession(), inClass );
	}
	
	public <C extends AbstractController> C controller( Class<C> inClass)
	{
		AbstractController controller = new SessionInstanceFactory().getSessionInstance( getRequest().getSession(), inClass );
		controller.setPageContext( getPageContext() );
		controller.setLayout( getViewManager().getLayout() );
		return (C)controller;
	}
	
	public void setNextAction( LiveAction action )
	{
		getActionMap().setNextAction( action );
	}
}
