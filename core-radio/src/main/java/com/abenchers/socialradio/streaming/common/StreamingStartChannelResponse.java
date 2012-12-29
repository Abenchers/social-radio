package com.abenchers.socialradio.streaming.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StreamingStartChannelResponse extends StreamingResponse {

	private String streamURL;

	public String getStreamURL() {
		return streamURL;
	}

	public void setStreamURL(String streamURL) {
		this.streamURL = streamURL;
	}

}
