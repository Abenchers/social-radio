package com.abenchers.socialradio.playlistmanager.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserTrack {

	private String trackId;
	private Long votes;
	private String trackName;

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public Long getVotes() {
		return votes;
	}

	public void setVotes(Long votes) {
		this.votes = votes;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trackId == null) ? 0 : trackId.hashCode());
		result = prime * result
				+ ((trackName == null) ? 0 : trackName.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		UserTrack other = (UserTrack) obj;
		if (obj == null) {
			return false;
		}
		return (getClass() != obj.getClass()) ? false : trackId
				.equals(other.trackId);
	}

}
