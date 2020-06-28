package com.geronimodesenvolvimentos.gameservice.websocket;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.SharedWorldSocketHandler;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld.CreateNewWorldRequestObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld.CreateNewWorldResponseObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld.CreateNewWorldService;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.GetWebsocketIdRequestObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.GetWebsocketIdResponseObject;
import com.geronimodesenvolvimentos.gameservice.stub.WebSocketSessionStub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SharedWorldSocketHandlerTests {
    @Autowired
    RemoteGameObjectManagement remoteGameObjectManagement;
    @Autowired
    ClientManagement clientManagement;
    @Autowired
    SharedWorldSocketHandler socketHandler;
    @Test
    void clientExistsAfterConnection() throws Exception{
        socketHandler.afterConnectionEstablished(new WebSocketSessionStub());
        WebSocketSession session = clientManagement.getClient("SESSION1");
        assertNotNull(session);
    }
    @Test
    void clientDoesNotExistAfterDisconnection() throws Exception{
        socketHandler.afterConnectionEstablished(new WebSocketSessionStub());
        WebSocketSession session = clientManagement.getClient("SESSION1");
        assertNotNull(session);
        socketHandler.afterConnectionClosed(new WebSocketSessionStub(), CloseStatus.GOING_AWAY);
        WebSocketSession sessionAfterDisconnect = clientManagement.getClient("SESSION1");
        assertNull(sessionAfterDisconnect);
    }
    @Test
    void canHandleGetIdMessage() throws Exception{
        socketHandler.afterConnectionEstablished(new WebSocketSessionStub());
        GetWebsocketIdRequestObject request = new GetWebsocketIdRequestObject();
        WebSocketSessionStub stub = new WebSocketSessionStub();
        socketHandler.handleMessage(stub, new TextMessage(new ObjectMapper().writeValueAsString(request)));
        GetWebsocketIdResponseObject response = new ObjectMapper().readValue(stub.messageThatWouldBeSentToClient, GetWebsocketIdResponseObject.class);
        assertNotNull(response);
        assertTrue( response.getType().equals("getId") );
        assertTrue(response.getWebsocketId().equals(stub.getId()));
    }
    @Test
    void canHandleCreateWorldMessage() throws Exception{
        socketHandler.afterConnectionEstablished(new WebSocketSessionStub());
        CreateNewWorldRequestObject request = new CreateNewWorldRequestObject();
        WebSocketSessionStub stub = new WebSocketSessionStub();
        socketHandler.handleMessage(stub, new TextMessage(new ObjectMapper().writeValueAsString(request)));
        JsonNode responseNode = new ObjectMapper().readTree(stub.messageThatWouldBeSentToClient);
        String type = responseNode.get("type").textValue();
        RemoteGameObject root = new ObjectMapper().readValue(responseNode.get("rootObject").toString(),RemoteGameObject.class);
        assertTrue(type.equals(CreateNewWorldService.messageType));
        assertTrue(root.getId().equals("ROOT"));
        assertNotNull(remoteGameObjectManagement.find("ROOT"));
    }
    
}