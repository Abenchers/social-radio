package com.abenchers.socialradio.contentinformation.metadataextract;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public class MediaInfoExtractor implements TrackInfoExtractor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MediaInfoExtractor.class);

	private Map<String, Object> trackSchema;

	private DOMMediaInfoParser parser;

	private TagExtractor tagExtractor;

	private static final String TITLE = "title";
	private static final String ALBUM = "album";
	private static final String ALBUM_PERFORMER = "albumPerformer";
	private static final String PERFORMER = "performer";
	private static final String GENRE = "genre";
	private static final String DURATION = "duration";
	private static final String BITRATE = "bitrate";
	private static final String FILESIZE = "fileSize";
	private static final String FORMAT = "format";
	private static final String URL = "url";

	@Override
	public Track extractTrackInfo(String source)
			throws ContentInformationException {
		final Track track = new Track();
		try {
			parser.init(tagExtractor.process(source));
			track.setAlbum(parser.extractMediaInfo(trackSchema.get(ALBUM)
					.toString()));
			track.setAlbumPerformer(parser.extractMediaInfo(trackSchema.get(
					ALBUM_PERFORMER).toString()));
			track.setBitrate(parser.extractMediaInfo(trackSchema.get(BITRATE)
					.toString()));
			track.setDuration(extractDurationFromString(parser
					.extractMediaInfo(trackSchema.get(DURATION).toString())));
			track.setFileSize(parser.extractMediaInfo(trackSchema.get(FILESIZE)
					.toString()));
			track.setFormat(parser.extractMediaInfo(trackSchema.get(FORMAT)
					.toString()));
			track.setFullURL(parser.extractMediaInfo(trackSchema.get(URL)
					.toString()));
			track.setGenre(parser.extractMediaInfo(trackSchema.get(GENRE)
					.toString()));
			track.setPerformer(parser.extractMediaInfo(trackSchema.get(
					PERFORMER).toString()));
			track.setTitle(parser.extractMediaInfo(trackSchema.get(TITLE)
					.toString()));

		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.ERROR_NO_METADATA_FOUND);
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.ERROR_NO_METADATA_FOUND);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.SOURCE_NOT_FOUND);
		} catch (XPathExpressionException e) {
			LOGGER.error(e.getMessage());
			throw new ContentInformationException(e,
					ResponseStatus.ERROR_NO_METADATA_FOUND);
		}

		return track;
	}

	private String extractDurationFromString(String extractMediaInfo) {
		String myStr = extractMediaInfo.replaceAll("[^\\d]", " ");
		String[] myStrParts = myStr.split("\\s+");
		Long minutes = Long.valueOf(myStrParts[0]) * 60l * 1000l;
		Long seconds = Long.valueOf(myStrParts[1]) * 1000l;
		Long total = minutes + seconds + Long.valueOf(myStrParts[2]);
		return String.valueOf(total);
	}

	public Map<String, Object> getTrackSchema() {
		return trackSchema;
	}

	public void setTrackSchema(Map<String, Object> trackSchema) {
		this.trackSchema = trackSchema;
	}

	public DOMMediaInfoParser getParser() {
		return parser;
	}

	public void setParser(DOMMediaInfoParser parser) {
		this.parser = parser;
	}

	public TagExtractor getTagExtractor() {
		return tagExtractor;
	}

	public void setTagExtractor(TagExtractor tagExtractor) {
		this.tagExtractor = tagExtractor;
	}

}
