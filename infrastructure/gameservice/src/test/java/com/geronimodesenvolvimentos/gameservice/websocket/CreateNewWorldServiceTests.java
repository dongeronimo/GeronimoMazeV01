package com.geronimodesenvolvimentos.gameservice.websocket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.model.Vector3;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld.CreateNewWorldResponseObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld.CreateNewWorldService;
import com.geronimodesenvolvimentos.gameservice.stub.WebSocketSessionStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateNewWorldServiceTests {
    private WebSocketSessionStub session;
    @Autowired
    private ClientManagement clientManagement;
    @Autowired
    private CreateNewWorldService createNewWorldService;

    private String requestJsonForCreateNewWorld;
    private String requestJsonForSomethingElse;

    @BeforeEach
    void setUp() throws JsonProcessingException{
        session = new WebSocketSessionStub("charles bronson");
        clientManagement.insertNewClient(session);
        Map<String, String> requestCreateNewWorldMap = new HashMap<>();
        requestCreateNewWorldMap.put("type", CreateNewWorldService.messageType);
        requestJsonForCreateNewWorld = new ObjectMapper().writeValueAsString(requestCreateNewWorldMap);
        Map<String, String> requestSomethingElse = new HashMap<>();
        requestSomethingElse.put("type", "foobar");
        requestSomethingElse.put("payload", "blablabla");
        requestSomethingElse.put("something", new ObjectMapper().writeValueAsString(new Vector3()));
        requestJsonForSomethingElse = new ObjectMapper().writeValueAsString(requestJsonForSomethingElse);
    }

    @Test
    void testIsForMe() throws JsonProcessingException{
        boolean isTrue = createNewWorldService.isForMe(requestJsonForCreateNewWorld);
        assertTrue(isTrue);
        boolean isFalse = createNewWorldService.isForMe(requestJsonForSomethingElse);
        assertFalse(isFalse);
    }
    @Test
    void canHandleMessage() throws JsonProcessingException, IOException{
        createNewWorldService.handleMessage(session, requestJsonForCreateNewWorld);
        assertNotNull(session.messageThatWouldBeSentToClient);
        JsonNode requestNode = new ObjectMapper().readTree(session.messageThatWouldBeSentToClient);
        String type = requestNode.get("type").textValue();
        RemoteGameObject obj = new ObjectMapper().readValue(requestNode.get("rootObject").toString(), RemoteGameObject.class);
        
        assertTrue(type.equals(CreateNewWorldService.messageType));
        assertTrue(obj.getId().equals("ROOT"));

    }
}