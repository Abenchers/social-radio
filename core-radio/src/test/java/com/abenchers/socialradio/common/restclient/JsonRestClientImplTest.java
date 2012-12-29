package com.abenchers.socialradio.common.restclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;


public class JsonRestClientImplTest {

	private static final String RESPONSE_STATUS_DESCRIPION = "OK";
	private static final Integer RESPONSE_STATUS_200 = Integer.valueOf(200);
	private static final String HOST = "http://localhost:8080/Web-Social-Radio/resourceManager/search";
	private static final String JSON_TO_SEARCH = "{\"fieldToSearch\":[\"cadillacs\"]}";
	private JsonRestClientImpl client;
	private HTTPMethodTypeFactory factoryMock;
	private DefaultHttpClient httpClientMock;

	@Before
	public void setUp() {

		client = new JsonRestClientImpl();
		factoryMock = Mockito.mock(HTTPMethodTypeFactory.class);
		httpClientMock = Mockito.mock(DefaultHttpClient.class);

	}

	@Test
	@Ignore
	public void shouldComunicate() throws RestClientException,
			ClientProtocolException, IOException, URISyntaxException {
		// httpClienttMock = new DefaultHttpClient();
		HttpPost httpPostMock = new HttpPost();// Mockito.mock(HttpPost.class);
		httpPostMock.setURI(new URI("SomeURL"));
		//HttpResponse basicHttpResponseMock = Mockito.mock(HttpResponse.class);
		Mockito.when(factoryMock.getHTTPMethod(MethodType.POST, JSON_TO_SEARCH))
				.thenReturn(httpPostMock);
		// Mockito.when(httpClienttMock.execute(httpPostMock)).thenReturn(
		// basicHttpResponseMock);

		client.setHttpClient(httpClientMock);
		client.setFactory(factoryMock);

		ClientHTTPResponse clientHTTPResponse = client.sendMessage(HOST,
				MethodType.POST, JSON_TO_SEARCH);

		Assert.assertEquals(RESPONSE_STATUS_200, clientHTTPResponse.getStatus());
		Assert.assertEquals(RESPONSE_STATUS_DESCRIPION,
				clientHTTPResponse.getDescription());
	}
}
