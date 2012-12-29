package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;

@Component("resourceListHandler")
public class ResourceListHandler extends ResourceHandler {

	@Override
	public LocalResourceModificationResponse process(
			final LocalResourceModificationRequest request) {
		final LocalResourceModificationResponse response = new LocalResourceModificationResponse();
		response.setResourceList(getLocalResourceDAO().listResources());
		response.setStatus(ResponseStatus.SUCCESS);
		return response;
	}

}
