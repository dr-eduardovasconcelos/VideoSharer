package com.devcaotics.videoSharer.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
	
	@Bean
	public ServerEndpointExporter serverEndPointExporter() {
		return new ServerEndpointExporter();
	}

}
