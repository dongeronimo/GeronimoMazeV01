package com.geronimodesenvolvimentos.gameservice.services.sharedWorld;

import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.objectUpdate.ObjectUpdateService;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId.GetWebSocketIdService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SharedWorldSocketHandler extends TextWebSocketHandler{
    private Logger logger = LoggerFactory.getLogger(SharedWorldSocketHandler.class);
    @Autowired
    private ClientManagement clientManagement;
    @Autowired
    private GetWebSocketIdService getWebsocketIdService;
    @Autowired
    private ObjectUpdateService objectUpdateService;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        clientManagement.insertNewClient(session);
        logger.info("Client connected:"+session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clientManagement.removeClient(session.getId());
        logger.info("Client disconected:"+session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (getWebsocketIdService.isForMe(message.getPayload())){
            getWebsocketIdService.handleMessage(session, message.getPayload());
        }
        if (objectUpdateService.isForMe(message.getPayload())){
            objectUpdateService.handleMessage(session, message.getPayload());
        }
    }
}