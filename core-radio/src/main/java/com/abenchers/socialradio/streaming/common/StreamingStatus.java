package com.abenchers.socialradio.streaming.common;

public enum StreamingStatus {
	OK("ok"), FAIL("fail");

	private String code;

	StreamingStatus(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
