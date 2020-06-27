package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld;

import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;

public class CreateNewWorldResponseObject {
	private RemoteGameObject rootObject;
	private String type;
	public CreateNewWorldResponseObject(RemoteGameObject rootObject) {
		this.setRootObject(rootObject);
		setType(CreateNewWorldService.messageType);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public RemoteGameObject getRootObject() {
		return rootObject;
	}
	public void setRootObject(RemoteGameObject rootObject) {
		this.rootObject = rootObject;
	}
    
}