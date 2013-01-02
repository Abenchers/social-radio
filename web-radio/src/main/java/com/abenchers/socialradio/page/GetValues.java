package com.abenchers.socialradio.page;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.elasticsearch.common.collect.Lists;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.playlistmanager.common.PlayListManagerStatus;
import com.abenchers.socialradio.playlistmanager.entity.UserTrack;

@ManagedBean
@SessionScoped
public class GetValues {

	private static final String SOCIAL_LIST = "socialList";

	@ManagedProperty(value = "#{searchWebComponent}")
	private SearchWebComponent searchWebComponent;

	@ManagedProperty(value = "#{updateTrackListWebComponent}")
	private UpdateTrackListWebComponent updateTrackListWebComponent;
	
	@ManagedProperty(value = "#{addSongToPlayListWebComponent}")
	private AddSongToPlayListWebComponent addSongToPlayListWebComponent;
	
	@ManagedProperty(value = "#{voteSongWebComponent}")
	private VoteSongWebComponent voteSongWebComponent;
	
	@ManagedProperty(value = "#{startUserWebComponent}")
	private StartUserWebComponent startUserWebComponent;


	private String selected;

	private String fileToAdd;

	private String searchField;

	private List<Track> tracksList;

	private List<UserTrack> playListSongs = Lists.newArrayList();

	private List<String> selectedSongs;

	private List<String> selectedSongsToVote;
	
	private List<String> foundSongs;

	private String streamingUrl;

	public void search() {
		tracksList = searchWebComponent.search(searchField);
	}

	public void updateTrackList() {

		playListSongs = updateTrackListWebComponent
				.updateTrackList(SOCIAL_LIST);
	}

	public void addSongToPlaylist() {

		addSongToPlayListWebComponent.addSongToPlaylist(selectedSongs);
	}

	public void votePositiveSongs() {

		voteSongWebComponent.votePositiveSongs(selectedSongsToVote, SOCIAL_LIST, PlayListManagerStatus.POSITIVE_VOTE);
	}

	public void voteNegativeSongs() {

		voteSongWebComponent.votePositiveSongs(selectedSongsToVote, SOCIAL_LIST, PlayListManagerStatus.NEGATIVE_VOTE);
	}

	public String streamingUrl() {
		return startUserWebComponent.streamingUrl();
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


	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}


	public List<Track> getTracksList() {
		return tracksList;
	}

	public void setTracksList(List<Track> tracksList) {
		this.tracksList = tracksList;
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
		return streamingUrl();
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

	public SearchWebComponent getSearchWebComponent() {
		return searchWebComponent;
	}

	public void setSearchWebComponent(SearchWebComponent searchWebComponent) {
		this.searchWebComponent = searchWebComponent;
	}

	public UpdateTrackListWebComponent getUpdateTrackListWebComponent() {
		return updateTrackListWebComponent;
	}

	public void setUpdateTrackListWebComponent(
			UpdateTrackListWebComponent updateTrackListWebComponent) {
		this.updateTrackListWebComponent = updateTrackListWebComponent;
	}

	public AddSongToPlayListWebComponent getAddSongToPlayListWebComponent() {
		return addSongToPlayListWebComponent;
	}

	public void setAddSongToPlayListWebComponent(
			AddSongToPlayListWebComponent addSongToPlayListWebComponent) {
		this.addSongToPlayListWebComponent = addSongToPlayListWebComponent;
	}

	public VoteSongWebComponent getVoteSongWebComponent() {
		return voteSongWebComponent;
	}

	public void setVoteSongWebComponent(VoteSongWebComponent voteSongWebComponent) {
		this.voteSongWebComponent = voteSongWebComponent;
	}

	public StartUserWebComponent getStartUserWebComponent() {
		return startUserWebComponent;
	}

	public void setStartUserWebComponent(StartUserWebComponent startUserWebComponent) {
		this.startUserWebComponent = startUserWebComponent;
	}

	public List<String> getFoundSongs() {
		return foundSongs;
	}

	public void setFoundSongs(List<String> foundSongs) {
		this.foundSongs = foundSongs;
	}
	
	

}
