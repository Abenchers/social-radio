package com.abenchers.socialradio.resourcemanager;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;

public interface ResourceManagerService {

	TracksFound searchTracks(TrackSearch tracksSearch);

	IndexResponse indexTrack(IndexTrack trackIndex);

}
