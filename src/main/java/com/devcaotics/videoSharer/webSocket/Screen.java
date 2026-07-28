package com.devcaotics.videoSharer.webSocket;

import org.springframework.stereotype.Component;

import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/screen/{id}")
public class Screen extends GeneralEndpoint{
	
}
