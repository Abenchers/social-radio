package org.playback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
@Controller
@RequestMapping("/player")
public class playbackServiceRest {
	
	@RequestMapping(value="/play", method = RequestMethod.POST)
	public String getUserConnection(@PathVariable String userId, String listName, HttpServletRequest request) {
		//Pido el proximo tema, sea radio o wishlist, creo el canal, 
		//se lo mando al streaming y retorno la url
		return "httpTrack"; 
	}
	
	@RequestMapping(value="/stop", method = RequestMethod.POST)
	public boolean stopPlayer(@PathVariable String userId, String listName, HttpServletRequest request) {
		// envio al streaming el comnado de stop
		return true; 
	}
	
	@RequestMapping(value="/next", method = RequestMethod.POST)
	public boolean nextPlayer(@PathVariable String userId, String listName, HttpServletRequest request) {
		// envio al streaming el comnado de next
		return true; 
	}
	
	@RequestMapping(value="/player", method = RequestMethod.POST)
	public boolean volumePlayer(@PathVariable String userId, String listName, int volume, HttpServletRequest request) {
		// cambiar el volumen del player
		return true; 
	}

}
