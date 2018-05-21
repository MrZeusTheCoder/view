/**
 * 
 */
package com.advancedpwr.action.controller;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Oct 6, 2011
 *
 */
public class PrimitiveBean
{
	protected boolean fieldBoolean;
	protected int fieldInt;
	protected long fieldLong;
	protected short fieldShort;
	protected float fieldFloat;
	protected double fieldDouble;
	protected char fieldChar;
	protected byte fieldByte;
	protected String fieldString;
	protected BigInteger fieldBigInteger;
	protected BigDecimal fieldBigDecimal;
	public boolean isBoolean()
	{
		return fieldBoolean;
	}
	public void setBoolean( boolean b )
	{
		fieldBoolean = b;
	}
	public int getInt()
	{
		return fieldInt;
	}
	public void setInt( int i )
	{
		fieldInt = i;
	}
	public long getLong()
	{
		return fieldLong;
	}
	public void setLong( long l )
	{
		fieldLong = l;
	}
	public short getShort()
	{
		return fieldShort;
	}
	public void setShort( short s )
	{
		fieldShort = s;
	}
	public float getFloat()
	{
		return fieldFloat;
	}
	public void setFloat( float f )
	{
		fieldFloat = f;
	}
	public double getDouble()
	{
		return fieldDouble;
	}
	public void setDouble( double d )
	{
		fieldDouble = d;
	}
	public char getChar()
	{
		return fieldChar;
	}
	public void setChar( char c )
	{
		fieldChar = c;
	}
	public byte getByte()
	{
		return fieldByte;
	}
	public void setByte( byte b )
	{
		fieldByte = b;
	}
	public String getString()
	{
		return fieldString;
	}
	public void setString( String string )
	{
		fieldString = string;
	}
	public BigInteger getBigInteger()
	{
		return fieldBigInteger;
	}
	public void setBigInteger( BigInteger bigInteger )
	{
		fieldBigInteger = bigInteger;
	}
	public BigDecimal getBigDecimal()
	{
		return fieldBigDecimal;
	}
	public void setBigDecimal( BigDecimal bigDecimal )
	{
		fieldBigDecimal = bigDecimal;
	}
}
