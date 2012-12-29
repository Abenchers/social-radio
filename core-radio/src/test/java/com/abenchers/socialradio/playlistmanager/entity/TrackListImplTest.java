package com.abenchers.socialradio.playlistmanager.entity;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.google.common.collect.Iterables;

public class TrackListImplTest {

	private static final String OTHER_TACK_ID = "otherTackId";
	private static final String SOME_TRACK_ID = "someTrackId";
	private TrackList trackList;

	@Before
	public void setUp() {
		trackList = new TrackListImpl();
	}

	@Test
	public void shouldAddUserTrack() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(SOME_TRACK_ID);
		trackList.addTrack(userTrack);
	}

	@Test
	public void shouldRemoveUserTrack() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(SOME_TRACK_ID);
		trackList.addTrack(userTrack);
		UserTrack userTrack2 = new UserTrack();
		userTrack2.setTrackId(OTHER_TACK_ID);
		trackList.addTrack(userTrack2);
		Assert.assertEquals(2, trackList.listTracks().size());
		trackList.removeTrack(SOME_TRACK_ID);
		Assert.assertEquals(1, trackList.listTracks().size());

	}

	@Test
	public void shouldAddPositiveVoteToUserTrack() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(SOME_TRACK_ID);
		trackList.addTrack(userTrack);
		UserTrack userTrack2 = new UserTrack();
		userTrack2.setTrackId(OTHER_TACK_ID);
		trackList.addTrack(userTrack2);
		Assert.assertEquals(2, trackList.listTracks().size());
		trackList.vote(SOME_TRACK_ID, PlayListManagerStatus.POSITIVE_VOTE);
		List<UserTrack> userTracksList = trackList.listTracks();

		UserTrack userTrackVoted = Iterables.getOnlyElement(Iterables.filter(
				userTracksList, new RemoveUserTrackPredicate(SOME_TRACK_ID)));
		Assert.assertSame(1l, userTrackVoted.getVotes());

	}
	
	@Test
	public void shouldNotRepeatTheSameUserTrack(){
		
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(SOME_TRACK_ID);
		trackList.addTrack(userTrack);
		UserTrack userTrack2 = new UserTrack();
		userTrack2.setTrackId(SOME_TRACK_ID);
		trackList.addTrack(userTrack2);
		
		Assert.assertEquals(1, trackList.listTracks().size());
		
		
	}

}
