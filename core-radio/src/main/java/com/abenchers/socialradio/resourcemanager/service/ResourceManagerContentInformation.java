package com.abenchers.socialradio.resourcemanager.service;

import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;

public interface ResourceManagerContentInformation {
	
	IndexResponse index(IndexTrack indexFile);

	TracksFound search(TrackSearch trackSearch);

}
