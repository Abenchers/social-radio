package com.abenchers.socialradio.playlistmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.handler.PlayListManagerHandler;
import com.abenchers.socialradio.playlistmanager.handler.PlayListManagerHandlerFactory;

@Component("playListManagerService")
public class PlayListManagerServiceImpl implements PlayListManagerService {

	@Autowired
	private PlayListManagerHandlerFactory handlerFactory;

	@Override
	public AlterPlayListResponse modifyPlayList(
			final AlterPlayListRequest request) {

		final PlayListManagerHandler handler = handlerFactory
				.getHandler(request.getAlterPlayListStatus());
		return handler.process(request);

	}

	public void setHandlerFactory(
			final PlayListManagerHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

}
