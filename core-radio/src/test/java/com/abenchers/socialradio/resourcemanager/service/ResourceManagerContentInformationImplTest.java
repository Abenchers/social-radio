package com.abenchers.socialradio.resourcemanager.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.IndexResponse;
import com.abenchers.socialradio.common.IndexTrack;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;

public class ResourceManagerContentInformationImplTest {

	private ResourceManagerContentInformationImpl resourceManagerContentInformation;

	private ContentInformationService serviceMock;

	@Before
	public void setUp() {
		resourceManagerContentInformation = new ResourceManagerContentInformationImpl();
		serviceMock = Mockito.mock(ContentInformationService.class);
	}

	@Test
	public void shouldIndex() {

		IndexTrack indexTrack = new IndexTrack();

		resourceManagerContentInformation.setService(serviceMock);
		Mockito.when(serviceMock.index(indexTrack)).thenReturn(
				new IndexResponse());
		IndexResponse indexResponse = resourceManagerContentInformation
				.index(indexTrack);
		Assert.assertNotNull(indexResponse);
	}

	@Test
	public void shouldSearch() {

		TrackSearch trackSearch = new TrackSearch();

		resourceManagerContentInformation.setService(serviceMock);
		Mockito.when(serviceMock.search(trackSearch)).thenReturn(
				new TracksFound());
		TracksFound tracksFound = resourceManagerContentInformation
				.search(trackSearch);
		Assert.assertNotNull(tracksFound);
	}

}
