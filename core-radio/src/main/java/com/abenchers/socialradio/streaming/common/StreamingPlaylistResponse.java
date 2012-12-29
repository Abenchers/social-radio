package com.abenchers.socialradio.streaming.common;

import java.util.List;

public class StreamingPlaylistResponse extends StreamingResponse {

	private List<String> playlist;

	public List<String> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<String> playlist) {
		this.playlist = playlist;
	}

}
