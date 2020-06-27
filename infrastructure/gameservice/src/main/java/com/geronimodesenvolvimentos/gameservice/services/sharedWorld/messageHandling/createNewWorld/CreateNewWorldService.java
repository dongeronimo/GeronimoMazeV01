package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.createNewWorld;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geronimodesenvolvimentos.gameservice.management.RemoteGameObjectManagement;
import com.geronimodesenvolvimentos.gameservice.model.RemoteGameObject;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.MissingTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class CreateNewWorldService {
    public static final String messageType = "createNewWorld";
    @Autowired
    private RemoteGameObjectManagement remoteGameObjectManagement;
    public boolean isForMe(String payload) throws JsonProcessingException{
        try{
            CreateNewWorldRequestObject req = rawStringToRequestObject(payload);
            if(req!=null && req.getType().equals(messageType)){
                return true;
            }else{
                return false;
            }
        }
        catch(JsonMappingException e){
            throw new MissingTypeException();
        }
        catch(JsonParseException e){
            throw new MalformedCreateWorldException();
        }
    }
    private CreateNewWorldRequestObject rawStringToRequestObject(String payload) throws JsonMappingException, JsonProcessingException{
        CreateNewWorldRequestObject req = new ObjectMapper().readValue(payload, CreateNewWorldRequestObject.class);
        return req;
    }
    public void handleMessage(WebSocketSession session, String payload) throws JsonProcessingException, IOException{
        RemoteGameObject rootObject = remoteGameObjectManagement.createNewWorld();
        CreateNewWorldResponseObject response = new CreateNewWorldResponseObject(rootObject);
        session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(response)));
    }
}