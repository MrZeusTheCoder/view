package com.advancedpwr.action.config;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Crypt
{
	private static final String SALT = "0pen Says Me";
	private Cipher cipher;
	private Cipher decipher;
	
	public Crypt()
	{
		init();
	}

	protected void init()
	{
		try
		{
			byte[] key = SALT.getBytes();
			DESKeySpec desKeySpec = new DESKeySpec( key );
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES" );
			SecretKey secretKey = keyFactory.generateSecret( desKeySpec );
			
			cipher = Cipher.getInstance( "DES/ECB/PKCS5Padding" );
			cipher.init( Cipher.ENCRYPT_MODE, secretKey );
			
			decipher = Cipher.getInstance( "DES/ECB/PKCS5Padding" );
			decipher.init( Cipher.DECRYPT_MODE, secretKey );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}
	
	public String encrypt( String source )
	{
		try
		{
			return DatatypeConverter.printBase64Binary( cipher.doFinal( source.getBytes() )  );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}
	
	public String decrypt( String source )
	{
		try
		{
			byte[] bytes = DatatypeConverter.parseBase64Binary( source );
			return new String ( decipher.doFinal( bytes ) );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}
}
