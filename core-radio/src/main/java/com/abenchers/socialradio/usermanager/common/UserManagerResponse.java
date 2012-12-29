package com.abenchers.socialradio.usermanager.common;

import javax.xml.bind.annotation.XmlRootElement;

import com.abenchers.socialradio.common.ResponseStatus;

@XmlRootElement
public class UserManagerResponse{

	private ResponseStatus responseStatus;

	private String streamingURL;

	private String description;

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getStreamingURL() {
		return streamingURL;
	}

	public void setStreamingURL(String streamingURL) {
		this.streamingURL = streamingURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
