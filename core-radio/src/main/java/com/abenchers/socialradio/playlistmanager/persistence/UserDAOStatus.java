package com.abenchers.socialradio.playlistmanager.persistence;

public enum UserDAOStatus {

	SUCCESS("success"), USER_NOT_EXIST("user dont exist"), DB_ERROR_ACCESS(
			"cant connect to database"), FAIL("fail");

	private String code;

	UserDAOStatus(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
