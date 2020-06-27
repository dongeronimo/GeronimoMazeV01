package com.geronimodesenvolvimentos.gameservice.websocket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.MissingTypeException;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.GetWebSocketIdService;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.GetWebsocketIdResponseObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.MalformedGetIdRequestException;
import com.geronimodesenvolvimentos.gameservice.stub.WebSocketSessionStub;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetWebsocketIdRequestServiceTests{
    @Autowired
    private ClientManagement clientManager;
    @Autowired GetWebSocketIdService getWebsocketIdService;

    private String requestJsonForGetId;
    private String requestJsonForSomethingElse;
    private WebSocketSessionStub session;
    @BeforeEach
    void setUp() throws JsonProcessingException{
        session = new WebSocketSessionStub("foobar");
        clientManager.insertNewClient(session);
        Map<String, String> map = new HashMap<>();
        map.put("type", GetWebSocketIdService.messageType);
        requestJsonForGetId = new ObjectMapper().writeValueAsString(map);
        Map<String, String> map2 = new HashMap<>();
        map2.put("type", "fooobar");
        requestJsonForSomethingElse = new ObjectMapper().writeValueAsString(map2);
    }
    @Test
    void testIsForMe() throws JsonProcessingException{
        boolean isForMe = getWebsocketIdService.isForMe(requestJsonForGetId);
        assertTrue(isForMe);
    }
    @Test
    void refusesMessagesThatAreForOthers() throws JsonProcessingException{
        boolean isForMe = getWebsocketIdService.isForMe(requestJsonForSomethingElse);
        assertFalse(isForMe);
    }
    @Test
    void isForMeDealsWithMalformedJson(){
        assertThrows(MalformedGetIdRequestException.class, ()->{
            boolean isForMe = getWebsocketIdService.isForMe("asjdiawjieahiehiawheiawhhie");
            assertFalse(isForMe);
        });
    }
    @Test
    void isForMeDealsWithJsonLackingTypeField(){
        assertThrows(MissingTypeException.class, ()->{
            Map<String, String> m = new HashMap<>();
            m.put("foo", "bar");
            boolean isForMe = getWebsocketIdService.isForMe(new ObjectMapper().writeValueAsString(m));
            assertFalse(isForMe);
        });
    }
    @Test
    void canHandleMessages() throws IOException{
        getWebsocketIdService.handleMessage(session, requestJsonForGetId);
        assertNotNull(session.messageThatWouldBeSentToClient);
        GetWebsocketIdResponseObject resp = new ObjectMapper().readValue(session.messageThatWouldBeSentToClient, GetWebsocketIdResponseObject.class);
        assertNotNull(resp);
        assertTrue(resp.getType().equals(GetWebSocketIdService.messageType));
        assertTrue(resp.getWebsocketId().equals("foobar"));
    }
    @Test
    void canHandleMessageDealsWithMalformedJson(){
        assertThrows(MalformedGetIdRequestException.class, ()->{
            getWebsocketIdService.handleMessage(session, "akdajkdjawkjkaw");
        });
    }
    @Test
    void canHandleMessageDealsWithJsonLackingType(){
        assertThrows(MissingTypeException.class, ()->{
            Map<String, String> m = new HashMap<>();
            m.put("foo", "bar");
            getWebsocketIdService.handleMessage(session, new ObjectMapper().writeValueAsString(m));
        });
    }
}