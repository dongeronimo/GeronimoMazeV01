package com.geronimodesenvolvimentos.gameservice.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import lombok.var;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RemoteGameObjectsPersistenceTests {
    @LocalServerPort
	private int port;
    @Autowired
    private RemoteGameObjectPersistence persistence;
    @Test
    void canAdd(){
        var qtd0 = persistence.getCount();
        persistence.addRemoteGameObject(new RemoteGameObject());
        var qtd1 = persistence.getCount();
        assertTrue(qtd1 == qtd0 + 1);
    }

    @Test
    void canFind(){
        var rgo = persistence.addRemoteGameObject(new RemoteGameObject());
        var found = persistence.findGameObjectById(rgo.getId());
        assertTrue(rgo.equals(found));
    }
    @Test
    void canDelete(){
        var rgo = persistence.addRemoteGameObject(new RemoteGameObject());
        var count = persistence.getCount();
        persistence.deleteGameObject(rgo.getId());
        var newCount = persistence.getCount();
        assertTrue(newCount == count -1);
    }
}