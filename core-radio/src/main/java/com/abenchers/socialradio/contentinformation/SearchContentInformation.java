package com.abenchers.socialradio.contentinformation;

import java.util.List;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;

public interface SearchContentInformation {

	List<Track> search(TrackSearch trackSearch);
}
