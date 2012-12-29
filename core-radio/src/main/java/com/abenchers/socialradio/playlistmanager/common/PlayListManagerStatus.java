package com.abenchers.socialradio.playlistmanager.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum PlayListManagerStatus {

	ADD("add song"), NEGATIVE_VOTE("removeVoteToSong"), POSITIVE_VOTE(
			"addVoteToSong"), LIST("list"), START_USER("startUser");

	private String code;

	PlayListManagerStatus(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
