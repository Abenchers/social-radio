package com.abenchers.socialradio.resourcemanager.localstorage.handler;

import java.io.File;
import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.SourceType;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationRequest;
import com.abenchers.socialradio.resourcemanager.common.LocalResourceModificationResponse;
import com.google.common.base.Strings;

@Component("resourceAddHandler")
public class ResourceAddHandler extends ResourceHandler {

	@Autowired
	private ContentInformationService contentInformationService;

	public LocalResourceModificationResponse process(
			final LocalResourceModificationRequest request) {
		final LocalResourceModificationResponse response = new LocalResourceModificationResponse();
		if (validateRequest(request, response)) {

			final File file = new File(request.getResource());
			List<String> fileToIndexList = filterAceptedExtensions(file);
			indexFiles(fileToIndexList);
			getLocalResourceDAO().addResource(request.getResource());
			response.setStatus(ResponseStatus.SUCCESS);
			response.setDescription("The resource has been added to the resources list");

		}
		return response;
	}

	private boolean validateRequest(LocalResourceModificationRequest request,
			LocalResourceModificationResponse response) {
		Boolean status = false;
		response.setStatus(ResponseStatus.FAIL);
		if (!Strings.isNullOrEmpty(request.getResource())) {
			final File file = new File(request.getResource());
			if (file.exists()) {
				if (!resourceExists(request.getResource())) {
					status = true;
				} else {
					response.setDescription("Duplicated resource");
				}
			} else {
				response.setDescription("The resources doesn't exist");
			}
		} else {
			response.setDescription("The resource can not be empty or null");
		}
		return status;
	}

	private boolean resourceExists(String resource) {
		List<String> resources = getLocalResourceDAO().listResources();
		return resources.contains(resource);
	}

	private void indexFiles(List<String> fileList) {
		final List<IndexResponse> indexTracks = Lists.newArrayList();
		IndexTrack indexTrack;
		for (String file : fileList) {
			indexTrack = new IndexTrack();
			indexTrack.setSource(file);
			indexTrack.setSourceType(SourceType.FILE);
			indexTracks.add(contentInformationService.index(indexTrack));
		}

	}

	public void setContentInformationService(
			ContentInformationService contentInformationService) {
		this.contentInformationService = contentInformationService;
	}

}
