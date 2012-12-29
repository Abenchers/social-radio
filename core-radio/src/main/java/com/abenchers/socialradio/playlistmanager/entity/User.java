package com.abenchers.socialradio.playlistmanager.entity;

public class User {

	private String userId;
	private UserInformation userInformation;
	private TrackList trackList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(final UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public TrackList getTrackList() {
		return trackList;
	}

	public void setTrackList(TrackList trackList) {
		this.trackList = trackList;
	}

}
