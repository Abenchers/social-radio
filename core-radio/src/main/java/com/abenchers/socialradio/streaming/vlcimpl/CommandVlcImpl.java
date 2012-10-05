package com.abenchers.socialradio.streaming.vlcimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.abenchers.socialradio.streaming.CommandInterface;


public class CommandVlcImpl implements CommandInterface {
	private static final String PUBLIC_URL = "10.255.49.20:1234";
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
	private static final String LOCALHOST = "localhost";
	private static final String PASSWORD = "admin";
	private VLCControl vlc = new VLCControl();

	
	public void add(String channel, String file) throws Exception {
		connect();
		vlc.sendCommand(SETUP + channel + " input " + file);
	}

	
	public String start(String channel) throws Exception {
		String url = PUBLIC_URL + "/" + UUID.randomUUID();
		connect();
		String response=vlc.sendCommand(NEW + channel + " broadcast enabled");
		if(response.contains("Name already in use")){
			url="";
			String[] splitString = response.split(NEW_LINE);
			for (String string : splitString) {
				if(string.contains("output")){
					String[] song = string.split("dst=");
					url=song[1].trim();
				}
			}
			return "http://" + url;
		}else{
			vlc.sendCommand(SETUP + channel + OUTPUT_STANDARD + url + "}");
			return "http://" + url;
		}
	}

	public void connect() throws Exception {
		if (!vlc.isConnected()) {
			vlc.connect(LOCALHOST, 4212);
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
			if (string.contains("input")) {
				listFlag = 1;
			}
			if (string.contains("output")) {
				listFlag = 0;
			}
			if (listFlag == 1 && !string.contains("input")) {
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

}
