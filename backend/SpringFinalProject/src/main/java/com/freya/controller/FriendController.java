package com.freya.controller;

import com.freya.dao.FriendDao;
import com.freya.dao.UserDao;
import com.freya.pojo.Request;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.freya.service.FriendService;
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
 * @Description 好友部分
 */

@RestController
@RequestMapping(value = "/api")
public class FriendController {

    private FriendService friendService;
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserService(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * @Description 加好友
     * @param request 请求体
     * @return 200成功，404失败
     */
    @CrossOrigin
    @RequestMapping(value = "/addFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addFriend(@RequestBody Request request) {
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        System.out.println(request.getUser().getUsername());
        return new Gson().toJson(friendService.addFriend(request.getUser(), request.getToken()));
    }

    /**
     * @Description 搜索好友
     * @param request 请求体，包含token和搜索好友用户名
     * @return 搜索好友
     */
    @CrossOrigin
    @RequestMapping(value = "/searchFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchFriend(@RequestBody Request request) {
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        return new Gson().toJson(friendService.searchFriend(request.getUser(), request.getToken()));
    }

    /**
     * @Description 接受好友申请
     * @param request 请求体，包含token
     * @return 200成功，404失败
     */
    @CrossOrigin
    @RequestMapping(value = "/agreeFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String agreeFriend(@RequestBody Request request) {
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        return new Gson().toJson(friendService.agreeFriend(request.getUser(), request.getToken()));
    }

    /**
     * @Description 获取好友
     * @param request 请求体，包含token
     * @return 返回好友列表
     */
    @CrossOrigin
    @RequestMapping(value = "/getFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFriend(@RequestBody Request request) {
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        return new Gson().toJson(friendService.getFriends(request.getUser()));
    }

    /**
     * @Description 删除好友
     * @param request 请求体
     * @return 200成功，404失败
     */
    @CrossOrigin
    @RequestMapping(value = "/deleteFriend", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteFriend(@RequestBody Request request) {
        if (!redisTemplate.hasKey(request.getToken() + "$pwd")) {
            return new Gson().toJson(new Response(404, false, "用户未登录", null));
        }
        User user = new User(redisTemplate.opsForValue().get(request.getToken() + "$pwd"));
        User user1 = request.getUser();
        return new Gson().toJson(friendService.deleteFriend(user, user1));
    }
}
