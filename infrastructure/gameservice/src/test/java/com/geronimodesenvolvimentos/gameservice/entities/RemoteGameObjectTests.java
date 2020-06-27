package com.geronimodesenvolvimentos.gameservice.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RemoteGameObjectTests {
    @LocalServerPort
	private int port;
    @Test
    void defaultConstructorIsOk(){
        RemoteGameObject go = new RemoteGameObject();
        assertNotNull(go.getId());
        assertTrue(go.getPosition().equals(new Vector3(0,0,0)));
        assertTrue(go.getSpeed().equals(new Vector3(0,0,0)));
        assertTrue(go.getOrientation().equals(new Vector3(0,0,1)));
        assertNull(go.getParent());
        assertTrue(go.getChildren().size() == 0);
        assertTrue(go.getPrefabType().equals("None"));
    }
    @Test
    void parameterConstructorIsOk(){
        RemoteGameObject go = new RemoteGameObject("foobar", new Vector3(1,2,3), 
        new Vector3(1,0,0), new Vector3(1,0,0), null, new ArrayList<RemoteGameObject>(), "flamengo");
        assertTrue(go.getId().equals("foobar"));
        assertTrue(go.getPosition().equals(new Vector3(1,2,3)));
        assertTrue(go.getSpeed().equals(new Vector3(1,0,0)));
        assertTrue(go.getOrientation().equals(new Vector3(1,0,0)));
        assertTrue(go.getParent() == null);
        assertTrue(go.getChildren().size() == 0);
        assertTrue(go.getPrefabType().equals("flamengo"));
    }
    @Test
    void equalityIsOk(){
        RemoteGameObject go1 = new RemoteGameObject("foobar", new Vector3(3,2,1), new Vector3(0,0,1), new Vector3(1,0,0), null, new ArrayList<RemoteGameObject>(), "flamengo");
        RemoteGameObject go2 = new RemoteGameObject("foobar", new Vector3(1,2,3), new Vector3(1,0,0), new Vector3(0,1,0), null, new ArrayList<RemoteGameObject>(), "flamengo");
        assertTrue(go1.equals(go2));
    }
}