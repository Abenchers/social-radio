package com.abenchers.socialradio.common.restclient;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

public class HTTPMethodTypeFactory {

	public HttpRequestBase getHTTPMethod(MethodType type, String content)
			throws RestClientException {

		if (MethodType.GET.getCode().equalsIgnoreCase(type.getCode())) {
			return new HttpGet();
		}
		if (MethodType.POST.getCode().equalsIgnoreCase(type.getCode())) {
			final HttpPost postRequest = new HttpPost();
			StringEntity input = null;
			try {
				input = new StringEntity(content);
			} catch (UnsupportedEncodingException e) {
				throw new RestClientException(e);
			}
			postRequest.setEntity(input);
			return postRequest;
		}

		if (MethodType.PUT.getCode().equalsIgnoreCase(type.getCode())) {
			final HttpPut putRequest = new HttpPut();
			StringEntity input = null;
			try {
				input = new StringEntity(content);
			} catch (UnsupportedEncodingException e) {
				throw new RestClientException(e);
			}
			putRequest.setEntity(input);
			return putRequest;
		}

		if (MethodType.DELETE.getCode().equalsIgnoreCase(type.getCode())) {
			return new HttpDelete();
		}

		return null;
	}

}
