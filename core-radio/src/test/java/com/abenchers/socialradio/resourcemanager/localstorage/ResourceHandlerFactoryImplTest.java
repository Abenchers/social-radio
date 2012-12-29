package com.abenchers.socialradio.resourcemanager.localstorage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceAddHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceListHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceRemoveHandler;

public class ResourceHandlerFactoryImplTest {

	private ResourceHandlerFactoryImpl factory;

	@Before
	public void setUp() {
		factory = new ResourceHandlerFactoryImpl();
	}

	@Test
	public void shouldReturnAddHandler() {

		factory.setAddResourceHandler(new ResourceAddHandler());

		ResourceHandler handler = factory.getHandler(LocalResourceAction.ADD);

		Assert.assertTrue(handler instanceof ResourceAddHandler);
	}

	@Test
	public void shouldReturnListHandler() {

		factory.setResourceListHandler(new ResourceListHandler());

		ResourceHandler handler = factory.getHandler(LocalResourceAction.LIST);

		Assert.assertTrue(handler instanceof ResourceListHandler);
	}

	@Test
	public void shouldReturnRemoveHandler() {

		factory.setResourceRemoveHandler(new ResourceRemoveHandler());

		ResourceHandler handler = factory
				.getHandler(LocalResourceAction.REMOVE);

		Assert.assertTrue(handler instanceof ResourceRemoveHandler);
	}

}
