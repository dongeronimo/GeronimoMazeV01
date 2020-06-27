package com.geronimodesenvolvimentos.gameservice.services.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class GetRemoteGameObjectByIdService implements GraphQLQueryResolver{
    @Autowired
    private RemoteGameObjectManagement manager;

    public RemoteGameObject getRemoteGameObjectById(String id){
        return manager.find(id);
    }
}