package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId;

public class GetWebsocketIdRequestObject {
    private String type = GetWebSocketIdService.messageType;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}