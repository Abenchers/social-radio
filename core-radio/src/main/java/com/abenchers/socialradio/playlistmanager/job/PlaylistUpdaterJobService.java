package com.abenchers.socialradio.playlistmanager.job;

import java.util.Calendar;
import java.util.Date;

import org.elasticsearch.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;

public class PlaylistUpdaterJobService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PlaylistUpdaterJobService.class);

	private TaskScheduler taskScheduler;

	private int maxMiliSecondsForNextSong;

	private PlaylistUpdaterJob playlistUpdaterJob;

	public void generateJob(final Long durationInMilliseconds,
			final String channel, final String userId) {

		playlistUpdaterJob.setChannel(channel);
		playlistUpdaterJob.setUserId(userId);

		Calendar currentTime = Calendar.getInstance();
		LOGGER.info("Current Time: " + currentTime.getTime());
		int checkedCast = Ints
				.checkedCast((durationInMilliseconds - maxMiliSecondsForNextSong) / 1000);
		LOGGER.info("The seconds to next song are: " + checkedCast);
		currentTime.add(Calendar.SECOND, checkedCast);
		Date startTime = currentTime.getTime();

		taskScheduler.schedule(playlistUpdaterJob, startTime);

		LOGGER.info("Task schedule in the channel: " + channel + " for: "
				+ startTime);

	}

	public void setTaskScheduler(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}

	public void setMaxMiliSecondsForNextSong(final int maxMiliSecondsForNextSong) {
		this.maxMiliSecondsForNextSong = maxMiliSecondsForNextSong;
	}

	public void setPlaylistUpdaterJob(
			final PlaylistUpdaterJob playlistUpdaterJob) {
		this.playlistUpdaterJob = playlistUpdaterJob;
	}

}
