package com.abenchers.socialradio.common.restclient;


public interface RestClient {

	public ClientHTTPResponse sendMessage(String host, MethodType httpMethod,
			String content) throws RestClientException;

}
