package com.abenchers.socialradio.playlistmanager.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("playListUpdaterJobManager")
public class PlayListUpdaterJobManagerImpl implements PlayListUpdaterJobManager {

	@Autowired
	private PlaylistUpdaterJobService playlistUpdaterJobService;

	@Override
	public void generateJob(Long durationInMilliseconds, String channel,
			String userId) {
		playlistUpdaterJobService.generateJob(durationInMilliseconds, channel,
				userId);

	}

}
