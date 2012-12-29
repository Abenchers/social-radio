package com.abenchers.socialradio.playlistmanager.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlterPlayListRequest {

	private String playListId;
	private String trackId;
	private String userId;
	private PlayListManagerStatus alterPlayListStatus;
	public String getPlayListId() {
		return playListId;
	}
	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PlayListManagerStatus getAlterPlayListStatus() {
		return alterPlayListStatus;
	}
	public void setAlterPlayListStatus(PlayListManagerStatus alterPlayListStatus) {
		this.alterPlayListStatus = alterPlayListStatus;
	}
	
	

}
