package com.advancedpwr.action.controller;

import com.advancedpwr.action.config.Crypt;


public class EncryptedRequestParameters extends RequestParameters
{
	public String parameters()
	{
		String rawParams = super.parameters();
		Crypt e = new Crypt();
		String encrypted = e.encrypt( rawParams.substring( 1 ) );
		String encoded = encode( encrypted );
		return "?" + EncryptedParameters.KEY + "=" + encoded;
	}	
}
