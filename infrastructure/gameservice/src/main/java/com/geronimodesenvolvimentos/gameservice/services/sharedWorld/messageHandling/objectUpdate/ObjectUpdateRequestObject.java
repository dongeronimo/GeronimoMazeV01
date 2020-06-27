package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.objectUpdate;

import com.geronimodesenvolvimentos.gameservice.model.Vector3;

public class ObjectUpdateRequestObject {
    private String type;
    private String objectId;
    private Vector3 position;
    private Vector3 speed;
    private Vector3 orientation;
    private String clientWebsocketId;
	public String getType() {
		return type;
	}
	public String getClientWebsocketId() {
		return clientWebsocketId;
	}
	public void setClientWebsocketId(String clientWebsocketId) {
		this.clientWebsocketId = clientWebsocketId;
	}
	public Vector3 getOrientation() {
		return orientation;
	}
	public void setOrientation(Vector3 orientation) {
		this.orientation = orientation;
	}
	public Vector3 getSpeed() {
		return speed;
	}
	public void setSpeed(Vector3 speed) {
		this.speed = speed;
	}
	public Vector3 getPosition() {
		return position;
	}
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}