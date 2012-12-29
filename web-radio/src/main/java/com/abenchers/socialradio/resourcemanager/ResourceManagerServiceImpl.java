package com.abenchers.socialradio.resourcemanager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.resourcemanager.service.ResourceManagerContentInformation;

@Component("resourceManagerService")
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceManagerServiceImpl {

	@Autowired
	private ResourceManagerContentInformation resourceManagerContentInformation;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ResourceManagerServiceImpl.class);

	@Path("/{index}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public IndexResponse indexTrack(IndexTrack indexTrack) {
		LOGGER.debug(indexTrack.toString());
		return resourceManagerContentInformation.index(indexTrack);
	}

	@Path("/{search}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TracksFound searchTracks(TrackSearch tracksSearch) {
		LOGGER.debug(tracksSearch.toString());
		return resourceManagerContentInformation.search(tracksSearch);
	}

	public void setResourceManagerContentInformation(
			final ResourceManagerContentInformation resourceManagerContentInformation) {
		this.resourceManagerContentInformation = resourceManagerContentInformation;
	}

}
