package com.abenchers.socialradio.contentinformation.service;

import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;

public interface ContentInformationService {

	IndexResponse index(IndexTrack indexTrack);

	TracksFound search(TrackSearch trackSearch);

}
