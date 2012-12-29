package com.abenchers.socialradio.common.restclient;

import junit.framework.Assert;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Before;
import org.junit.Test;

public class HTTPMethodTypeFactoryTest {

	private HTTPMethodTypeFactory factory;

	@Before
	public void setUp() {
		factory = new HTTPMethodTypeFactory();
	}

	@Test
	public void shouldCreateGetMethod() throws RestClientException {

		HttpRequestBase request = factory.getHTTPMethod(MethodType.GET,
				"someContent");

		Assert.assertTrue(request instanceof HttpGet);

	}

	@Test
	public void shouldCreatePostMethod() throws RestClientException {

		HttpRequestBase request = factory.getHTTPMethod(MethodType.POST,
				"someContent");

		Assert.assertTrue(request instanceof HttpPost);

	}

	@Test
	public void shouldCreatePutMethod() throws RestClientException {

		HttpRequestBase request = factory.getHTTPMethod(MethodType.PUT,
				"someContent");

		Assert.assertTrue(request instanceof HttpPut);

	}

	@Test
	public void shouldCreateDeleteMethod() throws RestClientException {

		HttpRequestBase request = factory.getHTTPMethod(MethodType.DELETE,
				"someContent");

		Assert.assertTrue(request instanceof HttpDelete);

	}

}
