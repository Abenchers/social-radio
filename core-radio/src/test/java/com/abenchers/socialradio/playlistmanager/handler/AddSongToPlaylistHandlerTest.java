package com.abenchers.socialradio.playlistmanager.handler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.elasticsearch.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.entity.TrackListImpl;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOStatus;

public class AddSongToPlaylistHandlerTest {

	private static final String SOME_TRACK = "someTrack";

	private static final String SOME_LIST = "someList";

	private static final String SOCIAL_LIST = "socialList";

	private AddSongToPlaylistHandler handler;

	private UserDAO userDAOMock;

	private ContentInformationService contentInformationServiceMock;

	@Before
	public void setUp() {
		handler = new AddSongToPlaylistHandler();
		userDAOMock = Mockito.mock(UserDAO.class);
		contentInformationServiceMock = Mockito
				.mock(ContentInformationService.class);
	}

	@Test
	public void shouldAddSongToPlayList() {

		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
		request.setPlayListId(SOME_LIST);
		request.setTrackId(SOME_TRACK);
		request.setUserId(SOCIAL_LIST);

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);

		User userFound = new User();
		userFound.setUserId(SOCIAL_LIST);
		userFound.setTrackList(new TrackListImpl());

		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setStatus(UserDAOStatus.SUCCESS);
		userDAOResponse.setUser(userFound);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		UserDAOResponse userDAOResponseSave = new UserDAOResponse();
		userDAOResponseSave.setStatus(UserDAOStatus.SUCCESS);

		Mockito.when(userDAOMock.saveOrUpdate(Mockito.any(User.class)))
				.thenReturn(userDAOResponseSave);

		TracksFound tracksFound = new TracksFound();
		tracksFound.setTracks(Lists.newArrayList(new Track()));
		Mockito.when(
				contentInformationServiceMock.search(Mockito
						.any(TrackSearch.class))).thenReturn(tracksFound);

		handler.setUserDAO(userDAOMock);
		handler.setContentInformationService(contentInformationServiceMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.SUCCESS,
				response.getStatus());

	}

	@Test
	public void shouldFailAddingSongToPlayListUserNotExist() {

		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
		request.setPlayListId(SOME_LIST);
		request.setTrackId(SOME_TRACK);
		request.setUserId(SOCIAL_LIST);

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);

		User userFound = new User();
		userFound.setUserId(SOCIAL_LIST);

		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setStatus(UserDAOStatus.FAIL);
		userDAOResponse.setUser(null);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		handler.setUserDAO(userDAOMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.FAIL,
				response.getStatus());

	}
	
	@Test
	public void shouldFailAddingSongToPlayListTrackIdEmpty() {

		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
		request.setPlayListId(SOME_LIST);
		request.setUserId(SOCIAL_LIST);

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);

		User userFound = new User();
		userFound.setUserId(SOCIAL_LIST);
		userFound.setTrackList(new TrackListImpl());

		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setStatus(UserDAOStatus.SUCCESS);
		userDAOResponse.setUser(userFound);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		UserDAOResponse userDAOResponseSave = new UserDAOResponse();
		userDAOResponseSave.setStatus(UserDAOStatus.SUCCESS);

		Mockito.when(userDAOMock.saveOrUpdate(Mockito.any(User.class)))
				.thenReturn(userDAOResponseSave);

		TracksFound tracksFound = new TracksFound();
		tracksFound.setTracks(Lists.newArrayList(new Track()));
		Mockito.when(
				contentInformationServiceMock.search(Mockito
						.any(TrackSearch.class))).thenReturn(tracksFound);

		handler.setUserDAO(userDAOMock);
		handler.setContentInformationService(contentInformationServiceMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.FAIL,
				response.getStatus());

	}
	
	@Test
	public void shouldFailAddingSongToPlayListTrackNotExits() {

		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
		request.setPlayListId(SOME_LIST);
		request.setUserId(SOCIAL_LIST);
		request.setTrackId(SOME_TRACK);

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);

		User userFound = new User();
		userFound.setUserId(SOCIAL_LIST);
		userFound.setTrackList(new TrackListImpl());

		UserDAOResponse userDAOResponse = new UserDAOResponse();
		userDAOResponse.setStatus(UserDAOStatus.SUCCESS);
		userDAOResponse.setUser(userFound);

		Mockito.when(userDAOMock.get(Mockito.any(User.class))).thenReturn(
				userDAOResponse);

		UserDAOResponse userDAOResponseSave = new UserDAOResponse();
		userDAOResponseSave.setStatus(UserDAOStatus.SUCCESS);

		Mockito.when(userDAOMock.saveOrUpdate(Mockito.any(User.class)))
				.thenReturn(userDAOResponseSave);

		TracksFound tracksFound = new TracksFound();
		tracksFound.setTracks(new ArrayList<Track>());
		Mockito.when(
				contentInformationServiceMock.search(Mockito
						.any(TrackSearch.class))).thenReturn(tracksFound);

		handler.setUserDAO(userDAOMock);
		handler.setContentInformationService(contentInformationServiceMock);

		AlterPlayListResponse response = handler.process(request);

		Assert.assertEquals(AlterPlayListResponseStatus.TRACK_DOES_NOT_EXIST,
				response.getStatus());

	}

}
