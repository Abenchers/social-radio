package com.abenchers.socialradio.contentinformation.elasticsearch;

import java.util.List;

import junit.framework.Assert;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.ObjectArrays;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;

public class SearchInformationContentElasticSearchTest {

	private static final int SEARCH_COUNT = 2;

	private static final String INDEX = "tracks";

	private SearchContentInformationElasticSearch searcher;
	private ElasticSearchClientFactory clientFactoryMock;
	private Client clientMock;
	private SearchRequestBuilder searchRequestBuilderMock;
	private ListenableActionFuture<SearchResponse> actionFutureMock;

	@Before
	public void setUp() {
		searcher = new SearchContentInformationElasticSearch();
		clientFactoryMock = Mockito.mock(ElasticSearchClientFactory.class);
		clientMock = Mockito.mock(Client.class);
		searchRequestBuilderMock = Mockito.mock(SearchRequestBuilder.class);
		actionFutureMock = (ListenableActionFuture<SearchResponse>) Mockito
				.mock(ListenableActionFuture.class);
	}

	@Test
	public void shouldSearch() {
		TrackSearch trackSearch = new TrackSearch();
		trackSearch.setFieldToSearch(Lists.newArrayList("Element To Search"));

		SearchResponse searchResponseMock = Mockito.mock(SearchResponse.class);
		SearchHits searchHitsMock = Mockito.mock(SearchHits.class);
		SearchHitToTrackFunction searchHitToTrackFunctionMock = Mockito
				.mock(SearchHitToTrackFunction.class);

		Mockito.when(clientFactoryMock.getElasticSearchClient()).thenReturn(
				clientMock);
		Mockito.when(clientMock.prepareSearch(INDEX)).thenReturn(
				searchRequestBuilderMock);

		// Mockito.verify(searchRequestBuilderMock);

		Mockito.when(searchRequestBuilderMock.execute()).thenReturn(
				actionFutureMock);
		Mockito.when(actionFutureMock.actionGet()).thenReturn(
				searchResponseMock);

		Mockito.when(searchResponseMock.getHits()).thenReturn(searchHitsMock);

		SearchHit[] searchHitArray = ObjectArrays.newArray(SearchHit.class,
				SEARCH_COUNT);

		Mockito.when(searchHitsMock.getHits()).thenReturn(searchHitArray);
		Mockito.when(
				searchHitToTrackFunctionMock.apply(Mockito.any(SearchHit.class)))
				.thenReturn(new Track());

		searcher.setSearchHitToTrackFunction(searchHitToTrackFunctionMock);
		searcher.setBuilder(clientFactoryMock);

		List<Track> tracks = searcher.search(trackSearch);

		Assert.assertTrue(tracks.size() == SEARCH_COUNT);

	}
}
