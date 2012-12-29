package com.abenchers.socialradio.playlistmanager.handler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;

public class PlayListManagerHandlerFactoryTest {

	private PlayListManagerHandlerFactory factory;

	@Before
	public void setUp() {
		factory = new PlayListManagerHandlerFactory();
	}

	@Test
	public void shouldReturnAddSongHandler() {

		factory.setAddSongToPlaylistHandler(new AddSongToPlaylistHandler());

		PlayListManagerHandler handler = factory
				.getHandler(PlayListManagerStatus.ADD);

		Assert.assertTrue(handler instanceof AddSongToPlaylistHandler);
	}

	@Test
	public void shouldReturnListHandler() {

		factory.setListSongsFromUser(new ListSongsFromUserHandler());

		PlayListManagerHandler handler = factory
				.getHandler(PlayListManagerStatus.LIST);

		Assert.assertTrue(handler instanceof ListSongsFromUserHandler);
	}

	@Test
	public void shouldReturnVoteHandler() {

		factory.setVoteSongHandler(new VoteSongHandler());

		PlayListManagerHandler handler = factory
				.getHandler(PlayListManagerStatus.POSITIVE_VOTE);

		Assert.assertTrue(handler instanceof VoteSongHandler);
	}

}
