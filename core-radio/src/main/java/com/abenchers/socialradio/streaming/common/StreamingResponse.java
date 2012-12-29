package com.abenchers.socialradio.streaming.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StreamingResponse {
	
	private StreamingStatus streamingStatus;

	private String description;

	public StreamingStatus getStreamingStatus() {
		return streamingStatus;
	}

	public void setStreamingStatus(StreamingStatus streamingStatus) {
		this.streamingStatus = streamingStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
