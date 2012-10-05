package com.abenchers.socialradio.streaming;

import com.abenchers.socialradio.streaming.vlcimpl.CommandVlcImpl;
import com.abenchers.socialradio.streaming.vlcimpl.ServerVLC;

//Recordar tener instalo el vlc y modificar el parametro VLC_EXE_URL en la clase ServerVLC
//Esta clase se va a borrar en un futuro y solo  se genera con fines de desarrollo

public class TestVlc {

	private static final String C_VLC_A_MP3 = "c:\\vlc\\a.mp3";
	private static final String RADIO = "radio";

	public static void main(String[] args) throws Exception {

		// Se inicia el server
		ServerVLC server = new ServerVLC();
		server.start();
		// se genera una interface de comando
		CommandInterface command = new CommandVlcImpl();
		// se genera un canal
		String url = command.start(RADIO);
		// se agrega un tema
		command.add(RADIO, C_VLC_A_MP3);
		// play al canal
		command.play(RADIO);
		// Ver playlist
		Thread.sleep(1000);
		System.out.print(command.getPlaylist(RADIO));
		// Verificar si esta corriendo un tema en el canal
		System.out.print(command.isPlaying(RADIO));
		// Verificar cuanto queda para que termine el tema
		Thread.sleep(1000);
		System.out.print(command.getRemainingTime(RADIO));
		// Se verifica que al intentar generar un canal ya generado no genere uno nuevo y retorne la url del que ya esta generado
		String url2 = command.start(RADIO);
		

	}

}
