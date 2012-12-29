package com.abenchers.socialradio.playlistmanager.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;

@Component("playListManagerHandlerFactory")
public class PlayListManagerHandlerFactory {

	@Autowired
	private AddSongToPlaylistHandler addSongToPlaylistHandler;

	@Autowired
	private ListSongsFromUserHandler listSongsFromUser;

	@Autowired
	private VoteSongHandler voteSongHandler;

	public PlayListManagerHandler getHandler(
			final PlayListManagerStatus alterPlayListStatus) {
		PlayListManagerHandler handler = null;
		if (PlayListManagerStatus.ADD.equals(alterPlayListStatus)) {
			handler = addSongToPlaylistHandler;
		}
		if (PlayListManagerStatus.LIST.equals(alterPlayListStatus)) {
			handler = listSongsFromUser;
		}
		if (PlayListManagerStatus.NEGATIVE_VOTE.equals(alterPlayListStatus)
				|| PlayListManagerStatus.POSITIVE_VOTE
						.equals(alterPlayListStatus)) {
			handler = voteSongHandler;
		}

		return handler;
	}

	public void setAddSongToPlaylistHandler(
			AddSongToPlaylistHandler addSongToPlaylistHandler) {
		this.addSongToPlaylistHandler = addSongToPlaylistHandler;
	}

	public void setListSongsFromUser(ListSongsFromUserHandler listSongsFromUser) {
		this.listSongsFromUser = listSongsFromUser;
	}

	public void setVoteSongHandler(VoteSongHandler voteSongHandler) {
		this.voteSongHandler = voteSongHandler;
	}

}
