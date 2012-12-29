package com.abenchers.socialradio.playlistmanager.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum AlterPlayListResponseStatus {

	SUCCESS("success"), FAIL("fail"), USER_DONT_EXIST("user dont exit"), TRACK_DOES_NOT_EXIST(
			"track dont exit"), WARNING("warning"), ;

	private String code;

	AlterPlayListResponseStatus(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
