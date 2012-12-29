package com.abenchers.socialradio.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IndexTrack extends BaseRequest {

	private String source;
	private SourceType sourceType;
	private Boolean allowIndexNoTag;
	private String trackId;

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	public Boolean getAllowIndexNoTag() {
		return allowIndexNoTag;
	}

	public void setAllowIndexNoTag(final Boolean allowIndexNoTag) {
		this.allowIndexNoTag = allowIndexNoTag;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(final String trackId) {
		this.trackId = trackId;
	}

}
