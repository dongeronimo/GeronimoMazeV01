package com.geronimodesenvolvimentos.gameservice;

import com.geronimodesenvolvimentos.gameservice.services.sharedWorld.SharedWorldSocketHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
    @Autowired
    private SharedWorldSocketHandler sharedWorldHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sharedWorldHandler, "/sharedWorld").setAllowedOrigins("*");
    } 
}