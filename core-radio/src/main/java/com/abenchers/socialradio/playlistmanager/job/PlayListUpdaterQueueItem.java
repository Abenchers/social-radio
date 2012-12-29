package com.abenchers.socialradio.playlistmanager.job;

public class PlayListUpdaterQueueItem {

	private String userId;
	private String channel;
	private Long newSongDuration;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getNewSongDuration() {
		return newSongDuration;
	}

	public void setNewSongDuration(Long newSongDuration) {
		this.newSongDuration = newSongDuration;
	}

}
