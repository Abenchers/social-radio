package com.abenchers.socialradio.streaming.common;

public class StreamingStatusResponse extends StreamingResponse {

	private Boolean isPlaying;

	public Boolean isPlaying() {
		return isPlaying;
	}

	public void setIsPlaying(final Boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

}
