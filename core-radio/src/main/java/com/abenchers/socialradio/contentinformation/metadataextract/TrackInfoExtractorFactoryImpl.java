package com.abenchers.socialradio.contentinformation.metadataextract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.SourceType;

@Component("trackInfoExtractorFactory")
public class TrackInfoExtractorFactoryImpl implements TrackInfoExtractorFactory {

	@Autowired
	private MediaInfoExtractor mediaInfoExtractor;

	@Override
	public TrackInfoExtractor getTrackInfoExtractor(SourceType sourceType) {
		return mediaInfoExtractor;
	}

	public void setMediaInfoExtractor(
			final MediaInfoExtractor mediaInfoExtractor) {
		this.mediaInfoExtractor = mediaInfoExtractor;
	}

}
