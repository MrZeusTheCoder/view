package com.advancedpwr.view.render;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 26, 2011
*  
*  This class reads a resource file from the classpath and processes the input,
*  evaluating calls of the form "$view.<method call>()" on the target object.
*  
*  If the target object has a public field "i18n", it will also evaluate calls
*  of the form "$view.<method call>()" on that object.
*  
*  If <method call> returns an Object and the object has a resource associated
*  with it that matches the extension, e.g. MyView.java and MyView.html where the
*  extension is set to ".html", this class will render the returned object.
*  
*  If <method call> returns a String it will render the string.
*  If <method call> returns "null" it will render the string "null".
*  If <method call> is return type void no output will be rendered.
*  
*  NOTE: The current implementation calls methods via reflection.
*/
public class ResourceViewRenderer implements Renderer
{
	private static final Logger LOGGER = LoggerFactory.getLogger( ResourceViewRenderer.class );
	
	protected Object fieldView;

	protected PrintStream fieldPrintStream;

	protected String fieldExtension;

	protected Interpreter fieldInterpreter;
	
	protected RenderingListener fieldListener;
	
	protected String fieldIndent = "";
	
	protected ExceptionHandler fieldExceptionHandler;
	
	/* (non-Javadoc)
	 * @see com.siemens.view.render.Renderer#render()
	 */
	public void render()
	{
		if ( getView() instanceof Collection )
		{
			Collection collection = (Collection) getView();
			for ( Iterator iterator = collection.iterator(); iterator.hasNext(); )
			{
				Object object = (Object) iterator.next();
				render( object );
				if ( iterator.hasNext() )
				{
					newline();
				}
			}
			return;
		}
		if ( getView() instanceof String )
		{
			print( getView() );
			return;
		}
		InputStream in = findClassPathResource();
		if ( in == null )
		{
			print( getView() );
			return;
		}
		if ( getView() instanceof Streamed )
		{
			streamImage( in );
			return;
		}
		interpret( in );
	}

	protected void streamImage( InputStream in )
	{
		try
		{
			byte[] buffer = new byte[2048];
			int read = 0;
			while( (read = in.read( buffer )) > 0 )
			{
				getPrintStream().write( buffer, 0, read );
			}
			getPrintStream().flush();
			getPrintStream().close();
		}
		catch ( Exception e )
		{
			handleException( e );
		}
	}
	
	protected void newline()
	{
		getPrintStream().print( "\n" );
	}
	protected void interpret( InputStream in )
	{
		try
		{
			getInterpreter().interpret( in );
			in.close();
		}
		catch ( Exception e )
		{
			handleException( e );
		}
	}
	
	protected void handleException( Exception e )
	{
		getExceptionHandler().handleException( getPrintStream(), e );
	}

	protected InputStream findClassPathResource()
	{
		InputStream in = resourceAsStream( targetClass() );
		Class superClass = targetClass().getSuperclass();
		while ( in == null && superClass != null )
		{
			in = resourceAsStream( superClass );
			superClass = superClass.getSuperclass();
		}
		return in;
	}

	protected String fieldResourceFileName;

	protected InputStream resourceAsStream( Class inClass )
	{
		SourceLoader loader = new SourceLoader();
		loader.setSourceClass( inClass );
		loader.setExtension( getExtension() );
		
		setResourceFileName( resourceFileName( inClass ) );
		
		LOGGER.debug( "Looking for " + loader.inputName() );
		return loader.inputStream();
		
//		return inClass.getResourceAsStream( resourceFileName( inClass ) );
	}

	/* (non-Javadoc)
	 * @see com.siemens.view.render.Renderer#getResourceFileName()
	 */
	public String getResourceFileName()
	{
		return fieldResourceFileName;
	}

	protected String resourceFileName( Class inClass )
	{
		return inClass.getSimpleName() + getExtension();
	}


	/* (non-Javadoc)
	 * @see com.siemens.view.render.Renderer#print(java.lang.String)
	 */
	protected void print( Object piece )
	{
		getPrintStream().print( getIndent() );
		getPrintStream().print( notNull( piece ) );
		getPrintStream().flush();
	}
	
	protected String notNull( Object value )
	{
		if ( value == null )
		{
			value = "";
		}
		return value.toString();
	}

	/* (non-Javadoc)
	 * @see com.siemens.view.render.Renderer#render(java.lang.String, java.lang.Object)
	 */
	public void render( Object result )
	{
		fireRenderEvent( result );
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setExtension( getExtension() );
		renderer.setView( result );
		renderer.setListener( getListener() );
		renderer.setPrintStream( getPrintStream() );
		renderer.setExceptionHandler( getExceptionHandler() );
		renderer.setIndent( getIndent() );
		renderer.render();
	}

	protected void fireRenderEvent( Object result )
	{
		if ( getListener() != null && result != null )
		{
			getListener().renderEvent( result );
		}
	}

	/* (non-Javadoc)
	 * @see com.siemens.view.render.Renderer#getView()
	 */
	public Object getView()
	{
		return fieldView;
	}

	public void setView( Object target )
	{
		fieldView = target;
	}

	protected Class targetClass()
	{
		return getView().getClass();
	}

	public String getExtension()
	{
		if ( getView() instanceof ExtensionProvider )
		{
			return ((ExtensionProvider)getView()).extension();
		}
		return fieldExtension;
	}

	public void setExtension( String extension )
	{
		fieldExtension = extension;
	}

	public PrintStream getPrintStream()
	{
		return fieldPrintStream;
	}

	public void setPrintStream( PrintStream printStream )
	{
		fieldPrintStream = printStream;
	}

	public void setResourceFileName( String resourceFileName )
	{
		fieldResourceFileName = resourceFileName;
	}

	public Interpreter getInterpreter()
	{
		if ( fieldInterpreter == null )
		{
			fieldInterpreter = new RegexInterpreter( this );
		}
		return fieldInterpreter;
	}

	public void setInterpreter( Interpreter interpreter )
	{
		fieldInterpreter = interpreter;
	}

	public RenderingListener getListener()
	{
		return fieldListener;
	}

	public void setListener( RenderingListener listener )
	{
		fieldListener = listener;
	}

	public String getIndent()
	{
		return fieldIndent;
	}

	public void setIndent( String indent )
	{
		fieldIndent = indent;
	}

	public ExceptionHandler getExceptionHandler()
	{
		if ( fieldExceptionHandler == null )
		{
			fieldExceptionHandler = new DefaultExceptionHandler();
		}
		return fieldExceptionHandler;
	}

	public void setExceptionHandler( ExceptionHandler exceptionHandler )
	{
		fieldExceptionHandler = exceptionHandler;
	}

}
