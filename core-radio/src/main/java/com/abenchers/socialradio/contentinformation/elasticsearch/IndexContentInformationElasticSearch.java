package com.abenchers.socialradio.contentinformation.elasticsearch;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.IndexContentInformation;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public class IndexContentInformationElasticSearch implements
		IndexContentInformation {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IndexContentInformationElasticSearch.class);

	@Autowired
	private ElasticSearchClientFactory builder;

	private static final String SEQUENCE_INDICE = "sequence";
	private static final String SEQUENCES_INDICE = "sequences";
	private static final String TRACK_INDICE = "track";
	private static final String TRACKS_INDICE = "tracks";
	private static final String TRACK_ID = "trackId";
	private static final String TITLE = "title";
	private static final String PERFORMER = "performer";
	private static final String GENRE = "genre";
	private static final String FULL_URL = "fullURL";
	private static final String FORMAT = "format";
	private static final String FILESIZE = "fileSize";
	private static final String DURATION = "duration";
	private static final String BITRATE = "bitrate";
	private static final String ALBUM_PERFORMER = "albumPerformer";
	private static final String ALBUM = "album";

	private XContentBuilder xContentBuilder;

	public ResponseStatus index(final Track track)
			throws ContentInformationException {
		final Client client = builder.getElasticSearchClient();
		ResponseStatus indexStatus = ResponseStatus.SUCCESS;
		try {

			// This is just for testing purposes
			xContentBuilder = (xContentBuilder == null) ? jsonBuilder()
					.startObject().field("value", "newValue").endObject()
					: xContentBuilder;
			IndexResponse newGeneratedIdResponse = client
					.prepareIndex(SEQUENCES_INDICE, SEQUENCE_INDICE)
					.setSource(xContentBuilder).execute().actionGet();
			String generatedId = newGeneratedIdResponse.getId();
			track.setTrackId(generatedId);
			client.prepareIndex(TRACKS_INDICE, TRACK_INDICE, generatedId)
					.setSource(
							jsonBuilder()
									.startObject()
									.field(ALBUM, track.getAlbum())
									.field(ALBUM_PERFORMER,
											track.getAlbumPerformer())
									.field(BITRATE, track.getBitrate())
									.field(DURATION, track.getDuration())
									.field(FILESIZE, track.getFileSize())
									.field(FORMAT, track.getFormat())
									.field(FULL_URL, track.getFullURL())
									.field(GENRE, track.getGenre())
									.field(PERFORMER, track.getPerformer())
									.field(TITLE, track.getTitle())
									.field(TRACK_ID, track.getTrackId())
									.endObject()).execute().actionGet();
		} catch (ElasticSearchException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.ERROR_INDEXING);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.ERROR_INDEXING);
		}

		return indexStatus;
	}

	public ElasticSearchClientFactory getBuilder() {
		return builder;
	}

	public void setBuilder(final ElasticSearchClientFactory builder) {
		this.builder = builder;
	}

	public void setxContentBuilder(XContentBuilder xContentBuilder) {
		this.xContentBuilder = xContentBuilder;
	}

}
