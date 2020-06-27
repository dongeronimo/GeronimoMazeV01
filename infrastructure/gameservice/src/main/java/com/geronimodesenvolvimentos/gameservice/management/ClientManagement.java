package com.geronimodesenvolvimentos.gameservice.management;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ClientManagement {
    private static Map<String, WebSocketSession> clients;
    private void createTableIfNeeded(){
        if(clients == null){
            clients = new HashMap<>();
        }
    }
    public void insertNewClient(WebSocketSession session){
        createTableIfNeeded();
        clients.put(session.getId(), session);
    }
    public void removeClient(String clientId){
        clients.remove(clientId);
    }
    public WebSocketSession getClient(String key){
        createTableIfNeeded();
        return clients.get(key);
    }
    public int getCount(){
        createTableIfNeeded();
        return clients.size();
    }
}