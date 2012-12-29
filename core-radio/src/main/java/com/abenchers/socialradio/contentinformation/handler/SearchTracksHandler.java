package com.abenchers.socialradio.contentinformation.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.SearchContentInformation;

@Component("searchTracksHandler")
public class SearchTracksHandler implements
		ContentInformationHandler<TrackSearch, TracksFound> {

	@Autowired
	private SearchContentInformation searchContentInformation;

	public TracksFound process(TrackSearch trackSearch) {
		final TracksFound foundTracks = new TracksFound();
		foundTracks.setStatus(ResponseStatus.SUCCESS);
		foundTracks.setTracks(searchContentInformation.search(trackSearch));
		return foundTracks;
	}

	public SearchContentInformation getSearchContentInformation() {
		return searchContentInformation;
	}

	public void setSearchContentInformation(
			SearchContentInformation searchContentInformation) {
		this.searchContentInformation = searchContentInformation;
	}

}
