package com.abenchers.socialradio.playlistmanager.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.entity.TrackListImpl;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOStatus;

public class VoteSongHandlerTest {

	private static final String SOCIAL_LIST = "socialList";

	private VoteSongHandler handler;

	private UserDAO userDAOMock;

	@Before
	public void setUp() {
		handler = new VoteSongHandler();
		userDAOMock = Mockito.mock(UserDAO.class);
	}

	@Test
	public void shouldVote() {
		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setTrackId("someTrack");
		request.setUserId(SOCIAL_LIST);
		request.setAlterPlayListStatus(PlayListManagerStatus.POSITIVE_VOTE);

		UserDAOResponse userDAOResponse = new UserDAOResponse();

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);

		User userFound = new User();
		userToFind.setUserId(SOCIAL_LIST);
		userFound.setTrackList(new TrackListImpl());
		userDAOResponse.setUser(userFound);
		userDAOResponse.setStatus(UserDAOStatus.SUCCESS);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);
		handler.setUserDAO(userDAOMock);

		handler.process(request);

	}

}
