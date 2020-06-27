package com.geronimodesenvolvimentos.gameservice.services.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;

import org.springframework.stereotype.Component;
@Component
public class NewRemoteGameObjectService implements GraphQLMutationResolver{
    private RemoteGameObjectManagement remoteGameObjectManagement;

    public NewRemoteGameObjectService(RemoteGameObjectManagement rgoManagement){
        System.out.println("NewRemoteGameObjectService");
        this.remoteGameObjectManagement = rgoManagement;
    }
    
    //   newRemoteGameObject(position:Vector3Input, speed:Vector3Input):RemoteGameObject!
    public RemoteGameObject newRemoteGameObject(Vector3 position, Vector3 speed, Vector3 orientation, 
    String prefabType, String parentId){
        return remoteGameObjectManagement.createNew(position, speed, orientation, prefabType, parentId);
    }
}