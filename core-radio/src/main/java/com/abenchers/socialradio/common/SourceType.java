package com.abenchers.socialradio.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum SourceType {
	FILE("file"), STREAM("stream");

	private String code;

	SourceType(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
