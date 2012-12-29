package com.abenchers.socialradio.searchmusic;

import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;

public interface SearchMusicService {

	TracksFound searchTracks(TrackSearch tracksSearch);

}
