package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId;

public class GetWebsocketIdResponseObject {
    private String type;
    private String websocketId;
    private String time;
	public String getType() {
		return type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWebsocketId() {
		return websocketId;
	}
	public void setWebsocketId(String websocketId) {
		this.websocketId = websocketId;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}