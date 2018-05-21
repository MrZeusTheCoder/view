package com.advancedpwr.view.filter;

import java.lang.reflect.Constructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConstructionStrategy
{
	boolean matches( Constructor inConstructor );
	
	Object createInstance( Constructor inConstructor, HttpServletRequest inRequest, HttpServletResponse inResponse ) throws ObjectConstructionException;
}
