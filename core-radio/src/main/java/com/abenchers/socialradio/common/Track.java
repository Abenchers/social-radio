package com.abenchers.socialradio.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Track {

	private String trackId;
	private String duration;
	private String fileSize;
	private String format; // EG: OGG, mpeg
	private String fullURL;
	private String bitrate;
	private String title;
	private String album;
	private String performer;
	private String albumPerformer;
	private String genre;
	

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFullURL() {
		return fullURL;
	}

	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}

	public String getBitrate() {
		return bitrate;
	}

	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getAlbumPerformer() {
		return albumPerformer;
	}

	public void setAlbumPerformer(String albumPerformer) {
		this.albumPerformer = albumPerformer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
