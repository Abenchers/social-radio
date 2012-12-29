package com.abenchers.socialradio.playlistmanager.entity;

import com.google.common.base.Predicate;

public class RemoveUserTrackPredicate implements Predicate<UserTrack> {

	private String trackId;

	public RemoveUserTrackPredicate(String trackId) {
		this.trackId = trackId;
	}

	@Override
	public boolean apply(UserTrack userTrack) {

		return userTrack.getTrackId() == trackId;
	}

}
