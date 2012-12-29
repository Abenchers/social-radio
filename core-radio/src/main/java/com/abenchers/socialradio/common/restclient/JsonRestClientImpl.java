package com.abenchers.socialradio.common.restclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

public class JsonRestClientImpl implements RestClient {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE = "Content-Type";
	private HttpRequestBase baseRequest;
	private DefaultHttpClient httpClient;

	private HTTPMethodTypeFactory factory;

	public JsonRestClientImpl() {
		httpClient = new DefaultHttpClient();
	}

	public ClientHTTPResponse sendMessage(final String host,
			final MethodType httpMethod, final String content)
			throws RestClientException {

		final ClientHTTPResponse response = new ClientHTTPResponse();
		HttpResponse httpResponse = null;
		try {
			baseRequest = factory.getHTTPMethod(httpMethod, content);
			URI uri = new URI(host);
			baseRequest.setURI(uri);
			baseRequest.addHeader(CONTENT_TYPE, APPLICATION_JSON);
			httpResponse = httpClient.execute(baseRequest);
		} catch (ClientProtocolException e) {
			throw new RestClientException(e);
		} catch (IOException e) {
			throw new RestClientException(e);
		} catch (URISyntaxException e) {
			throw new RestClientException(e);
		}
		response.setStatus(httpResponse.getStatusLine().getStatusCode());
		response.setDescription(httpResponse.getStatusLine().getReasonPhrase());
		return response;
	}

	public void setFactory(HTTPMethodTypeFactory factory) {
		this.factory = factory;
	}

	public void setHttpClient(DefaultHttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	

}
