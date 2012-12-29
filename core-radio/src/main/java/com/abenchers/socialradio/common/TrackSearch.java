package com.abenchers.socialradio.common;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackSearch extends BaseRequest {

	private List<String> fieldToSearch;

	public List<String> getFieldToSearch() {
		return fieldToSearch;
	}

	public void setFieldToSearch(final List<String> fieldToSearch) {
		this.fieldToSearch = fieldToSearch;
	}

}
