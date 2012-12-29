package com.abenchers.socialradio.contentinformation.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.DeleteContentInformation;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Component("deleteContentInformation")
public class DeleteContentInformationElasticSearch implements
		DeleteContentInformation {

	private static final String INDEX = "tracks";

	@Autowired
	private ElasticSearchClientFactory clientFactory;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteContentInformationElasticSearch.class);

	@Override
	public ResponseStatus delete(Track track)
			throws ContentInformationException {

		LOGGER.debug("Traying to delete the track: " + track.getTrackId());
		ResponseStatus status = ResponseStatus.SUCCESS;
		Preconditions.checkArgument(!Strings.isNullOrEmpty(track.getTrackId()),
				"The trackId must not be empty to delete");

		final Client client = clientFactory.getElasticSearchClient();

		final DeleteResponse response = client
				.prepareDelete(INDEX, "track", track.getTrackId()).execute()
				.actionGet();
		if (response.isNotFound()) {
			LOGGER.debug("The track to delete is not found: "
					+ track.getTrackId());
			status = ResponseStatus.TRACK_NOT_FOUND;
		}
		return status;
	}

	public void setClientFactory(ElasticSearchClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

}
