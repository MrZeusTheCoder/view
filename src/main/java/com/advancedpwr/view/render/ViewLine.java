package com.advancedpwr.view.render;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 27, 2011
*/
public class ViewLine
{
	public static Pattern PATTERN = Pattern.compile( "(\\$view\\.[^(]*[(][ \t]*[)])|(\\$i18n\\.[^(]*[(][ \\t]*[)])" );
	
	protected String fieldLine;
	
	public ViewLine( String inLine )
	{
		fieldLine = inLine;
	}

	public String getLine()
	{
		return fieldLine;
	}
	
	public List viewMethodCalls()
	{
		return methodCalls( getPattern() );
	}

	protected Pattern getPattern()
	{
		return PATTERN;
	}
	
	protected List methodCalls( Pattern inPattern )
	{
		List names = new ArrayList();
		Matcher matcher = inPattern.matcher( getLine() );
		while ( matcher.find() )
		{
			ViewCall call = createCall( matcher.group() );
			names.add( call );
		}
		return names;
	}
	
	protected ViewCall createCall( String inString )
	{
		return ViewCall.create( inString );
	}
	
	public List pieces()
	{
		return toPieces( getPattern(), getLine().substring( indent().length() ) );
	}
	
	protected List toPieces( Pattern inPattern, String inLine )
	{
		Matcher matcher = inPattern.matcher( inLine );
		List pieces = new ArrayList();
		int index = 0;
		while ( matcher.find() )
		{
			pieces.add( inLine.substring( index, matcher.start() ) );
			pieces.add( matcher.group() );
			index = matcher.end();
		}
		pieces.add( inLine.substring( index ) );
		return pieces;
	}

	public String indent()
	{
		String trimmed = getLine().trim();
		return getLine().substring( 0, getLine().indexOf( trimmed ) );
	}
	
}
