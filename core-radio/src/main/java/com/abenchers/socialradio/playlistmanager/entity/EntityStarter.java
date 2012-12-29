package com.abenchers.socialradio.playlistmanager.entity;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.job.PlaylistUpdaterJobService;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.service.StreamingService;
import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.service.UserManagerService;

@Component("entityStarter")
public class EntityStarter {

	private static final String TRACK_ID = "ijP82MwWRGOefwkX_iiwPw";

	private static final String SOCIAL_LIST = "socialList";

	@Autowired
	private UsersHolder userQueue;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private StreamingService streamingService;

	@Autowired
	private PlaylistUpdaterJobService playlistUpdaterJobService;
	
	@Autowired
	private UserManagerService userManagerService;

	@PostConstruct
	public void startEntities() {
		// Creating User
		User user = new User();
		user.setUserId(SOCIAL_LIST);
		TrackListImpl trackList = new TrackListImpl();
		trackList.addTrack(createDefaultTrack());
		user.setTrackList(trackList);
		user.setUserInformation(new UserInformation());

		userDAO.saveOrUpdate(user);

		
		// Starting channel
		StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
		streamingActionRequest.setChannel(SOCIAL_LIST);
		
		/*
		StreamingStartChannelResponse streamingStartChannelResponse = streamingService
				.start(streamingActionRequest);

		// Creating User in the queue
		UserQueueStatus userQueueStatus = new UserQueueStatus();
		userQueueStatus.setChannel(SOCIAL_LIST);
		userQueueStatus.setLastActivity(Calendar.getInstance());
		userQueueStatus.setUrl(streamingStartChannelResponse.getStreamURL());
		userQueue.addUser(SOCIAL_LIST, userQueueStatus);

		// Starting the social list
		playlistUpdaterJobService.generateJob(6001l,
				SOCIAL_LIST, SOCIAL_LIST);
		 */
		//Start playing
		//streamingService.play(streamingActionRequest);
//		UserManagerRequest requestManagerRequest = new UserManagerRequest();
//		requestManagerRequest.setUserId(SOCIAL_LIST);
//		userManagerService.startUser(requestManagerRequest);
//		
//		
	}

	private UserTrack createDefaultTrack() {
		UserTrack userTrack = new UserTrack();
		userTrack.setTrackId(TRACK_ID);
		userTrack.setVotes(0l);
		return userTrack;
	}

}
