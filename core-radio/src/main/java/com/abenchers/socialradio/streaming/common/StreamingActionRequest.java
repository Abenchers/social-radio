package com.abenchers.socialradio.streaming.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StreamingActionRequest {

	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
