package com.abenchers.socialradio.usermanager.handler;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.UsersHolderImpl;
import com.abenchers.socialradio.playlistmanager.entity.UserQueueStatus;
import com.abenchers.socialradio.playlistmanager.job.PlaylistUpdaterJobService;
import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.service.StreamingService;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;

@Component("startUserHandler")
public class StartUserHandler {

	private static final String USER_ALREADY_STARTER_DESCRIPTION = "The user is already starter";

	@Autowired
	private UsersHolderImpl userQueue;

	@Autowired
	private StreamingService streamingService;

	@Autowired
	private PlaylistUpdaterJobService updaterJobService;

	public UserManagerResponse process(final String userId) {

		UserManagerResponse response = new UserManagerResponse();
		
		if(userQueue.existsUser(userId)){
			response.setStreamingURL(userQueue.getUserStatus(userId).getUrl());
			response.setResponseStatus(ResponseStatus.WARNING);
			response.setDescription(USER_ALREADY_STARTER_DESCRIPTION);
		}
		else{
		
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(userId);

		StreamingStartChannelResponse streamingStartChannelResponse = streamingService
				.start(streamingActionRequest);
		UserQueueStatus userQueueStatus = new UserQueueStatus();
		userQueueStatus.setUrl(streamingStartChannelResponse.getStreamURL());

		userQueueStatus.setLastActivity(Calendar.getInstance());
		userQueueStatus.setChannel(userId);
		userQueue.addUser(userId, userQueueStatus);

		updaterJobService.generateJob(10000l, userId, userId);

		response.setResponseStatus(ResponseStatus.SUCCESS);
		response.setStreamingURL(streamingStartChannelResponse.getStreamURL());
		
		streamingService.play(streamingActionRequest);
		
		}
		return response;
		
	}

}
