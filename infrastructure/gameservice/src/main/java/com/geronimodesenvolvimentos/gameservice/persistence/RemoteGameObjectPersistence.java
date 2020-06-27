package com.geronimodesenvolvimentos.gameservice.persistence;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import com.geronimodesenvolvimentos.gameservice.model.*;
@Component
public class RemoteGameObjectPersistence {
    private static Map<String, RemoteGameObject> remoteGameObjects;

    private void createStorageIfNull(){
        if(remoteGameObjects == null){
            remoteGameObjects = new HashMap<>();
        }
    }
    public int getCount(){
        createStorageIfNull();
        return remoteGameObjects.size();
    }
    public RemoteGameObject addRemoteGameObject(RemoteGameObject go){
        createStorageIfNull();
        remoteGameObjects.put(go.getId(), go);
        return remoteGameObjects.get(go.getId());
    }
    public RemoteGameObject findGameObjectById(String id){
        createStorageIfNull();
        return remoteGameObjects.get(id);
    }
    public void deleteGameObject(String id){
        createStorageIfNull();
        remoteGameObjects.remove(id);
    }
}