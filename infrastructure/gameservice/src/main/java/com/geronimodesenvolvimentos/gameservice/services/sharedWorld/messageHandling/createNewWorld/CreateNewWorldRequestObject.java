package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld;

public class CreateNewWorldRequestObject {
    private String type = CreateNewWorldService.messageType;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}