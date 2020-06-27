package com.geronimodesenvolvimentos.gameservice.management;

import static org.junit.jupiter.api.Assertions.assertTrue;


import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RemoteGameObjectManagementTests {
    @LocalServerPort
	private int port;
    @Autowired
    private RemoteGameObjectManagement manager;
    @Test
    void canCreateNew(){
        RemoteGameObject go = manager.createNew(new Vector3(), new Vector3(), new Vector3(0,1,0), "ftype", null);
        assertTrue(go.getId()!=null);
        assertTrue(go.getPosition().equals(new Vector3()));
        assertTrue(go.getSpeed().equals(new Vector3()));
        assertTrue(go.getOrientation().equals(new Vector3(0,1,0)));
        assertTrue(go.getPrefabType().equals("ftype"));
        assertTrue(go.getParent() == null);
    }
    @Test
    void canSetParent(){
        RemoteGameObject parent = manager.createNew(new Vector3(), new Vector3(), new Vector3(0,1,0), "ftype", null);
        RemoteGameObject child = manager.createNew(new Vector3(), new Vector3(), new Vector3(0,1,0), "foobar", parent.getId());
        assertTrue(parent.getChildren().size() == 1);
        assertTrue(parent.getChildren().stream()
            .filter(go->go.getId().equals(child.getId())).count() == 1);
        assertTrue(child.getParent().equals(parent));
    }
    @Test
    void canCreateRoot(){
        RemoteGameObject worldRoot = manager.createNewWorld();
        assertTrue(worldRoot.getId().equals("ROOT"));
    }
}