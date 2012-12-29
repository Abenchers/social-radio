package com.abenchers.socialradio.playlistmanager.entity;

import java.util.Map;

import org.elasticsearch.common.collect.Maps;
import org.springframework.stereotype.Component;

@Component("userQueue")
public class UsersHolderImpl implements UsersHolder {

	private Map<String, UserQueueStatus> userQueue;

	public UsersHolderImpl() {
		this.userQueue = Maps.newHashMap();
	}

	@Override
	public void addUser(String userId, UserQueueStatus userQueueStatus) {
		userQueue.put(userId, userQueueStatus);

	}

	@Override
	public Boolean existsUser(String userId) {
		return userQueue.containsKey(userId);
	}

	@Override
	public void removeUser(String userId) {
		userQueue.remove(userId);
	}

	@Override
	public UserQueueStatus getUserStatus(String userId) {
		return userQueue.get(userId);
	}

}
