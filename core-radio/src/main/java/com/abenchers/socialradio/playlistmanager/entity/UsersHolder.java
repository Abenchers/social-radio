package com.abenchers.socialradio.playlistmanager.entity;

public interface UsersHolder {
	
	public void addUser(String userId, UserQueueStatus userQueueStatus);
	public Boolean existsUser(String userId);
	public void removeUser(String userId);
	public UserQueueStatus getUserStatus(String userId);

}
