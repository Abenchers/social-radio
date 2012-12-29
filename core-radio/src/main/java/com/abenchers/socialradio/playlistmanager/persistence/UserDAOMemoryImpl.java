package com.abenchers.socialradio.playlistmanager.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.entity.User;

@Component("userDAO")
public class UserDAOMemoryImpl implements UserDAO {

	@Autowired
	private RadioList radioList;

	@Override
	public UserDAOResponse get(User user) {
		final UserDAOResponse response = new UserDAOResponse();
		response.setStatus(UserDAOStatus.USER_NOT_EXIST);
		User userFound = getUser(user);
		if (userFound != null) {
			response.setUser(userFound);
			response.setStatus(UserDAOStatus.SUCCESS);
		}

		return response;
	}

	private User getUser(User user) {
		User userFound = null;
		for (User userItem : radioList.getUsers()) {
			if (userItem.getUserId().equals(user.getUserId())) {
				userFound = userItem;
				break;
			}
		}
		return userFound;
	}

	@Override
	public UserDAOResponse saveOrUpdate(User user) {
		final User userFound = getUser(user);
		final UserDAOResponse response = new UserDAOResponse();
		response.setStatus(UserDAOStatus.SUCCESS);

		if (userFound == null) {
			radioList.getUsers().add(user);
		} else {
			updateUser(user);
		}
		return response;
	}

	private void updateUser(User user) {
		List<User> userList = radioList.getUsers();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserId().equals(user.getUserId())) {
				userList.remove(i);
				userList.add(user);
				break;
			}
		}

	}

	public RadioList getRadioList() {
		return radioList;
	}

	public void setRadioList(RadioList radioList) {
		this.radioList = radioList;
	}

}
