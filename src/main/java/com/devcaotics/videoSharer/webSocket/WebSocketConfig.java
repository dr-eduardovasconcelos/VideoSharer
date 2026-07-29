package com.devcaotics.videoSharer.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


public class WebSocketConfig {
	
	
	public ServerEndpointExporter serverEndPointExporter() {
		return new ServerEndpointExporter();
	}

}
