package com.freya.service;

import com.freya.dao.ChatLogDao;
import com.freya.pojo.ChatLog;
import com.freya.pojo.Request;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ChatService extends WebsocketService {
    RedisTemplate<String, String> redisTemplate;
    ChatLogDao chatLogDao;

    @Autowired
    public void setChatLogDao(ChatLogDao chatLogDao) {
        this.chatLogDao = chatLogDao;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Response getUserMsg(User user, User user1) {
        return new Response(200, true, "成功", chatLogDao.getSingleUserChatLog(user, user1));
    }

    public void sendMessage(Request request) {
//        System.out.println("sendMessage");
        ChatLog chatLog = request.getChatLog();
        chatLog.setSendTime(new Date());
        System.out.println("测试");
        System.out.println(redisTemplate.hasKey(request.getToken() + "$pwd"));
        if (redisTemplate.hasKey(request.getToken() + "$pwd") && redisTemplate.hasKey(chatLog.getReceiver().getUsername() + "$socket")) {
            chatLogDao.addChatLog(chatLog);
            server.getClient(UUID.fromString(redisTemplate.opsForValue().get(chatLog.getReceiver().getUsername() + "$socket"))).sendEvent("msgR", new Gson().toJson(chatLog));
        }
    }

    public Response getRecentUser(User user) {
        List<ChatLog> chatLogs = chatLogDao.getSingleUserRecent(user);
        List<User> userList = new ArrayList<>();
        chatLogs.forEach((e) -> {
            if (!userList.contains(e.getSender()))  userList.add(e.getSender());
            if (!userList.contains(e.getReceiver()))  userList.add(e.getReceiver());
        });
        System.out.println(userList);
        return new Response(200, true, "成功", userList);
    }
}
