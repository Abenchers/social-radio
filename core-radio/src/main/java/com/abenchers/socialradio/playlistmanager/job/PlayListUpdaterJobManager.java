package com.abenchers.socialradio.playlistmanager.job;

public interface PlayListUpdaterJobManager {

	void generateJob(Long durationInMilliseconds, String channel, String userId);

}
