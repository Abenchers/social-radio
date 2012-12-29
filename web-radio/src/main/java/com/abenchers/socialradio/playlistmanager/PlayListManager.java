package com.abenchers.socialradio.playlistmanager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.service.PlayListManagerService;

@Service("playListManager")

public class PlayListManager {

	@Autowired
	private PlayListManagerService playListManagerService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modifyPlayList")
	public AlterPlayListResponse playbackModification(
			AlterPlayListRequest request) {

		return playListManagerService.modifyPlayList(request);

	}

}
