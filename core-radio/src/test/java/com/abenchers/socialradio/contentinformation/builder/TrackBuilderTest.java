package com.abenchers.socialradio.contentinformation.builder;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.common.Track;

public class TrackBuilderTest {

	private static final String TITLE = "titleSong";
	private static final String ALBUM = "albumName";
	private static final String ALBUM_PERFORMER = "performer";
	private static final String BITRATE = "bitrate";
	private static final String FILESIZE = "fileSize";
	private static final String FORMAT = "format";
	private static final String URL = "url";
	private static final String GENRE = "genre";
	private static final String PERFORMER = "performer";
	private static final String DURATION = "duration";
	private static final String TRACK_ID = "trackId";

	private TrackObjectBuilder trackBuilder;

	@Before
	public void setUp() {
		trackBuilder = new TrackObjectBuilder();
	}

	@Test
	public void shouldBuild() {

		trackBuilder.withTitle(TITLE);
		trackBuilder.withAlbum(ALBUM);
		trackBuilder.withAlbumPerformer(ALBUM_PERFORMER);
		trackBuilder.withBitrate(BITRATE);
		trackBuilder.withFileSize(FILESIZE);
		trackBuilder.withFormat(FORMAT);
		trackBuilder.withFullURL(URL);
		trackBuilder.withGenre(GENRE);
		trackBuilder.withPerformer(PERFORMER);
		trackBuilder.withDuration(DURATION);
		trackBuilder.withTrackId(TRACK_ID);

		final Track trackCreated = trackBuilder.build();

		Assert.assertSame(TITLE, trackCreated.getTitle());
		Assert.assertSame(ALBUM, trackCreated.getAlbum());
		Assert.assertSame(ALBUM_PERFORMER, trackCreated.getAlbumPerformer());
		Assert.assertSame(BITRATE, trackCreated.getBitrate());
		Assert.assertSame(FILESIZE, trackCreated.getFileSize());
		Assert.assertSame(FORMAT, trackCreated.getFormat());
		Assert.assertSame(URL, trackCreated.getFullURL());
		Assert.assertSame(GENRE, trackCreated.getGenre());
		Assert.assertSame(PERFORMER, trackCreated.getPerformer());
		Assert.assertSame(DURATION, trackCreated.getDuration());
		Assert.assertSame(TRACK_ID, trackCreated.getTrackId());

	}

}
