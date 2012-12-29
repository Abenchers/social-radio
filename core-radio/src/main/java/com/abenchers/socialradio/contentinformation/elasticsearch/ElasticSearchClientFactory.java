package com.abenchers.socialradio.contentinformation.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchClientFactory {

	private String elasticSearchNodeURL;
	private Integer elasticSearchNodePort;

	private Client client = null;

	public Client getElasticSearchClient() {
		return (client == null) ? new TransportClient()
				.addTransportAddress(new InetSocketTransportAddress(
						elasticSearchNodeURL, elasticSearchNodePort)) : client;
	}

	public String getElasticSearchNodeURL() {
		return elasticSearchNodeURL;
	}

	public void setElasticSearchNodeURL(final String elasticSearchNodeURL) {
		this.elasticSearchNodeURL = elasticSearchNodeURL;
	}

	public Integer getElasticSearchNodePort() {
		return elasticSearchNodePort;
	}

	public void setElasticSearchNodePort(final Integer elasticSearchNodePort) {
		this.elasticSearchNodePort = elasticSearchNodePort;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

}
