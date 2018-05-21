package com.advancedpwr.view.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 4, 2011
*  
*  A simple, line by line interpreter that identifies "$view.method()" using
*  a regular expression and evaluates them using reflection.
*  
*/
public class RegexInterpreter extends AbstractInterpreter
{
	private static final Logger LOGGER = LoggerFactory.getLogger( RegexInterpreter.class );
	
	protected int lineNumber;
	
	public RegexInterpreter( Renderer inRenderer )
	{
		super( inRenderer );
	}
	
	/* (non-Javadoc)
	 * @see com.siemens.view.render.Interpreter#interpret(java.io.InputStream)
	 */
	public void interpret( InputStream in ) throws IOException
	{
		Charset utf8 = Charset.availableCharsets().get( "UTF-8" );
		BufferedReader reader = new BufferedReader( new InputStreamReader( in, utf8 ) );
		while ( reader.ready() )
		{
			lineNumber++;
			interpretLine( reader.readLine() );
			if ( reader.ready() )
			{
				newline();
			}
		}
	}
	
	protected void newline()
	{
		getPrintStream().print( "\n" );
	}

	protected void interpretLine( String inLine ) throws IOException
	{
		ViewLine line = new ViewLine( inLine );

		Map results = evaluateViewCalls( line );

		List pieces = line.pieces();
		Set keys = results.keySet();
		String indent = getRenderer().getIndent();
		getRenderer().setIndent( indent + line.indent() );
		for ( Iterator iterator = pieces.iterator(); iterator.hasNext(); )
		{
			String piece = (String) iterator.next();
			if ( piece.length() == 0 )
			{
				continue;
			}
			Object result = piece;
			if ( keys.contains( piece ) )
			{
				result = results.get( piece );
			}
			render( result );
			getRenderer().setIndent( "" );
		}
		getRenderer().setIndent( indent );
	}

	protected Map evaluateViewCalls( ViewLine line )
	{
		Map results = new HashMap();
		List viewCalls = line.viewMethodCalls();
		for ( Iterator iterator = viewCalls.iterator(); iterator.hasNext(); )
		{
			ViewCall viewCall = (ViewCall) iterator.next();
			Object result = executeCall( viewCall );
			results.put( viewCall.getSource(), result );
		}
		return results;
	}

	protected Object executeCall( ViewCall inCall )
	{
		String location = debugLocation( inCall.methodName() );
		try
		{
			LOGGER.debug( "Rendering " + location );
			return inCall.execute( getView() );
		}
		catch ( NoSuchMethodException e )
		{
			LOGGER.warn( "Missing method " + location  );
			return inCall.getSource();
		}
		catch ( InvocationTargetException ite )
		{
			LOGGER.error( "Exception rendering " + location, ite.getCause() );
			throw new InterpreterException( "Exception rendering " + location, ite.getCause() );
		}
		catch ( IllegalAccessException e )
		{
			LOGGER.error( "Exception rendering " + location, e );
			throw new InterpreterException( "Exception rendering " + location, e );
		}
		
	}

	protected Class<? extends Object> targetClass()
	{
		return getView().getClass();
	}

	protected String debugLocation( String methodName )
	{
		return targetClass().getCanonicalName() + "." + methodName + "(), line " + lineNumber
				+ " of " + getResourceFileName();
	}

	protected void render( Object result ) throws IOException
	{
		getRenderer().render( result );

	}

}
