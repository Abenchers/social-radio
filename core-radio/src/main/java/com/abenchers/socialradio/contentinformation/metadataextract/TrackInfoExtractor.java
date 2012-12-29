package com.abenchers.socialradio.contentinformation.metadataextract;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public interface TrackInfoExtractor {
	
	Track extractTrackInfo(String source) throws ContentInformationException;

}
