package com.abenchers.socialradio.playlistmanager.entity;

import java.util.Calendar;

public class UserQueueStatus {

	private String url;

	private Calendar lastActivity;
	
	private String channel;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Calendar getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Calendar lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

}
