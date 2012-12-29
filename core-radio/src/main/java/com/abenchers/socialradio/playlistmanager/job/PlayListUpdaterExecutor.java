package com.abenchers.socialradio.playlistmanager.job;

public interface PlayListUpdaterExecutor {

	void send(PlayListUpdaterQueueItem item);

}
