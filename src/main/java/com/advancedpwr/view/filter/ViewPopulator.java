/**
 * 
 */
package com.advancedpwr.view.filter;

import javax.servlet.http.HttpSession;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 2, 2012
 *
 */
public interface ViewPopulator<T>
{
	public boolean matches( T inView );
	
	public void populate( HttpSession inSession, T inView );
}
