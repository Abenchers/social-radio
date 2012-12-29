package com.abenchers.socialradio.contentinformation.elasticsearch;

import junit.framework.Assert;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.contentinformation.exception.ContentInformationException;

public class IndexInformationContentElasticSearchTest {

	private static final String NEW_ID = "newId";
	private static final String SEQUENCE_INDICE = "sequence";
	private static final String SEQUENCES_INDICE = "sequences";
	private static final String TRACK_INDICE = "track";
	private static final String TRACKS_INDICE = "tracks";

	private IndexContentInformationElasticSearch indexer;
	private ElasticSearchClientFactory factoryMock;
	private Client clientMock;
	private IndexRequestBuilder indexRequestBuilderMock;
	private ListenableActionFuture<IndexResponse> actionFutureMock;

	@Before
	public void setUp() {
		indexer = new IndexContentInformationElasticSearch();
		factoryMock = Mockito.mock(ElasticSearchClientFactory.class);
		clientMock = Mockito.mock(Client.class);
		indexRequestBuilderMock = Mockito.mock(IndexRequestBuilder.class);
		actionFutureMock = (ListenableActionFuture<IndexResponse>) Mockito
				.mock(ListenableActionFuture.class);
	}

	@Test
	public void shouldIndex() throws ContentInformationException {
		indexer.setBuilder(factoryMock);

		IndexResponse indexResponse = Mockito.mock(IndexResponse.class);

		Mockito.when(factoryMock.getElasticSearchClient()).thenReturn(
				clientMock);

		Mockito.when(clientMock.prepareIndex(SEQUENCES_INDICE, SEQUENCE_INDICE))
				.thenReturn(indexRequestBuilderMock);
		Mockito.when(
				indexRequestBuilderMock.setSource(Mockito
						.any(XContentBuilder.class))).thenReturn(
				indexRequestBuilderMock);
		Mockito.when(indexRequestBuilderMock.execute()).thenReturn(
				actionFutureMock);
		Mockito.when(actionFutureMock.actionGet()).thenReturn(indexResponse);
		Mockito.when(indexResponse.getId()).thenReturn(NEW_ID);

		Mockito.when(
				clientMock.prepareIndex(TRACKS_INDICE, TRACK_INDICE, NEW_ID))
				.thenReturn(indexRequestBuilderMock);

		Mockito.when(
				indexRequestBuilderMock.setSource(Mockito
						.any(XContentBuilder.class))).thenReturn(
				indexRequestBuilderMock);
		Mockito.when(indexRequestBuilderMock.execute()).thenReturn(
				actionFutureMock);
		Mockito.when(actionFutureMock.actionGet()).thenReturn(indexResponse);

		ResponseStatus status = indexer.index(new Track());

		Assert.assertSame(ResponseStatus.SUCCESS, status);
	}

	@Test(expected = ContentInformationException.class)
	public void shouldThrowElasticSearchException()
			throws ContentInformationException {
		indexer.setBuilder(factoryMock);

		Mockito.when(factoryMock.getElasticSearchClient()).thenReturn(
				clientMock);

		Mockito.when(clientMock.prepareIndex(SEQUENCES_INDICE, SEQUENCE_INDICE))
				.thenReturn(indexRequestBuilderMock);
		Mockito.when(
				indexRequestBuilderMock.setSource(Mockito
						.any(XContentBuilder.class))).thenReturn(
				indexRequestBuilderMock);
		Mockito.when(indexRequestBuilderMock.execute()).thenThrow(
				new ElasticSearchException("defaultError"));

		indexer.index(new Track());
	}

}
