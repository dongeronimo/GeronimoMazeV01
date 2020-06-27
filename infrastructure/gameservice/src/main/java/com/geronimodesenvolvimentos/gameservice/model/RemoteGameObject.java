package com.geronimodesenvolvimentos.gameservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RemoteGameObject {
    private String id;
    private Vector3 position;
    private Vector3 speed;
    private Vector3 orientation;
    private RemoteGameObject parent;
    private List<RemoteGameObject> children;
    private String prefabType;
    
    public RemoteGameObject(){
        id = UUID.randomUUID().toString();
        position = new Vector3();
        speed = new Vector3();
        setOrientation(new Vector3(0,0,1));
        setParent(null);
        setChildren(new ArrayList<>());
        setPrefabType("None");
    }
	public RemoteGameObject(String id, Vector3 position, Vector3 speed, Vector3 orientation, RemoteGameObject parent, List<RemoteGameObject> children, String prefabType){
        this.id = id;
        this.position = position;
        this.speed = speed;
        this.setOrientation(orientation);
        this.setParent(parent);
        this.setChildren(children);
        this.setPrefabType(prefabType);
    }
    public String getPrefabType() {
		return prefabType;
	}
	public void setPrefabType(String prefabType) {
		this.prefabType = prefabType;
	}

    public List<RemoteGameObject> getChildren() {
		return children;
	}
	public void setChildren(List<RemoteGameObject> children) {
		this.children = children;
	}
	public RemoteGameObject getParent() {
		return parent;
	}
	public void setParent(RemoteGameObject parent) {
		this.parent = parent;
	}
	public Vector3 getOrientation() {
		return orientation;
	}
	public void setOrientation(Vector3 orientation) {
		this.orientation = orientation;
	}
    @Override
    public boolean equals(Object o){
        if(o == null || o instanceof RemoteGameObject == false){
            return false;
        }else{
            RemoteGameObject other = (RemoteGameObject)o;
            return other.id.equals(this.id);
        }
    }
    @Override
    public int hashCode(){
        return id.hashCode();
    }
    public String getId() {
		return id;
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
	public void setId(String id) {
		this.id = id;
	}
       
    
}