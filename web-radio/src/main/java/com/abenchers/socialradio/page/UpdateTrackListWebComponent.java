package com.abenchers.socialradio.page;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.entity.TrackList;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;

@ManagedBean(name = "updateTrackListWebComponent")
@Component
@SessionScoped
public class UpdateTrackListWebComponent {

	@Autowired
	// @ManagedProperty(value="#{UserDAO}")
	private UserDAO userDAO;

	public List<UserTrack> updateTrackList(String userId) {

		User userToFind = new User();
		userToFind.setUserId(userId);
		UserDAOResponse userDAOResponse = userDAO.get(userToFind);
		TrackList trackListFromUser = userDAOResponse.getUser().getTrackList();

		List<UserTrack> playListSongs = trackListFromUser.listTracks();

		return playListSongs;
	}

}
