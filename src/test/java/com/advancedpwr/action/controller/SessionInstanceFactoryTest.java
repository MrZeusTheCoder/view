package com.advancedpwr.action.controller;

import static org.easymock.EasyMock.*;

import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

public class SessionInstanceFactoryTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testSessionInstance()
	{
		HttpSession session = createMock( HttpSession.class );
		expect( session.getAttribute( PrimitiveBean.class.getName() ) ).andReturn( null );
		session.setAttribute( contains(PrimitiveBean.class.getName() ), isA( PrimitiveBean.class ) );
		expectLastCall();
		replay( session );
		SessionInstanceFactory factory = new SessionInstanceFactory();
		PrimitiveBean bean = factory.getSessionInstance( session, PrimitiveBean.class );
		assertNotNull( bean );
		
	}
	
	public void testSessionInstance_exists()
	{
		PrimitiveBean bean = new PrimitiveBean();
		HttpSession session = createMock( HttpSession.class );
		expect( session.getAttribute( PrimitiveBean.class.getName() ) ).andReturn( bean );
		
		replay( session );
		SessionInstanceFactory factory = new SessionInstanceFactory();
		PrimitiveBean returnedBean = factory.getSessionInstance( session, PrimitiveBean.class );
		assertEquals( bean, returnedBean );
		
	}

}
