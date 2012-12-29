package com.abenchers.socialradio.resourcemanager.localstorage;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceAction;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceHandler;

public interface ResourceHandlerFactory {

	ResourceHandler getHandler(LocalResourceAction localResourceAction);

}
