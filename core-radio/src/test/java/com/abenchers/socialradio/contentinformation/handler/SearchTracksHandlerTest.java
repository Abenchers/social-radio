package com.abenchers.socialradio.contentinformation.handler;

import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.contentinformation.SearchContentInformation;

public class SearchTracksHandlerTest {

	private SearchTracksHandler handler;
	private SearchContentInformation searchContentInformationMock;

	@Before
	public void setUp() {
		handler = new SearchTracksHandler();
		searchContentInformationMock = Mockito
				.mock(SearchContentInformation.class);
	}

	@Test
	public void shouldSearch() {

		TrackSearch trackSearch = new TrackSearch();
		List<Track> tracks = Lists.newArrayList();
		Mockito.when(searchContentInformationMock.search(trackSearch))
				.thenReturn(tracks);

		handler.setSearchContentInformation(searchContentInformationMock);

		handler.process(trackSearch);
	}
}
