package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;

@Component("resourceRemoveHandler")
public class ResourceRemoveHandler extends ResourceHandler {

	@Override
	public LocalResourceModificationResponse process(
			final LocalResourceModificationRequest request) {
		LocalResourceModificationResponse response = new LocalResourceModificationResponse();
		response.setStatus(ResponseStatus.FAIL);
		response.setDescription("The resource doesn't exists");
		if (getLocalResourceDAO().removeResource(request.getResource())) {
			response.setStatus(ResponseStatus.SUCCESS);
			response.setDescription("The resource has been deleted");
		}
		return response;
	}

}
