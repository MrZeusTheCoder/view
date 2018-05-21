/**
 * 
 */
package com.advancedpwr.view.ui.captcha;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 21, 2013
 *
 */
public interface Challenge
{

	public abstract String getChallenge();

	public abstract boolean passes();

}