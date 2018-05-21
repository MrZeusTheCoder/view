package com.advancedpwr.action.config;

import junit.framework.TestCase;


public class CryptTest extends TestCase
{


	public void testCipher_and_Decipher()
	{
		String source = "May the Force be with you.";
		Crypt crypt = new Crypt();
		String encrypted = crypt.encrypt( source );
		assertFalse( source.equals( encrypted  ) );
		System.out.println(encrypted );
		String decrypted = crypt.decrypt( encrypted );
		System.out.println(decrypted);
		assertEquals( source, decrypted );
	}

}
