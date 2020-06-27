package com.geronimodesenvolvimentos.gameservice;
import java.util.HashMap;
import java.util.Map;

import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.objectUpdate.ObjectUpdateService;
import com.geronimodesenvolvimentos.gameservice.stub.WebSocketSessionStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class ObjectUpdateServiceTests {
    @Autowired
    private ObjectUpdateService objectUpdateService;
    @Autowired
    private ClientManagement clientManager;

    private String requestJsonForObjectUpdate;
    private String requestJsonForSomethingElse;
    private WebSocketSessionStub session;
    @BeforeEach
    void setUp(){
        session = new WebSocketSessionStub("foobar");
        clientManager.insertNewClient(session);
        Map<String, String> map = new HashMap<>();
        map.put("type", ObjectUpdateService.messageType);
        
    }
    @Test
    void testIsForMe(){
        boolean isForMe = objectUpdateService.isForMe(requestJsonForObjectUpdate);
    }
    @Test
    void refusesMessagesThatAreForOthers(){

    }
    @Test
    void isForMeDealsWithMalformedJson(){

    }
    @Test
    void isForMeDealsWithLackingTypeField(){

    }
    @Test
    void canHandleMessages(){

    }
}