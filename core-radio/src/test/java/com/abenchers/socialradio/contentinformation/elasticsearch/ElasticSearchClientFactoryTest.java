package com.abenchers.socialradio.contentinformation.elasticsearch;

import junit.framework.Assert;

import org.elasticsearch.client.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ElasticSearchClientFactoryTest {

	private ElasticSearchClientFactory factory;
	private Client clientMock;

	@Before
	public void setUp() {
		factory = new ElasticSearchClientFactory();
		clientMock = Mockito.mock(Client.class);
	}

	@Test
	public void shouldGenerateClient() {
		factory.setClient(clientMock);

		Client clientReceived = factory.getElasticSearchClient();

		Assert.assertNotNull(clientReceived);
	}

}
