package com.abenchers.socialradio.resourcemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;

@Component("resourceManagerContentInformation")
public class ResourceManagerContentInformationImpl implements
		ResourceManagerContentInformation {

	@Autowired
	private ContentInformationService contentInformationService;

	public IndexResponse index(final IndexTrack indexFile) {
		return contentInformationService.index(indexFile);
	}

	public TracksFound search(final TrackSearch trackSearch) {
		return contentInformationService.search(trackSearch);
	}

	public void setService(final ContentInformationService service) {
		this.contentInformationService = service;
	}

}
