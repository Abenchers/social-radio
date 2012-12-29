package com.abenchers.socialradio.playlistmanager.entity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class RemoveUserTrackPredicateTest {

	private static final String TRACK_ID = "trackId";
	private static final String OTHER_TRACK_ID = "other";
	private RemoveUserTrackPredicate removeUserTrackPredicate;

	@Before
	public void setUp() {
		removeUserTrackPredicate = new RemoveUserTrackPredicate(TRACK_ID);
	}

	@Test
	public void shouldReturnTrue() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(TRACK_ID);
		Assert.assertTrue(removeUserTrackPredicate.apply(userTrack));
	}

	@Test
	public void shouldReturnFalse() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(OTHER_TRACK_ID);
		Assert.assertFalse(removeUserTrackPredicate.apply(userTrack));
	}

}
