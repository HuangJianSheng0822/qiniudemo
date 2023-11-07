package com.qn.qiniudemoapi.config;

import com.qn.qiniudemoapi.handler.BarrageWebSocketHandler;
import com.qn.qiniudemoapi.handler.LiveWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * webSocket配置
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final BarrageWebSocketHandler barrageWebSocketHandler;

    private final LiveWebSocketHandler liveWebSocketHandler;

    @Autowired
    public WebSocketConfig(BarrageWebSocketHandler barrageWebSocketHandler,LiveWebSocketHandler liveWebSocketHandler) {
        this.barrageWebSocketHandler = barrageWebSocketHandler;
        this.liveWebSocketHandler=liveWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(barrageWebSocketHandler, "/barrage")
                .addHandler(liveWebSocketHandler,"/live")
                //允许跨域
                .setAllowedOrigins("*");
    }
}
