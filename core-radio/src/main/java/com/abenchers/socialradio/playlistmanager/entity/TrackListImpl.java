package com.abenchers.socialradio.playlistmanager.entity;

import java.util.List;

import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TrackListImpl implements TrackList {

	private List<UserTrack> userTracks;

	public TrackListImpl() {
		userTracks = Lists.newArrayList();
	}

	@Override
	public void addTrack(final UserTrack userTrack) {
		Preconditions.checkArgument(
				!Strings.isNullOrEmpty(userTrack.getTrackId()),
				"The trackId Should not be null");
		
		if (userTrack.getVotes() == null) {
			userTrack.setVotes(0l);
		}
		if(!userTracks.contains(userTrack)){
			userTracks.add(userTrack);
		}
		
	}

	@Override
	public Boolean removeTrack(String trackId) {
		Integer oldSize = userTracks.size();
		userTracks = Lists.newArrayList(Iterables.filter(userTracks,
				new RemoveUserTrackPredicate(trackId)));
		return userTracks.size() != oldSize;
	}

	@Override
	public List<UserTrack> listTracks() {
		return userTracks;
	}

	@Override
	public Boolean vote(String trackId, PlayListManagerStatus voteType) {
		return makeVote(trackId, voteType);
	}

	@Override
	public UserTrack getNextPlayListSong() {
		UserTrack userTrack = null;
		if (!userTracks.isEmpty())
			userTrack = userTracks.remove(0);
		return userTrack;
	}

	private Boolean makeVote(String trackId, PlayListManagerStatus voteType) {
		Boolean status = false;
		for (int index = 0; index < userTracks.size(); index++) {

			if (userTracks.get(index).getTrackId().equals(trackId)) {
				status = true;
				if (PlayListManagerStatus.NEGATIVE_VOTE.equals(voteType)) {
					userTracks.get(index).setVotes(
							userTracks.get(index).getVotes() - 1);
					downVote(userTracks, index);
				} else {
					userTracks.get(index).setVotes(
							userTracks.get(index).getVotes() + 1);
					UpVote(userTracks, index);
				}
				break;
			}
		}
		return status;
	}

	private void UpVote(List<UserTrack> userTracks, int index) {
		while (userTracks.listIterator(index).hasPrevious()
				&& userTracks.get(index - 1).getVotes() < userTracks.get(index)
						.getVotes()) {
			UserTrack userTrackToMove = userTracks.get(index);
			userTracks.remove(index);
			userTracks.add(index - 1, userTrackToMove);
			index--;

		}
	}

	private void downVote(List<UserTrack> userTracks, int index) {
		while (userTracks.listIterator(index + 1).hasNext()
				&& userTracks.get(index + 1).getVotes() > userTracks.get(index)
						.getVotes()) {
			UserTrack userTrackToMove = userTracks.get(index + 1);
			userTracks.remove(index + 1);
			userTracks.add(index, userTrackToMove);
			index++;

		}
	}

	public void setUserTracks(List<UserTrack> userTracks) {
		this.userTracks = userTracks;
	}

}
