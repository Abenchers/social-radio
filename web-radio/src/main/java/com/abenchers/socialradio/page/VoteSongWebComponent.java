package com.abenchers.socialradio.page;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.service.PlayListManagerService;


@ManagedBean(name = "voteSongWebComponent")
@Component
@SessionScoped
public class VoteSongWebComponent {

	@Autowired
	// @ManagedProperty(value="#{PlayListManagerService}")
	private PlayListManagerService playListManagerService;

	public void votePositiveSongs(List<String> selectedSongsToVote,
			String userId, PlayListManagerStatus action) {

		AlterPlayListRequest request;
		for (String trackId : selectedSongsToVote) {
			request = new AlterPlayListRequest();
			request.setTrackId(trackId);
			request.setUserId(userId);
			request.setAlterPlayListStatus(action);
			playListManagerService.modifyPlayList(request);
		}
	}

}
