package com.abenchers.socialradio.usermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.ResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOStatus;
import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;
import com.abenchers.socialradio.usermanager.handler.StartUserHandler;

@Component("userManagerService")
public class UserManagerServiceImpl implements UserManagerService {

	@Autowired
	private StartUserHandler startUserHandler;

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserManagerResponse startUser(final UserManagerRequest request) {
		UserManagerResponse response = new UserManagerResponse();
		response.setResponseStatus(ResponseStatus.USER_DONT_EXIST);
		User userToFind = new User();
		userToFind.setUserId(request.getUserId());
		UserDAOResponse userDAOResponse = userDAO.get(userToFind);
		if (!UserDAOStatus.SUCCESS.equals(userDAOResponse.getStatus())) {
			response.setResponseStatus(ResponseStatus.USER_DONT_EXIST);
		} else {
			response = startUserHandler.process(request.getUserId());
		}
		return response;

	}



}
