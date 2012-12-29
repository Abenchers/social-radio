package com.abenchers.socialradio.playlistmanager.handler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.TrackListImpl;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOStatus;

public class ListSongsFromUserHandlerTest {

	private static final String SOCIAL_LIST = "socialList";

	private ListSongsFromUserHandler handler;

	private UserDAO userDAOMock;

	@Before
	public void setUp() {
		handler = new ListSongsFromUserHandler();

		userDAOMock = Mockito.mock(UserDAO.class);

	}

	@Test
	public void shouldList() {

		AlterPlayListRequest request = new AlterPlayListRequest();

		request.setUserId(SOCIAL_LIST);

		User userFound = new User();
		TrackListImpl trackList = new TrackListImpl();
		userFound.setUserId(SOCIAL_LIST);
		trackList.addTrack(createUserTrack("firstTrack"));
		trackList.addTrack(createUserTrack("secondTrack"));
		trackList.addTrack(createUserTrack("LastTrack"));
		userFound.setTrackList(trackList);
		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setUser(userFound);
		userDAOResponse.setStatus(UserDAOStatus.SUCCESS);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		handler.setUserDAO(userDAOMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.SUCCESS,
				response.getStatus());
		Assert.assertTrue(response.getTrackIds().size() == 3);
	}

	@Test
	public void shouldFailUserNotExist() {

		AlterPlayListRequest request = new AlterPlayListRequest();

		request.setUserId(SOCIAL_LIST);

		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setUser(null);
		userDAOResponse.setStatus(UserDAOStatus.FAIL);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		handler.setUserDAO(userDAOMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.USER_DONT_EXIST,
				response.getStatus());
	}

	private UserTrack createUserTrack(String trackId) {

		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(trackId);
		userTrack.setVotes(0l);

		return userTrack;
	}
}
