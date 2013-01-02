package com.abenchers.socialradio.jsffiles;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.playlistmanager.common.AlterPlayListRequest;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.entity.TrackList;
import com.abenchers.socialradio.playlistmanager.entity.User;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAO;
import com.abenchers.socialradio.playlistmanager.persistence.UserDAOResponse;
import com.abenchers.socialradio.playlistmanager.service.PlayListManagerService;
import com.abenchers.socialradio.resourcemanager.localstorage.persistence.LocalResourceDAO;
import com.abenchers.socialradio.resourcemanager.service.ResourceManagerContentInformation;
import com.abenchers.socialradio.usermanager.common.UserManagerRequest;
import com.abenchers.socialradio.usermanager.common.UserManagerResponse;
import com.abenchers.socialradio.usermanager.service.UserManagerService;

//@Component
//@ManagedBean
//@SessionScoped
public class GetValues{


	private static final String SOCIAL_LIST = "socialList";

	@Autowired
	//@ManagedProperty(value="#{LocalResourceDAO}")
	private LocalResourceDAO localResource;

	@Autowired
	//@ManagedProperty(value="#{ResourceManagerContentInformation}")
	private ResourceManagerContentInformation resourceManager;

	@Autowired
	//@ManagedProperty(value="#{UserDAO}")
	private UserDAO userDAO;

	@Autowired
	//@ManagedProperty(value="#{PlayListManagerService}")
	private PlayListManagerService playListManagerService;

	@Autowired
	//@ManagedProperty(value="#{UserManagerService}")
	private UserManagerService userManagerService;

	private String selected;

	private List<String> list;

	private String fileToAdd;

	private String searchField;

	private List<Track> tracksList;

	private List<UserTrack> playListSongs = Lists.newArrayList();

	private List<String> selectedSongs;

	private List<String> selectedSongsToVote;

	private String streamingUrl;

	public void showSelectedFolder() {
		System.out.println("***************** folder selected " + fileToAdd);
	}

	public void search() {
		TrackSearch trackSearch = new TrackSearch();

		List<String> fieldsToSearch = Lists
				.newArrayList(searchField.split(" "));
		trackSearch.setFieldToSearch(fieldsToSearch);
		TracksFound tracksFound = resourceManager.search(trackSearch);
		tracksList = tracksFound.getTracks();

		list = Lists.newArrayList();
		for (Track track : tracksList) {
			list.add(track.getTitle());
		}

	}

	public void updateTrackList() {

		User userToFind = new User();
		userToFind.setUserId(SOCIAL_LIST);
		UserDAOResponse userDAOResponse = userDAO.get(userToFind);
		TrackList trackListFromUser = userDAOResponse.getUser().getTrackList();

		playListSongs = trackListFromUser.listTracks();
	}

	public void addSongToPlaylist() {

		AlterPlayListRequest request;
		for (String track : selectedSongs) {
			request = new AlterPlayListRequest();
			request.setTrackId(track);
			request.setUserId(SOCIAL_LIST);
			request.setAlterPlayListStatus(PlayListManagerStatus.ADD);
			playListManagerService.modifyPlayList(request);
		}
	}

	public void votePositiveSongs() {

		AlterPlayListRequest request;
		for (String trackId : selectedSongsToVote) {
			request = new AlterPlayListRequest();
			request.setTrackId(trackId);
			request.setUserId(SOCIAL_LIST);
			request.setAlterPlayListStatus(PlayListManagerStatus.POSITIVE_VOTE);
			playListManagerService.modifyPlayList(request);
		}
	}

	public void voteNegativeSongs() {

		AlterPlayListRequest request;
		for (String trackId : selectedSongsToVote) {
			request = new AlterPlayListRequest();
			request.setTrackId(trackId);
			request.setUserId(SOCIAL_LIST);
			request.setAlterPlayListStatus(PlayListManagerStatus.NEGATIVE_VOTE);
			playListManagerService.modifyPlayList(request);
		}
	}

	public String streamingUrl() {

		UserManagerRequest userManagerRequest = new UserManagerRequest();
		userManagerRequest.setUserId(SOCIAL_LIST);
		UserManagerResponse userManagerResponse = userManagerService
				.startUser(userManagerRequest);
		return userManagerResponse.getStreamingURL();
	}

	public LocalResourceDAO getLocalResource() {
		return localResource;
	}

	public void setLocalResource(LocalResourceDAO localResource) {
		this.localResource = localResource;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getFileToAdd() {
		return fileToAdd;
	}

	public void setFileToAdd(String fileToAdd) {
		this.fileToAdd = fileToAdd;
	}

	public List<String> getList() {
		list = localResource.listResources();
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public void setResourceManager(
			ResourceManagerContentInformation resourceManager) {
		this.resourceManager = resourceManager;
	}

	public List<Track> getTracksList() {
		return tracksList;
	}

	public void setTracksList(List<Track> tracksList) {
		this.tracksList = tracksList;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<UserTrack> getPlayListSongs() {
		return playListSongs;
	}

	public void setPlayListSongs(List<UserTrack> playListSongs) {
		this.playListSongs = playListSongs;
	}

	public List<String> getSelectedSongs() {
		return selectedSongs;
	}

	public void setSelectedSongs(List<String> selectedSongs) {
		this.selectedSongs = selectedSongs;
	}

	public String getStreamingUrl() {
		UserManagerRequest userManagerRequest = new UserManagerRequest();
		userManagerRequest.setUserId(SOCIAL_LIST);
		UserManagerResponse userManagerResponse = userManagerService
				.startUser(userManagerRequest);
		streamingUrl = userManagerResponse.getStreamingURL();
		return streamingUrl;
	}

	public void setStreamingUrl(String streamingUrl) {
		this.streamingUrl = streamingUrl;
	}

	public List<String> getSelectedSongsToVote() {
		return selectedSongsToVote;
	}

	public void setSelectedSongsToVote(List<String> selectedSongsToVote) {
		this.selectedSongsToVote = selectedSongsToVote;
	}

}
