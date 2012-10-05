package com.abenchers.socialradio.streaming;

import java.util.List;

public interface CommandInterface {

	public void add(String fileUrl, String fileID) throws Exception;

	public String start(String channel) throws Exception;

	public void clear(String channel) throws Exception;

	public void play(String channel) throws Exception;

	public void stop(String channel) throws Exception;

	public List<String> getPlaylist(String channel) throws Exception;

	public Long getRemainingTime(String channel) throws Exception;

	public boolean isPlaying(String channel) throws Exception;

}
