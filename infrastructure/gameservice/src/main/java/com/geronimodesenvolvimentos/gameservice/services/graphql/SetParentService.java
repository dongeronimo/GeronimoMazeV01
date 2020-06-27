package com.geronimodesenvolvimentos.gameservice.services.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetParentService implements GraphQLMutationResolver{
    @Autowired
    private RemoteGameObjectManagement remoteGameObjectManagement;

    public RemoteGameObject setParent(String parentId, String childId){
        RemoteGameObject parent = remoteGameObjectManagement.find(parentId);
        RemoteGameObject child = remoteGameObjectManagement.find(childId);
        remoteGameObjectManagement.setParent(parent, child);
        return parent;
    }
}