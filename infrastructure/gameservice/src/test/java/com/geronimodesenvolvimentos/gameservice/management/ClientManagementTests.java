package com.geronimodesenvolvimentos.gameservice.management;

import static org.junit.jupiter.api.Assertions.assertTrue;


import com.geronimodesenvolvimentos.gameservice.stub.WebSocketSessionStub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientManagementTests {
    @Autowired
    private ClientManagement manager;
    @Test
    void canCreateClient(){
        WebSocketSessionStub session = new WebSocketSessionStub();
        int c0 = manager.getCount();
        manager.insertNewClient(session);
        int c1 = manager.getCount();
        assertTrue(c1 == c0 + 1);
    }
    @Test
    void canRemoveClient(){
        WebSocketSessionStub session = new WebSocketSessionStub();
        manager.insertNewClient(session);
        int c0 = manager.getCount();
        manager.removeClient(session.getId());
        int c1 = manager.getCount();
        assertTrue(c1 == c0 - 1);
    }
}