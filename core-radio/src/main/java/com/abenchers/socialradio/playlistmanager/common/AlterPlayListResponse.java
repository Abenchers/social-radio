package com.abenchers.socialradio.playlistmanager.common;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.elasticsearch.common.collect.Lists;

import com.abenchers.socialradio.playlistmanager.entity.UserTrack;
@XmlRootElement
public class AlterPlayListResponse {

	private AlterPlayListResponseStatus status;
	private String description;
	private List<UserTrack> trackIds;

	public AlterPlayListResponseStatus getStatus() {
		return status;
	}

	public void setStatus(AlterPlayListResponseStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserTrack> getTrackIds() {
		if (trackIds == null)
			trackIds = Lists.newArrayList();
		return trackIds;
	}

	public void setTrackIds(List<UserTrack> trackIds) {
		this.trackIds = trackIds;
	}

}
