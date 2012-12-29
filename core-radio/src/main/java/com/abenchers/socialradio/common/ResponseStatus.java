package com.abenchers.socialradio.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum ResponseStatus {

	SUCCESS("success"), FAIL("fail"), WARNING("warning"), SOURCE_NOT_FOUND(
			"source not found"), ERROR_INDEXING("could not index"), SOURCE_NOT_SUPPORT(
			"source is not suported"), ERROR_NO_METADATA_FOUND(
			"can't retrieve metadata from track"), TRACK_NOT_FOUND(
			"The track has not been found"), USER_DONT_EXIST("user dont exit"), TRACK_DONT_EXIST(
			"track dont exit");

	private String code;

	ResponseStatus(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
