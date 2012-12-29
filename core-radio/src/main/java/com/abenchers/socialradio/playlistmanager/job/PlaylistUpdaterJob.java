package com.abenchers.socialradio.playlistmanager.job;

import org.elasticsearch.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserQueueStatus;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.entity.UsersHolder;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.service.StreamingService;
import com.google.common.collect.Iterables;

@Component("playlistUpdaterJob")
@Scope("prototype")
public class PlaylistUpdaterJob implements Runnable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PlaylistUpdaterJob.class);

	@Autowired
	private StreamingService streamingService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	@Qualifier("updatePlaylistJobChannel")
	private PollableChannel pollableChannel;

	@Autowired
	private ContentInformationService contentInformationService;

	@Autowired
	UsersHolder usersHolder;

	private String userId;

	private String channel;

	@Override
	public void run() {

		final User userToFind = new User();
		userToFind.setUserId(userId);
		// User exists should be an validation
		final UserQueueStatus userStatus = usersHolder.getUserStatus(userId);
		if (userStatus != null) {
			// Probably this search by trackId should be added to
			// ContentInformation interface
			final UserDAOResponse userDAOResponse = userDAO.get(userToFind);
			LOGGER.info("Updating the user: "
					+ userDAOResponse.getUser().getUserId());
			final TrackSearch trackSearch = new TrackSearch();
			final UserTrack userTrack = userDAOResponse.getUser()
					.getTrackList().getNextPlayListSong();

			if (userTrack != null) {
				trackSearch.setFieldToSearch(Lists.newArrayList(userTrack
						.getTrackId()));
				final TracksFound tracksFound = contentInformationService
						.search(trackSearch);
				final StreamingAddRequest streamingAddRequest = new StreamingAddRequest();
				streamingAddRequest.setChannel(channel);
				final Track trackFound = Iterables.getOnlyElement(tracksFound
						.getTracks());
				streamingAddRequest.setFileUrl(trackFound.getFullURL());

				streamingService.add(streamingAddRequest);

				PlayListUpdaterQueueItem item = new PlayListUpdaterQueueItem();
				item.setChannel(channel);
				item.setUserId(userId);
				item.setNewSongDuration(Long.valueOf(trackFound.getDuration()));
				Message<PlayListUpdaterQueueItem> message = new GenericMessage<PlayListUpdaterQueueItem>(
						item);
				LOGGER.info("Adding the new Job to queue");
				pollableChannel.send(message);

				StreamingActionRequest streamingActionRequest = new StreamingActionRequest();
				streamingActionRequest.setChannel(channel);
				streamingService.play(streamingActionRequest);

			} else {
				LOGGER.info("There is no songs to reproduce");
			}
		}

	}

	public void setStreamingService(StreamingService streamingService) {
		this.streamingService = streamingService;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setContentInformationService(
			ContentInformationService contentInformationService) {
		this.contentInformationService = contentInformationService;
	}

	public void setUsersHolder(UsersHolder usersHolder) {
		this.usersHolder = usersHolder;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setPollableChannel(PollableChannel pollableChannel) {
		this.pollableChannel = pollableChannel;
	}

}
