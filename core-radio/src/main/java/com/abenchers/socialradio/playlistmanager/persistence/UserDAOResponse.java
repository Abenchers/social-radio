package com.abenchers.socialradio.playlistmanager.persistence;

import com.abenchers.socialradio.playlistmanager.entity.User;

public class UserDAOResponse {

	private UserDAOStatus status;
	private User user;

	public UserDAOStatus getStatus() {
		return status;
	}

	public void setStatus(UserDAOStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
