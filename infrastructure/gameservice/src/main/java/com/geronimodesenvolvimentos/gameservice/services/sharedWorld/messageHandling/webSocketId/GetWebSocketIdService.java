package com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.webSocketId;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geronimodesenvolvimentos.gameservice.management.ClientManagement;
import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.messageHandling.MissingTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class GetWebSocketIdService {
    @Autowired
    private ClientManagement clientManager;

    public static final String messageType = "getId";
	public boolean isForMe(String payload) throws JsonProcessingException {
        try{
            GetWebsocketIdRequestObject req = rawStringToRequestObject(payload);
            if(req!=null && req.getType().equals(messageType)){
                return true;
            }else{
                return false;
            }
        }catch(JsonMappingException e){
            throw new MissingTypeException();
        }catch(JsonParseException e){
            throw new MalformedGetIdRequestException();
        }
	}

	public void handleMessage(WebSocketSession session, String payload) throws IOException {
        try{
            rawStringToRequestObject(payload);
            String id = session.getId();
            String clientId = clientManager.getClient(id).getId();
            GetWebsocketIdResponseObject response = createResponse(clientId);
            session.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(response)));
        }
        catch(JsonMappingException e){
            throw new MissingTypeException();
        }
        catch(JsonParseException e){
            throw new MalformedGetIdRequestException();
        }
    }
 
    private GetWebsocketIdRequestObject rawStringToRequestObject(String payload) throws JsonMappingException, JsonProcessingException{
        GetWebsocketIdRequestObject req = new ObjectMapper().readValue(payload, GetWebsocketIdRequestObject.class);
        return req;
    }

    private GetWebsocketIdResponseObject createResponse(String clientId){
        GetWebsocketIdResponseObject response = new GetWebsocketIdResponseObject();
        response.setType(messageType);
        response.setTime( Instant.now().toString());
        response.setWebsocketId(clientId);
        return response;
    }
    
}