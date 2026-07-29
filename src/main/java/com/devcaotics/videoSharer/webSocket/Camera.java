package com.devcaotics.videoSharer.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/ws/camera/{id}")
public class Camera extends GeneralEndpoint{

	private List<Screen> screens = new ArrayList<Screen>();
	
	@OnMessage(maxMessageSize = 5000000)
	public void transmitVideo(Session s, byte[] video) {
		
		if(!screens.isEmpty())
			screens.stream().forEach(screen -> {
				try {
					screen.getSession().getBasicRemote().sendObject(video);
				} catch (IOException | EncodeException e) {
					// TODO Auto-generated catch block
					
				}
			});
		
	}

	public List<Screen> getScreens() {
		return screens;
	}
	
	
	
}
