package com.abenchers.socialradio.playlistmanager.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;

@Component("listSongsFromUser")
public class ListSongsFromUserHandler implements PlayListManagerHandler {

	@Autowired
	private UserDAO userDAO;

	@Override
	public AlterPlayListResponse process(AlterPlayListRequest request) {
		final AlterPlayListResponse response = new AlterPlayListResponse();
		response.setStatus(AlterPlayListResponseStatus.USER_DONT_EXIST);
		final User userTofind = new User();
		userTofind.setUserId(request.getUserId());
		final UserDAOResponse userDAOResponse = userDAO.get(userTofind);
		final User user = userDAOResponse.getUser();
		if (user != null) {
			response.setStatus(AlterPlayListResponseStatus.SUCCESS);

			for (UserTrack userTrack : user.getTrackList().listTracks()) {
				response.getTrackIds().add(userTrack);
			}
		}

		return response;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
