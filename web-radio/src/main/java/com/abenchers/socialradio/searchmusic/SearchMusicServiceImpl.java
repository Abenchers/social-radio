package com.abenchers.socialradio.searchmusic;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.resourcemanager.service.ResourceManagerContentInformation;

@Component("searchMusicService")
public class SearchMusicServiceImpl implements SearchMusicService {

	@Autowired
	private ResourceManagerContentInformation resourceManager;

	@Path("/search")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TracksFound searchTracks(TrackSearch tracksSearch) {
		TracksFound tracksFound = resourceManager.search(tracksSearch);
		return tracksFound;
	}

	public void setResourceManager(
			final ResourceManagerContentInformation resourceManager) {
		this.resourceManager = resourceManager;
	}

}
