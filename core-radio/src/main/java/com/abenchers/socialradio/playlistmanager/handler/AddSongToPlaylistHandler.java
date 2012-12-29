package com.abenchers.socialradio.playlistmanager.handler;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.contentinformation.service.ContentInformationService;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponse;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListResponseStatus;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOStatus;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

@Component("addSongToPlaylistHandler")
public class AddSongToPlaylistHandler implements PlayListManagerHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ContentInformationService contentInformationService;

	@Override
	public AlterPlayListResponse process(AlterPlayListRequest request) {
		final AlterPlayListResponse response = new AlterPlayListResponse();
		response.setStatus(AlterPlayListResponseStatus.SUCCESS);
		response.setDescription("The song has been added to the playlist");

		User userToFound = new User();
		userToFound.setUserId(request.getUserId());

		User userFound = userDAO.get(userToFound).getUser();
		if (userFound != null) {
			
			if(!Strings.isNullOrEmpty(request.getTrackId())){
				final TrackSearch trackSearch = new TrackSearch();
				trackSearch.setFieldToSearch(Lists.newArrayList(request.getTrackId()));
				final TracksFound tracksFound = contentInformationService.search(trackSearch);
				
				if(tracksFound.getTracks().size()==1){
				final UserTrack userTrack = new UserTrack();
				userTrack.setTrackId(request.getTrackId());
				userTrack.setVotes(0l);
				final Track uniqueFoundTrack = Iterables.getOnlyElement(tracksFound.getTracks());
				userTrack.setTrackName(uniqueFoundTrack.getPerformer() + " - " + uniqueFoundTrack.getTitle());
				userFound.getTrackList().addTrack(userTrack);

				UserDAOResponse daoResponse = userDAO.saveOrUpdate(userFound);
				setResponseStatus(response, daoResponse.getStatus());
				}else{
					response.setStatus(AlterPlayListResponseStatus.TRACK_DOES_NOT_EXIST);
					response.setDescription("The trackId does not exist");
				}
			} else{
				response.setStatus(AlterPlayListResponseStatus.FAIL);
				response.setDescription("The trackId Must not be empty");
			}

		} else {
			response.setStatus(AlterPlayListResponseStatus.FAIL);
			response.setDescription("The user doesn't exist");
		}
		return response;
	}

	private void setResponseStatus(AlterPlayListResponse response,
			UserDAOStatus status) {
		if (UserDAOStatus.USER_NOT_EXIST.equals(status)) {
			response.setStatus(AlterPlayListResponseStatus.USER_DONT_EXIST);
		}

	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setContentInformationService(
			ContentInformationService contentInformationService) {
		this.contentInformationService = contentInformationService;
	}

	
}
