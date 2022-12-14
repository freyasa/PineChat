package com.freya.controller;

import com.freya.pojo.Request;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.freya.service.ChatService;
import com.freya.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzq
 * @Description 文本聊天部分的http请求接收器
 */

@RestController
@RequestMapping(value = "/api")
public class ChatController {
    RedisTemplate<String, String> redisTemplate;
    ChatService chatService;
    UserService userService;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @Description 获得用户信息
     * @param request 请求体（包含token和用户名）
     * @return 用户信息
     */
    @CrossOrigin
    @RequestMapping(value = "/getUserMsg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserMsg(@RequestBody Request request) {
        System.out.println(request);
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        return new Gson().toJson(chatService.getUserMsg(request.getUser(), new User(redisTemplate.opsForValue().get(request.getToken() + "$pwd"))));
    }

    /**
     * @Description 获得最近聊天的用户
     * @param request 请求体
     * @return 最近聊天的用户列表
     */
    @CrossOrigin
    @RequestMapping(value = "/getRecentUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRecentUser(@RequestBody Request request) {

        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        return new Gson().toJson(chatService.getRecentUser(request.getUser()));
    }

    /**
     * @Description http端的发送信息
     * @param request 请求体
     */
    @CrossOrigin
    @RequestMapping(value = "/sendUserMsg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendUserMsg(@RequestBody Request request) {
        chatService.sendMessage(request);
    }

}
