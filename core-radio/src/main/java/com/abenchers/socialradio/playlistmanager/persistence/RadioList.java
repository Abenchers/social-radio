package com.abenchers.socialradio.playlistmanager.persistence;

import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.entity.TrackList;
import com.abenchers.socialradio.playlistmanager.entity.TrackListImpl;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserInformation;

@Component("radioList")
@Scope(value = "singleton")
public class RadioList {

	private static final String SOCIAL_LIST = "socialList";
	private List<User> users;

	public RadioList() {
		users = Lists.newArrayList();
		final User user = new User();
		user.setUserId(SOCIAL_LIST);
		TrackList trackList = new TrackListImpl();
		user.setTrackList(trackList);
		user.setUserInformation(new UserInformation());
		users.add(user);

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
