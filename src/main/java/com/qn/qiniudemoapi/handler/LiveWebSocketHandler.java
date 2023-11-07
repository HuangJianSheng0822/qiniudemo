package com.qn.qiniudemoapi.handler;

import com.qn.qiniudemoapi.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LiveWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private LiveService liveService;
    private Map<String, Set<WebSocketSession>> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String liveId = session.getUri().toString().split("=")[1];
        //判断是否已经存在
        if (sessionMap.get(liveId)==null) {
            Set<WebSocketSession> sets = new HashSet<>();
            sets.add(session);
            sessionMap.put(liveId,sets);
            //加入redis
            liveService.hasNewPeople(liveId,session.getRemoteAddress().toString());
        }else {
            sessionMap.get(liveId).add(session);
            liveService.hasNewPeople(liveId,session.getRemoteAddress().toString());
        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String liveId = session.getUri().toString().split("=")[1];
        sessionMap.get(liveId).remove(session);
        liveService.exitLive(liveId, session.getRemoteAddress().toString());
    }
}
