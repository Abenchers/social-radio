package com.abenchers.socialradio.resourcemanager.remotestorage.conector;

import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;

public interface RemoteConnector {
	
	TracksFound search(TrackSearch  trackSearch);

}
 