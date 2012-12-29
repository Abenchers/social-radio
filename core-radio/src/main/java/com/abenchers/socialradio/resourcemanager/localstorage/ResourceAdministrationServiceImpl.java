package com.abenchers.socialradio.resourcemanager.localstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.abenchers.socialradio.resourcemanager.localstorage.handler.ResourceHandler;

@Component("resourceAdministrationService")
public class ResourceAdministrationServiceImpl implements
		ResourceAdministrationService {

	@Autowired
	private ResourceHandlerFactory fileModificationHandlerFactory;

	@Override
	public LocalResourceModificationResponse process(
			final LocalResourceModificationRequest request) {

		final ResourceHandler handler = fileModificationHandlerFactory
				.getHandler(request.getAction());

		return handler.process(request);
	}

	public void setFileModificationHandlerFactory(
			final ResourceHandlerFactory fileModificationHandlerFactory) {
		this.fileModificationHandlerFactory = fileModificationHandlerFactory;
	}

}
