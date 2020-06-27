package com.geronimodesenvolvimentos.gameservice.management;

import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;
import com.geronimodesenvolvimentos.gameservice.persistence.RemoteGameObjectPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoteGameObjectManagement {
    @Autowired
    private RemoteGameObjectPersistence remoteGameObjectPersistence;

	public RemoteGameObject createNew(Vector3 position, Vector3 speed, Vector3 orientation, String prefabType, String parentId) {
        RemoteGameObject newObj = new RemoteGameObject();
        newObj.setPosition(position);
        newObj.setSpeed(speed);
        newObj.setOrientation(orientation);
        newObj.setPrefabType(prefabType);
        if(parentId != null){
            setParent(remoteGameObjectPersistence.findGameObjectById(parentId), newObj);
        }else{
            newObj.setParent(null);
        }
        return remoteGameObjectPersistence.addRemoteGameObject(newObj);
    }
    
    public void setParent(RemoteGameObject parent, RemoteGameObject child){
        child.setParent(parent);
        parent.getChildren().add(child);
        
    }

	public RemoteGameObject find(String id) {
		return remoteGameObjectPersistence.findGameObjectById(id);
	}

	public RemoteGameObject createNewWorld() {
        RemoteGameObject worldRoot = new RemoteGameObject();
        worldRoot.setId("ROOT");
        remoteGameObjectPersistence.addRemoteGameObject(worldRoot);
        return worldRoot;
	}
    
}