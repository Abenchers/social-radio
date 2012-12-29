package com.abenchers.socialradio.contentinformation.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.IndexContentInformation;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.abenchers.socialradio.contentinformation.metadataextract.TrackInfoExtractor;
import com.abenchers.socialradio.contentinformation.metadataextract.TrackInfoExtractorFactory;

@Component("indexTrackHandler")
public class IndexTrackHandler implements
		ContentInformationHandler<IndexTrack, IndexResponse> {

	@Autowired
	private IndexContentInformation indexContentInformation;

	@Autowired
	private TrackInfoExtractorFactory trackInfoExtractorFactory;

	public IndexResponse process(IndexTrack indexTack)
			throws ContentInformationException {

		final IndexResponse indexResponse = new IndexResponse();
		indexResponse.setStatus(ResponseStatus.SUCCESS);

		TrackInfoExtractor extractor = trackInfoExtractorFactory
				.getTrackInfoExtractor(indexTack.getSourceType());

		Track track = extractor.extractTrackInfo(indexTack.getSource());

		indexResponse.setTrack(track);
		indexResponse.setSource(indexTack.getSource());
		indexResponse.setStatus(indexContentInformation.index(track));

		return indexResponse;
	}

	public void setIndexContentInformation(
			IndexContentInformation indexContentInformation) {
		this.indexContentInformation = indexContentInformation;
	}

	public void setTrackInfoExtractorFactory(
			TrackInfoExtractorFactory trackInfoExtractorFactory) {
		this.trackInfoExtractorFactory = trackInfoExtractorFactory;
	}

}
