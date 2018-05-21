package com.advancedpwr.action.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancedpwr.view.exception.ExceptionView;
import com.advancedpwr.view.layout.Layout;
import com.advancedpwr.view.render.ExceptionHandler;
import com.advancedpwr.view.render.HtmlViewRenderer;
import com.advancedpwr.view.render.RenderingListener;
import com.advancedpwr.view.render.ResourceViewRenderer;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jul 2, 2010
*  
*  
*/
public class ViewManager implements Serializable
{
	private static final Logger LOGGER = LoggerFactory.getLogger( ViewManager.class );

	protected PageContext fieldPageContext;
	
	protected Layout fieldLayout;
	
	protected ExceptionHandler fieldExceptionHandler;
	
	protected RenderingListener fieldRenderingListener;
	
	protected ViewStack fieldViews;
	
	public PageContext getPageContext()
	{
		return fieldPageContext;
	}

	public void setPageContext( PageContext pageContext )
	{
		fieldPageContext = pageContext;
	}
		
	protected void handleException( Exception e )
	{
		error(e);
		ExceptionView view = new ExceptionView();
		view.setException( e );
		PrintStream stream;
		try
		{
			stream = createPrintStream();
		}
		catch ( IOException e1 )
		{
			error( e1 );
			throw new RuntimeException( e1 );
		}
		view.setPrintStream( stream );
		Object pageWithLayout = wrapWithLayout( view );	
		ResourceViewRenderer renderer = new HtmlViewRenderer();
		renderer.setView( pageWithLayout );
		if ( hasExceptionHandler() )
		{
			renderer.setExceptionHandler( getExceptionHandler() );
		}
		renderer.setPrintStream( stream );
		render( renderer );
	}

	protected void error( Throwable t )
	{
		LOGGER.error( t.getMessage(), t );
	}

	public void renderHtml( Object inView )
	{
		Object view = wrapWithLayout( inView );	
		ResourceViewRenderer renderer = new HtmlViewRenderer();
		renderer.setView( view );
		renderer.setListener( getRenderingListener() );
		if ( hasExceptionHandler() )
		{
			renderer.setExceptionHandler( getExceptionHandler() );
		}
		renderToStream( renderer );
		add( inView );
	}

	public void add( Object inView )
	{
		getViews().addView( inView );
	}

	public void previous()
	{
		renderHtml( getViews().previous() );
	}
	
	protected boolean hasExceptionHandler()
	{
		return getExceptionHandler() != null;
	}

	public void renderJavascript( Object view )
	{
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setExtension( ".js" );
		renderer.setView( view );
		renderToStream( renderer );
	}

	protected void renderToStream( ResourceViewRenderer renderer )
	{
		try
		{
			renderer.setPrintStream( createPrintStream() );
			render( renderer );
		}
		catch ( IOException e )
		{
			handleException( e );
		}
	}

	protected void render( ResourceViewRenderer renderer )
	{
		renderer.render();
	}

	protected Object wrapWithLayout( Object view )
	{
		if ( hasLayout() )
		{
			getLayout().wrap( view );
			return getLayout();
		}
		return view;
	}

	protected PrintStream createPrintStream() throws IOException
	{
		return new ResponseStream( getPageContext().getResponse() );
	}

	protected boolean hasLayout()
	{
		return getLayout() != null;
	}
	
	public Layout getLayout()
	{
		return fieldLayout;
	}

	public void setLayout( Layout layout )
	{
		fieldLayout = layout;
	}

	public ExceptionHandler getExceptionHandler()
	{
		return fieldExceptionHandler;
	}

	public void setExceptionHandler( ExceptionHandler exceptionHandler )
	{
		fieldExceptionHandler = exceptionHandler;
	}

	public RenderingListener getRenderingListener()
	{
		return fieldRenderingListener;
	}

	public void setRenderingListener( RenderingListener renderingListener )
	{
		fieldRenderingListener = renderingListener;
	}

	public ViewStack getViews()
	{
		if ( fieldViews == null )
		{
			fieldViews = new ViewStack();
		}
		return fieldViews;
	}

	public void current()
	{
		if ( getViews().isEmpty() )
		{
			return;
		}	
		renderHtml( getViews().peek() );
	}

}
