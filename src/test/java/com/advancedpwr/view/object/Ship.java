/**
 * 
 */
package com.advancedpwr.view.object;

import java.util.Date;
import java.util.List;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Apr 2, 2012
 *
 */
public class Ship
{
	protected String fieldName;
	protected Date fieldCommissionDate;
	protected Crewman[] fieldCrew;
	protected Crewman fieldCaptain;
	protected int fieldContractNumber;
	protected List fieldManifest;
	
	public String getName()
	{
		return fieldName;
	}
	public void setName( String name )
	{
		fieldName = name;
	}
	public Date getCommissionDate()
	{
		return fieldCommissionDate;
	}
	public void setCommissionDate( Date commissionDate )
	{
		fieldCommissionDate = commissionDate;
	}
	public Crewman[] getCrew()
	{
		return fieldCrew;
	}
	public void setCrew( Crewman[] crew )
	{
		fieldCrew = crew;
	}
	public Crewman getCaptain()
	{
		return fieldCaptain;
	}
	public void setCaptain( Crewman captain )
	{
		fieldCaptain = captain;
	}
	public int getContractNumber()
	{
		return fieldContractNumber;
	}
	public void setContractNumber( int contractNumber )
	{
		fieldContractNumber = contractNumber;
	}
	public List getManifest()
	{
		return fieldManifest;
	}
	public void setManifest( List manifest )
	{
		fieldManifest = manifest;
	}
	
	public Object getFirstCargoItem()
	{
		return getManifest().get(0);
	}
}
