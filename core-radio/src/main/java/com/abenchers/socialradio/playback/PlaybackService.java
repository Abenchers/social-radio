package com.abenchers.socialradio.playback;

import com.abenchers.socialradio.streaming.common.StreamingActionRequest;
import com.abenchers.socialradio.streaming.common.StreamingAddRequest;
import com.abenchers.socialradio.streaming.common.StreamingPlaylistResponse;
import com.abenchers.socialradio.streaming.common.StreamingRemainingTimeResponse;
import com.abenchers.socialradio.streaming.common.StreamingResponse;
import com.abenchers.socialradio.streaming.common.StreamingStartChannelResponse;
import com.abenchers.socialradio.streaming.common.StreamingStatusResponse;

public interface PlaybackService {

	StreamingResponse add(StreamingAddRequest streamingAddRequest);

	StreamingStartChannelResponse start(
			StreamingActionRequest streamingActionRequest);

	StreamingResponse clear(StreamingActionRequest streamingActionRequest);

	StreamingResponse play(StreamingActionRequest streamingActionRequest);

	StreamingResponse stop(StreamingActionRequest streamingActionRequest);

	StreamingPlaylistResponse getPlaylist(
			StreamingActionRequest streamingActionRequest);

	StreamingRemainingTimeResponse getRemainingTime(
			StreamingActionRequest streamingActionRequest);

	StreamingStatusResponse isPlaying(
			StreamingActionRequest streamingActionRequest);

}
