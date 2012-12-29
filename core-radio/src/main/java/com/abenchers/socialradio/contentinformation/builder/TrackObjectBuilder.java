package com.abenchers.socialradio.contentinformation.builder;

import org.springframework.stereotype.Component;

import com.abenchers.socialradio.common.Track;

@Component("trackBuilder")
public class TrackObjectBuilder{

	private Track track;

	public TrackObjectBuilder() {
		track = new Track();
	}

	/* (non-Javadoc)
	 * @see com.abenchers.socialradio.contentinformation.builder.TrackBuilder#build()
	 */
	public Track build() {
		return this.track;
	}

	public TrackObjectBuilder withAlbum(final String album) {
		track.setAlbum(album);
		return this;
	}

	public TrackObjectBuilder withAlbumPerformer(final String albumPerformer) {
		track.setAlbumPerformer(albumPerformer);
		return this;
	}

	public TrackObjectBuilder withBitrate(final String bitrate) {
		track.setBitrate(bitrate);
		return this;
	}

	public TrackObjectBuilder withDuration(final String duration) {
		track.setDuration(duration);
		return this;
	}

	public TrackObjectBuilder withFileSize(final String fileSize) {
		track.setFileSize(fileSize);
		return this;
	}

	public TrackObjectBuilder withFormat(final String format) {
		track.setFormat(format);
		return this;
	}

	public TrackObjectBuilder withFullURL(final String url) {
		track.setFullURL(url);
		return this;
	}

	public TrackObjectBuilder withGenre(final String genre) {
		track.setGenre(genre);
		return this;
	}

	public TrackObjectBuilder withPerformer(final String performer) {
		track.setPerformer(performer);
		return this;
	}

	public TrackObjectBuilder withTitle(final String title) {
		track.setTitle(title);
		return this;
	}

	public TrackObjectBuilder withTrackId(final String trackId) {
		track.setTrackId(trackId);
		return this;
	}

}
