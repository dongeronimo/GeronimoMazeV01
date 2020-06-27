package com.geronimodesenvolvimentos.gameservice.stub;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.http.HttpHeaders;

public class WebSocketSessionStub implements WebSocketSession{
    private String id;
    public String messageThatWouldBeSentToClient;
    public WebSocketSessionStub(){
        this.id="SESSION1";
    }
    public WebSocketSessionStub(String id){
        this.id=id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public URI getUri() {
        return null;
    }

    @Override
    public HttpHeaders getHandshakeHeaders() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Principal getPrincipal() {
        return null;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    @Override
    public String getAcceptedProtocol() {
        return null;
    }

    @Override
    public void setTextMessageSizeLimit(int messageSizeLimit) {
    }

    @Override
    public int getTextMessageSizeLimit() {
        return 0;
    }

    @Override
    public void setBinaryMessageSizeLimit(int messageSizeLimit) {
    }

    @Override
    public int getBinaryMessageSizeLimit() {
        return 0;
    }

    @Override
    public List<WebSocketExtension> getExtensions() {
        return null;
    }

    @Override
    public void sendMessage(WebSocketMessage<?> message) throws IOException {    
        this.messageThatWouldBeSentToClient = message.getPayload().toString();
    }

    @Override
    public boolean isOpen() {
        System.out.println("isOpen");
        return true;
    }

    @Override
    public void close() throws IOException {

    }
    @Override
    public void close(CloseStatus status) throws IOException {
        
    }

 }