package com.abenchers.socialradio.playback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingPlaylistResponse;
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;
import com.abenchers.socialradio.streaming.service.StreamingService;

@Component("playbackService")
public class PlaybackServiceImpl implements PlaybackService {

	@Autowired
	private StreamingService streamingService;

	@Override
	public StreamingResponse add(StreamingAddRequest streamingAddRequest) {
		return streamingService.add(streamingAddRequest);
	}

	@Override
	public StreamingStartChannelResponse start(
			StreamingActionRequest streamingActionRequest) {
		return streamingService.start(streamingActionRequest);
	}

	@Override
	public StreamingResponse clear(StreamingActionRequest streamingActionRequest) {
		return streamingService.clear(streamingActionRequest);
	}

	@Override
	public StreamingResponse play(StreamingActionRequest streamingActionRequest) {
		return streamingService.play(streamingActionRequest);
	}

	@Override
	public StreamingResponse stop(StreamingActionRequest streamingActionRequest) {
		return streamingService.stop(streamingActionRequest);
	}

	@Override
	public StreamingPlaylistResponse getPlaylist(
			StreamingActionRequest streamingActionRequest) {
		return streamingService.getPlaylist(streamingActionRequest);
	}

	@Override
	public StreamingRemainingTimeResponse getRemainingTime(
			StreamingActionRequest streamingActionRequest) {
		return streamingService.getRemainingTime(streamingActionRequest);
	}

	@Override
	public StreamingStatusResponse isPlaying(
			StreamingActionRequest streamingActionRequest) {
		return streamingService.isPlaying(streamingActionRequest);
	}

	public void setStreamingService(StreamingService streamingService) {
		this.streamingService = streamingService;
	}

}
