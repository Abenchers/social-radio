package com.abenchers.socialradio.page;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.service.PlayListManagerService;

@ManagedBean(name = "addSongToPlayListWebComponent")
@Component
@SessionScoped
public class AddSongToPlayListWebComponent {


	@Autowired
	//@ManagedProperty(value="#{PlayListManagerService}")
	private PlayListManagerService playListManagerService;


	
	private static final String SOCIAL_LIST = "socialList";
	
	public void addSongToPlaylist(List<String> selectedSongs) {

		AlterPlayListRequest request;
		for (String track : selectedSongs) {
			request = new AlterPlayListRequest();
			request.setTrackId(track);
			request.setUserId(SOCIAL_LIST);
			request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
			playListManagerService.modifyPlayList(request);
		}
	}
}
