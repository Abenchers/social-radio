package com.abenchers.socialradio.playlistmanager.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("playListUpdaterExecutor")
public class PlayListUpdaterExecutorImpl implements PlayListUpdaterExecutor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PlayListUpdaterExecutorImpl.class);

	@Autowired
	private PlayListUpdaterJobManager playListUpdaterJobManager;

	@Override
	public void send(PlayListUpdaterQueueItem item) {
		playListUpdaterJobManager.generateJob(item.getNewSongDuration(),
				item.getChannel(), item.getUserId());

		LOGGER.info("Generation new job for channel: " + item.getChannel()
				+ " user: " + item.getUserId() + " to time: "
				+ item.getNewSongDuration());

	}

}
