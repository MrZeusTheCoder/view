/**
 * 
 */
package com.advancedpwr.action.j2ee;



/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 12, 2013
 *
 */
public class FakeTimerControls implements TimerControls
{
	protected boolean fieldEnabledFlag;
	protected long fieldPeriod;
	/* (non-Javadoc)
	 * @see com.advancedpwr.action.j2ee.TimerControls#isEnabled()
	 */
	public boolean isEnabled()
	{
		return isEnabledFlag();
	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.action.j2ee.TimerControls#period()
	 */
	public long period()
	{
		return getPeriod();
	}

	public boolean isEnabledFlag()
	{
		return fieldEnabledFlag;
	}

	public void setEnabledFlag( boolean enabledFlag )
	{
		fieldEnabledFlag = enabledFlag;
	}

	public long getPeriod()
	{
		return fieldPeriod;
	}

	public void setPeriod( long period )
	{
		fieldPeriod = period;
	}

}
