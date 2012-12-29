package com.abenchers.socialradio.resourcemanager.localstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceAddHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceListHandler;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceRemoveHandler;

@Component("resourceHandlerFactory")
public class ResourceHandlerFactoryImpl implements ResourceHandlerFactory {

	@Autowired
	private ResourceAddHandler addResourceHandler;

	@Autowired
	private ResourceListHandler resourceListHandler;

	@Autowired
	private ResourceRemoveHandler resourceRemoveHandler;

	@Override
	public ResourceHandler getHandler(
			final LocalResourceAction localResourceAction) {
		if (localResourceAction == LocalResourceAction.ADD) {
			return addResourceHandler;
		}
		if (localResourceAction == LocalResourceAction.LIST) {
			return resourceListHandler;
		}
		if (localResourceAction == LocalResourceAction.REMOVE) {
			return resourceRemoveHandler;
		}
		return null;
	}

	public void setAddResourceHandler(
			final ResourceAddHandler addResourceHandler) {
		this.addResourceHandler = addResourceHandler;
	}

	public void setResourceListHandler(
			final ResourceListHandler resourceListHandler) {
		this.resourceListHandler = resourceListHandler;
	}

	public void setResourceRemoveHandler(
			final ResourceRemoveHandler resourceRemoveHandler) {
		this.resourceRemoveHandler = resourceRemoveHandler;
	}

}
