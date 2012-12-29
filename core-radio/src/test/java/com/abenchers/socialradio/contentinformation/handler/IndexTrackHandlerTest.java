package com.abenchers.socialradio.contentinformation.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.SourceType;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.IndexContentInformation;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.abenchers.socialradio.contentinformation.metadataextract.TrackInfoExtractor;
import com.abenchers.socialradio.contentinformation.metadataextract.TrackInfoExtractorFactory;

public class IndexTrackHandlerTest {

	private static final String SOURCE = "source";
	private IndexTrackHandler handler;
	private TrackInfoExtractorFactory trackInfoExtractorFactoryMock;
	private IndexContentInformation indexContentInformationMock;
	private TrackInfoExtractor trackInfoExtractorMock;

	@Before
	public void setUp() {
		handler = new IndexTrackHandler();
		trackInfoExtractorFactoryMock = Mockito
				.mock(TrackInfoExtractorFactory.class);
		indexContentInformationMock = Mockito
				.mock(IndexContentInformation.class);
		trackInfoExtractorMock = Mockito.mock(TrackInfoExtractor.class);
	}

	@Test
	public void shouldIndex() throws ContentInformationException {

		IndexTrack indexTrack = new IndexTrack();
		indexTrack.setSource(SOURCE);
		indexTrack.setSourceType(SourceType.FILE);
		Track track = new Track();

		Mockito.when(
				trackInfoExtractorFactoryMock
						.getTrackInfoExtractor(SourceType.FILE)).thenReturn(
				trackInfoExtractorMock);

		Mockito.when(
				trackInfoExtractorMock.extractTrackInfo(Mockito
						.any(String.class))).thenReturn(new Track());

		Mockito.when(indexContentInformationMock.index(track)).thenReturn(
				ResponseStatus.SUCCESS);

		handler.setTrackInfoExtractorFactory(trackInfoExtractorFactoryMock);

		handler.setIndexContentInformation(indexContentInformationMock);

		handler.process(indexTrack);
	}

	@Test(expected = ContentInformationException.class)
	public void shouldFailIndexing() throws ContentInformationException {

		IndexTrack indexTrack = new IndexTrack();
		indexTrack.setSource(SOURCE);
		indexTrack.setSourceType(SourceType.FILE);

		TrackInfoExtractor trackInfoExtractorMock = Mockito
				.mock(TrackInfoExtractor.class);

		Mockito.when(
				trackInfoExtractorMock.extractTrackInfo(Mockito
						.any(String.class))).thenThrow(
				new ContentInformationException(new Exception(),
						ResponseStatus.ERROR_INDEXING));
		Mockito.when(
				trackInfoExtractorFactoryMock
						.getTrackInfoExtractor(SourceType.FILE)).thenReturn(
				trackInfoExtractorMock);

		handler.setIndexContentInformation(indexContentInformationMock);

		handler.setTrackInfoExtractorFactory(trackInfoExtractorFactoryMock);

		handler.process(indexTrack);
	}
}
