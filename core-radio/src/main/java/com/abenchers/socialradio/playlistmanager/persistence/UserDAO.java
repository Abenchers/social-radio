package com.abenchers.socialradio.playlistmanager.persistence;

import com.abenchers.socialradio.playlistmanager.entity.User;

public interface UserDAO {
	
	UserDAOResponse get(User user);
	
	UserDAOResponse saveOrUpdate(User user); 
}
