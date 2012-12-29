package com.abenchers.socialradio.streaming.vlcimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.abenchers.socialradio.streaming.service.CommandInterface;

public class CommandVlcImpl implements CommandInterface {
	private static final String INPUT = "input";
	private static final String HTTP = "http://";
	private static final String BROADCAST_ENABLED = " broadcast enabled";
	private static final String OUTPUT = "output";
	private static final String DST = "dst=";
	private static final String NAME_ALREADY_IN_USE = "Name already in use";

	private static final String SPLIT_CHAR = " : ";
	private static final String NEW_LINE = "\\r?\\n";
	private static final String OUTPUT_STANDARD = " output #standard{access=http,mux=ogg,dst=";
	private static final String NEW = "new ";
	private static final String SETUP = "setup ";
	private static final String INPUTDEL_ALL = " inputdel all";
	private static final String STOP = " stop";
	private static final String PLAY = " play";
	private static final String CONTROL = "control ";
	private static final String SHOW = "show ";

	private static final String PASSWORD = "admin";
	
	@Autowired
	private VLCControl vlc;

	private String vlcServiceStreamPublicURL;
	private String vlcServiceStreamPublicPort;
	private String vlcServiceStreamHost;

	public void add(String channel, String file) throws Exception {
		connect();
		vlc.sendCommand(SETUP + channel + " input " + file);
	}

	public String start(String channel) throws Exception {
		String url = vlcServiceStreamPublicURL +":" + vlcServiceStreamPublicPort
				+ "/" + UUID.randomUUID();
		connect();
		String response = vlc.sendCommand(NEW + channel + BROADCAST_ENABLED);
		if (response.contains(NAME_ALREADY_IN_USE)) {
			url = "";
			String[] splitString = response.split(NEW_LINE);
			for (String string : splitString) {
				if (string.contains(OUTPUT)) {
					String[] song = string.split(DST);
					url = song[1].trim();
				}
			}
			return HTTP + url;
		} else {
			vlc.sendCommand(SETUP + channel + OUTPUT_STANDARD + url + "}");
			return HTTP + url;
		}
	}

	public void connect() throws Exception {
		if (!vlc.isConnected()) {
			vlc.connect(vlcServiceStreamHost, 4212);
			vlc.sendCommand(PASSWORD);
		}
	}

	public void clear(String channel) throws Exception {
		connect();
		vlc.sendCommand(SETUP + channel + INPUTDEL_ALL);
	}

	public void play(String channel) throws Exception {
		connect();
		vlc.sendCommand(CONTROL + channel + PLAY);
	}

	public void stop(String channel) throws Exception {
		connect();
		vlc.sendCommand(CONTROL + channel + STOP);
	}

	public List<String> getPlaylist(String channel) throws Exception {
		connect();
		List<String> playlist = new ArrayList<String>();
		String response = vlc.sendCommand(SHOW + channel);
		String[] splitString = response.split(NEW_LINE);
		int listFlag = 0;

		for (String string : splitString) {
			if (string.contains(INPUT)) {
				listFlag = 1;
			}
			if (string.contains(OUTPUT)) {
				listFlag = 0;
			}
			if (listFlag == 1 && !string.contains(INPUT)) {
				String[] song = string.split(SPLIT_CHAR);
				playlist.add(song[1].trim());
			}
		}
		return playlist;
	}

	public Long getRemainingTime(String channel) throws Exception {
		connect();
		long timeL = 0;
		long lengthL = 0;
		String response = vlc.sendCommand(SHOW + channel);
		String[] splitString = response.split(NEW_LINE);

		for (String string : splitString) {
			if (string.contains("time")) {
				String[] time = string.split(SPLIT_CHAR);
				timeL = Long.parseLong(time[1].trim());
			}
			if (string.contains("length")) {
				String[] length = string.split(SPLIT_CHAR);
				lengthL = Long.parseLong(length[1].trim());
			}
		}

		return lengthL - timeL;
	}

	public boolean isPlaying(String channel) throws Exception {
		connect();
		String response = vlc.sendCommand(SHOW + channel);
		return response.contains("playing");
	}

	public String getVlcServiceStreamPublicURL() {
		return vlcServiceStreamPublicURL;
	}

	public void setVlcServiceStreamPublicURL(String vlcServiceStreamPublicURL) {
		this.vlcServiceStreamPublicURL = vlcServiceStreamPublicURL;
	}

	public String getVlcServiceStreamPublicPort() {
		return vlcServiceStreamPublicPort;
	}

	public void setVlcServiceStreamPublicPort(String vlcServiceStreamPublicPort) {
		this.vlcServiceStreamPublicPort = vlcServiceStreamPublicPort;
	}

	public String getVlcServiceStreamHost() {
		return vlcServiceStreamHost;
	}

	public void setVlcServiceStreamHost(String vlcServiceStreamHost) {
		this.vlcServiceStreamHost = vlcServiceStreamHost;
	}

}
