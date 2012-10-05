package com.abenchers.socialradio.streaming.vlcimpl;

import java.io.IOException;

public class ServerVLC {
	private static final String VLC_EXE_URL = "C:\\vlc\\vlc.exe";
	private static final String PASSWORD = "admin";

	public void start() {
		String[] command = { "cmd", "/c",
				VLC_EXE_URL + " -I telnet --telnet-password " + PASSWORD };
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
