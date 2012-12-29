package com.abenchers.socialradio.playlistmanager.entity;

import java.util.List;

import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;

public interface TrackList {

	void addTrack(UserTrack userTrack);

	Boolean removeTrack(String trackId);

	List<UserTrack> listTracks();

	Boolean vote(String trackId, PlayListManagerStatus voteType);

	UserTrack getNextPlayListSong();

}
