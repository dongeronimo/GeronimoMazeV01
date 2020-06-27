package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.objectUpdate;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ObjectUpdateService {
    public static final String messageType = "objectUpdate";

	public boolean isForMe(String payload) {
		return false;
	}

	public void handleMessage(WebSocketSession session, String payload) {
	}
}