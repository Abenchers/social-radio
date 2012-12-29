package com.abenchers.socialradio.playlistmanager.persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserInformation;

public class UserDAOMemoryImplTest {

	private static final String USER_ID = "userId";
	private UserDAOMemoryImpl userDAO;

	@Before
	public void setUp() {
		userDAO = new UserDAOMemoryImpl();
		userDAO.setRadioList(new RadioList());
	}

	@Test
	public void shouldSaveAndGetUser() {

		User user = new User();
		user.setUserId(USER_ID);
		userDAO.saveOrUpdate(user);

		User userToFind = new User();
		userToFind.setUserId(USER_ID);

		UserDAOResponse response = userDAO.get(userToFind);

		Assert.assertEquals(UserDAOStatus.SUCCESS, response.getStatus());
		Assert.assertEquals(USER_ID, response.getUser().getUserId());
	}
	
	@Test
	public void shouldUpdateAUser() {

		User user = new User();
		user.setUserId(USER_ID);
		userDAO.saveOrUpdate(user);

		User userToFind = new User();
		userToFind.setUserId(USER_ID);

		UserDAOResponse response = userDAO.get(userToFind);

		Assert.assertNull(response.getUser().getUserInformation());
		
		User userFound = response.getUser();
		userFound.setUserInformation(new UserInformation());
		
		userDAO.saveOrUpdate(userFound);
		
		response = userDAO.get(userToFind);
		
		Assert.assertNotNull(response.getUser().getUserInformation());
		
	}
}
