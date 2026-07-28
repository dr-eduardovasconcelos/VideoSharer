package com.devcaotics.videoSharer.webSocket;

import java.nio.ByteBuffer;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class ByteEncoder implements Encoder.Binary<ByteBuffer>{

	@Override
	public ByteBuffer encode(ByteBuffer object) throws EncodeException {
		// TODO Auto-generated method stub
		return object;
	}

}
