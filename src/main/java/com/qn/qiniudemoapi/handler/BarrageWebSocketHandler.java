package com.qn.qiniudemoapi.handler;

import com.qn.qiniudemoapi.dto.BarrageDto;
import com.qn.qiniudemoapi.pojo.Barrage;
import com.qn.qiniudemoapi.service.BarrageService;
import com.qn.qiniudemoapi.util.JwtUtil;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 弹幕评论
 */
@Component
public class BarrageWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private BarrageService barrageService;

    // 在类中定义一个Map来保存WebSocket连接
    private Map<String, Set<WebSocketSession>> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String videoId = session.getUri().toString().split("=")[1];
        //判断是否已经存在
        if (sessionMap.get(videoId)==null) {
            Set<WebSocketSession> sets = new HashSet<>();
            sets.add(session);
            sessionMap.put(videoId,sets);
        }else {
            sessionMap.get(videoId).add(session);
        }

    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        BarrageDto barrageDto = msgToDto(message);
        //广播
        String videoId = barrageDto.getVideoId();
        com.alibaba.fastjson.JSONObject obj = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(barrageDto);
        sendMessageToAll(videoId, obj.toString());
        addBarrage(barrageDto);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.values().forEach(e->{
            e.remove(session);
        });
    }


    public BarrageDto msgToDto(TextMessage message){
        String payload = message.getPayload();
        BarrageDto barrageDto=new BarrageDto();
        try {
            JSONObject jsonObject = new JSONObject(payload);
            String jwt = jsonObject.getString("userId");
            String decrypt = JwtUtil.decrypt(jwt);
            barrageDto.setUserId(decrypt);
            barrageDto.setVideoId(jsonObject.getString("videoId"));
            barrageDto.setContent(jsonObject.getString("content"));
            barrageDto.setSchedule(jsonObject.getString("schedule"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return barrageDto;
    }

    public void sendMessage(WebSocketSession session,String msg){
        TextMessage textMessage=new TextMessage(msg);
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAll(String videoId,String msg){
        sessionMap.get(videoId).forEach(session->{
            sendMessage(session,msg);
        });
    }

    @Async
    public ResponseDataStructure addBarrage(BarrageDto barrageDto){
        Barrage barrage = new Barrage();
        barrage.setVideoId(barrageDto.getVideoId());
        barrage.setUserId(barrageDto.getUserId());
        barrage.setContent(barrageDto.getContent());
        barrage.setSchedule(barrageDto.getSchedule());
        boolean save = barrageService.save(barrage);
        return new ResponseDataStructure(200,"",save);
    }

}
