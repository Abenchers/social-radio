package com.abenchers.socialradio.contentinformation.metadataextract;

import com.abenchers.socialradio.common.SourceType;

public interface TrackInfoExtractorFactory {

	TrackInfoExtractor getTrackInfoExtractor(SourceType sourceType);

}
