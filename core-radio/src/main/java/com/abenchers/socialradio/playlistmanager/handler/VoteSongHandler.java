package com.abenchers.socialradio.playlistmanager.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;

@Component("voteSongHandler")
public class VoteSongHandler implements PlayListManagerHandler {

	@Autowired
	private UserDAO userDAO;

	private static final String SOCIAL_LIST = "socialList";

	@Override
	public AlterPlayListResponse process(AlterPlayListRequest request) {
		final AlterPlayListResponse response = new AlterPlayListResponse();
		response.setStatus(AlterPlayListResponseStatus.SUCCESS);
		if (SOCIAL_LIST.equals(request.getUserId())) {
			final User userToFind = new User();
			userToFind.setUserId(SOCIAL_LIST);
			User user = userDAO.get(userToFind).getUser();

			if (!user.getTrackList().vote(request.getTrackId(),
					request.getAlterPlayListStatus())) {
				response.setStatus(AlterPlayListResponseStatus.TRACK_DOES_NOT_EXIST);
			}

			userDAO.saveOrUpdate(user);

		} else {
			response.setStatus(AlterPlayListResponseStatus.FAIL);
			response.setDescription("You only can vote to \"socialList\" ");
		}
		return response;
	}



	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
