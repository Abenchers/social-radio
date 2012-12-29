package com.abenchers.socialradio.playlistmanager.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.handler.ListSongsFromUserHandler;
import com.abenchers.socialradio.playlistmanager.handler.PlayListManagerHandlerFactory;

public class PlayListManagerServiceImplTest {

	private PlayListManagerServiceImpl service;

	private PlayListManagerHandlerFactory handlerFactoryMock;

	private ListSongsFromUserHandler listHandler;

	@Before
	public void setUp() {
		service = new PlayListManagerServiceImpl();

		handlerFactoryMock = Mockito.mock(PlayListManagerHandlerFactory.class);

		listHandler = Mockito.mock(ListSongsFromUserHandler.class);
	}

	@Test
	public void shouldProcess() {
		AlterPlayListRequest request = new AlterPlayListRequest();
		request.setAlterPlayListStatus(PlayListManagerStatus.LIST);

		AlterPlayListResponse expectedResponse = new AlterPlayListResponse();
		expectedResponse.setStatus(AlterPlayListResponseStatus.SUCCESS);

		Mockito.when(handlerFactoryMock.getHandler(PlayListManagerStatus.LIST))
				.thenReturn(listHandler);

		Mockito.when(listHandler.process(request)).thenReturn(expectedResponse);
		service.setHandlerFactory(handlerFactoryMock);

		AlterPlayListResponse response = service.modifyPlayList(request);

		Assert.assertEquals(AlterPlayListResponseStatus.SUCCESS,
				response.getStatus());

		Mockito.verify(handlerFactoryMock).getHandler(PlayListManagerStatus.LIST);
		Mockito.verify(listHandler).process(request);
	}
}
