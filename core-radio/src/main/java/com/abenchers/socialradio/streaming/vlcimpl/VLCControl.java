package com.abenchers.socialradio.streaming.vlcimpl;

import java.beans.PropertyChangeSupport;
import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;

public class VLCControl extends TelnetClient implements Runnable,
		TelnetNotificationHandler {

	public static final int VLC_PORT = 4212;
	private static VLCControl staticInstance = null;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	public VLCControl() {
	}

	@Override
	public void disconnect() throws IOException {
		sendCommand("logout");
		super.disconnect();
	}

	public void connect() throws IOException {
		if (isConnected()) {
			return;
		}
		staticInstance = this;
		try {
			staticInstance.connect("localhost", VLC_PORT);
			Thread thread = new Thread(new VLCControl());
			thread.start();
			staticInstance.registerNotifHandler(this);
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						if (isConnected()) {
							disconnect();
						}
					} catch (IOException ex) {
					}
				}
			});
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	public String sendCommand(String s) throws IOException {
		if (!isConnected()) {
			connect();
		}
		if (s == null) {
			return null;
		}
		if (!s.endsWith("\n")) {
			s = s + "\n";
		}
		getOutputStream().write(s.getBytes());
		getOutputStream().flush();
		byte[] buff = new byte[1024];
		int ret_read = 0;

		do {
			try {
				ret_read = getInputStream().read(buff);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (ret_read > 0) {
				return new String(buff, 0, ret_read);
			}
		} while (ret_read >= 0);
		return s;

	}

	public static final String CLIENT_MESSAGE = "ClientMessage";

	
	public void run() {

	}

	
	public void receivedNegotiation(int negotiation_code, int option_code) {

	}

	public PropertyChangeSupport getSupport() {
		return support;
	}
}
