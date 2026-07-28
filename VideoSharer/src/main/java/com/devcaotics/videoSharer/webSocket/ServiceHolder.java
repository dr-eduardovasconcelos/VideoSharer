package com.devcaotics.videoSharer.webSocket;

import java.util.HashMap;
import java.util.Map;

public class ServiceHolder {
	
	private static final Map<String, Camera> cameras;
	
	static {
		cameras = new HashMap<String, Camera>();
	}
	
	private static void addScreen(String cameraId, Screen s) {
		cameras.get(cameraId).getScreens().add(s);
	}
	
	private static void addCamera(String id, Camera c) {
		cameras.put(id, c);
	}
	
	public static void addEndPoint(GeneralEndpoint endpoint) {
		
		if (endpoint instanceof Camera) {
			addCamera(endpoint.getId(), (Camera)endpoint);
		}else {
			addScreen(endpoint.getId(), (Screen)endpoint);
		}
		
	}
	
	private static void byeCamera(Camera c) {
		cameras.remove(c.getId());
	}
	
	private static void byeScreen(Screen s) {
		cameras.get(s.getId()).getScreens().remove(s);
	}
	
	public static void byeEndpoint(GeneralEndpoint end) {
		if(end instanceof Camera) {
			byeCamera((Camera)end);
		}else {
			byeScreen((Screen)end);
		}
	}

}
