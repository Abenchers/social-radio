package com.abenchers.socialradio.contentinformation.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.SourceType;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.abenchers.socialradio.contentinformation.handler.IndexTrackHandler;
import com.abenchers.socialradio.contentinformation.handler.SearchTracksHandler;

@Component("contentInformationService")
public class ContentInformationServiceImpl implements ContentInformationService {

	@Autowired
	private IndexTrackHandler indexTrackHandler;

	@Autowired
	private SearchTracksHandler searchTracksHandler;

	public IndexResponse index(IndexTrack indexTrack) {
		IndexResponse indexResponse = new IndexResponse();
		if (validateIndexTrack(indexTrack, indexResponse))
			try {
				indexResponse = indexTrackHandler.process(indexTrack);
			} catch (ContentInformationException e) {
				indexResponse.setStatus(e.getStatus());
			}

		return indexResponse;
	}

	public TracksFound search(TrackSearch trackSearch) {
		return searchTracksHandler.process(trackSearch);
	}

	public IndexTrackHandler getIndexTrackHandler() {
		return indexTrackHandler;
	}

	public void setIndexTrackHandler(IndexTrackHandler indexTrackHandler) {
		this.indexTrackHandler = indexTrackHandler;
	}

	public SearchTracksHandler getSearchTracksHandler() {
		return searchTracksHandler;
	}

	public void setSearchTracksHandler(SearchTracksHandler searchTracksHandler) {
		this.searchTracksHandler = searchTracksHandler;
	}

	private Boolean validateIndexTrack(IndexTrack indexTrack,
			IndexResponse indexResponse) {
		Boolean state = true;
		if (SourceType.FILE.equals(indexTrack.getSourceType())) {
			if (!(new File(indexTrack.getSource())).exists()) {
				indexResponse
						.setStatus(ResponseStatus.SOURCE_NOT_FOUND);
				state = false;
			}
		} else if (SourceType.FILE != indexTrack.getSourceType()) {
			indexResponse
					.setStatus(ResponseStatus.SOURCE_NOT_SUPPORT);
			state = false;
		}
		indexResponse.setSource(indexTrack.getSource());
		return state;

	}

}
