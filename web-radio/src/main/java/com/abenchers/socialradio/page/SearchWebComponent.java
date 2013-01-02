package com.abenchers.socialradio.page;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.Track;
import com.abenchers.socialradio.common.TrackSearch;
import com.abenchers.socialradio.common.TracksFound;
import com.abenchers.socialradio.resourcemanager.service.ResourceManagerContentInformation;

@ManagedBean(name = "searchWebComponent")
@Component
@SessionScoped
public class SearchWebComponent {
	
	@Autowired
	//@ManagedProperty(value="#{ResourceManagerContentInformation}")
	private ResourceManagerContentInformation resourceManager;

	
	private List<String> list;
	
	public List<Track> search(String searchField) {
		TrackSearch trackSearch = new TrackSearch();

		List<String> fieldsToSearch = Lists
				.newArrayList(searchField.split(" "));
		trackSearch.setFieldToSearch(fieldsToSearch);
		TracksFound tracksFound = resourceManager.search(trackSearch);
		List<Track> tracksList = tracksFound.getTracks();

		list = Lists.newArrayList();
		for (Track track : tracksList) {
			list.add(track.getTitle());
		}
		
		return tracksList;

	}

}
