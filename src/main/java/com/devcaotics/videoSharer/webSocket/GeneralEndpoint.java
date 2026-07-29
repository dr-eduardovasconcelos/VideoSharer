package com.devcaotics.videoSharer.webSocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;

public class GeneralEndpoint {
	
	private String id;
	private Session session;
	
	@OnOpen
	public void open(Session session,@PathParam("id") String id) {
		this.id = id;
		this.session= session;
		
		ServiceHolder.addEndPoint(this);
		
	}
	
	@OnClose
	public void close(Session session) {
		ServiceHolder.byeEndpoint(this);
	}
	
	@OnError
	public void error(Throwable t) {
		close(this.session);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	

}
