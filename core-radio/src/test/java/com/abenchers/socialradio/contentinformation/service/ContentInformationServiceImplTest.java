package com.abenchers.socialradio.contentinformation.service;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.SourceType;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;
import com.abenchers.socialradio.contentinformation.handler.IndexTrackHandler;
import com.abenchers.socialradio.contentinformation.handler.SearchTracksHandler;

public class ContentInformationServiceImplTest {

	@Rule
    public TemporaryFolder folder = new TemporaryFolder();
	
	private ContentInformationServiceImpl service;
	private IndexTrackHandler indexTrackHandlerMock;
	private SearchTracksHandler searchTracksHandler;

	@Before
	public void setUp() {
		service = new ContentInformationServiceImpl();
		indexTrackHandlerMock = Mockito.mock(IndexTrackHandler.class);
		searchTracksHandler = Mockito.mock(SearchTracksHandler.class);
	}

	@Test
	public void shouldIndex() throws ContentInformationException, IOException {
		final File file = folder.newFile("someSong.mp3");
		final IndexTrack indexFile = new IndexTrack();
		indexFile.setSource(file.getAbsolutePath());
		indexFile.setSourceType(SourceType.FILE);
		Mockito.when(indexTrackHandlerMock.process(indexFile)).thenReturn(
				new IndexResponse());
		service.setIndexTrackHandler(indexTrackHandlerMock);

		service.index(indexFile);
		Mockito.verify(indexTrackHandlerMock).process(indexFile);
	}

	@Test
	public void shouldSearch() {
		final TrackSearch trackSearch = new TrackSearch();
		Mockito.when(searchTracksHandler.process(trackSearch)).thenReturn(
				new TracksFound());
		service.setSearchTracksHandler(searchTracksHandler);

		service.search(trackSearch);
		Mockito.verify(searchTracksHandler).process(trackSearch);
	}

}
