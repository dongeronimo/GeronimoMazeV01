package com.geronimodesenvolvimentos.gameservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.geronimodesenvolvimentos.gameservice.model.Vector3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Vector3Tests {
    @LocalServerPort
	private int port;
    @Test
    void constructorIsOk(){
        float x = 1.0f;
        float y = 2.0f;
        float z = 3.0f;
        Vector3 newVec = new Vector3(x,y,z);
        assertEquals(x, newVec.getX());
        assertEquals(y, newVec.getY());
        assertEquals(z, newVec.getZ());
    }
    @Test
    void equalityIsOk(){
        Vector3 v0 = new Vector3(2.0f/3.0f, (float)Math.PI, (float)Math.sqrt(2));
        Vector3 v1 = new Vector3(2.0f/3.0f, (float)Math.PI, (float)Math.sqrt(2));
        assertTrue(v0.equals(v1));
    }
}