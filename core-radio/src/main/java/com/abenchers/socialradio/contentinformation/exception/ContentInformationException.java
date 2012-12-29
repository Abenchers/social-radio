package com.abenchers.socialradio.contentinformation.exception;

import com.abenchers.socialradio.common.ResponseStatus;

public class ContentInformationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5744724539957438922L;

	private Exception exception;

	private ResponseStatus status;

	public ContentInformationException(Exception exception,
			ResponseStatus status) {
		super();
		this.exception = exception;
		this.status = status;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

}
