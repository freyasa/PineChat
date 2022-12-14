package com.freya.service;

import com.freya.dao.FriendDao;
import com.freya.dao.UserDao;
import com.freya.pojo.Friend;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class FriendService extends WebsocketService {
    private FriendDao friendDao;
    private UserDao userDao;

    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setFriendDao(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Response addFriend(User objUser, String token) {
        User fromUser = userDao.getSingleUser(new User(redisTemplate.opsForValue().get(token + "$pwd")));
        System.out.println(redisTemplate.hasKey(objUser.getUsername() + "$socket"));
        if (redisTemplate.hasKey(objUser.getUsername() + "$socket")) {
            System.out.println(redisTemplate.opsForValue().get(objUser.getUsername() + "$socket"));
            server.getClient(UUID.fromString(redisTemplate.opsForValue().get(objUser.getUsername() + "$socket"))).sendEvent("friend", new Gson().toJson(fromUser));
            return new Response(200, true, "成功", friendDao.getConditionalUser(objUser));
        }
        return new Response(404, false, "用户未登录", null);
    }

    public Response getFriends(User user) {
        return new Response(200, true, "成功", friendDao.getSingleUserFriend(user));
    }

    public Response deleteFriend(User user, User user1) {
        Friend friend1 = new Friend();
        friend1.setPart1(userDao.getSingleUser(user));
        friend1.setPart2(userDao.getSingleUser(user1));
        var fe1 = friendDao.getSingleFriend(friend1);

        Friend friend2 = new Friend();
        friend2.setPart2(userDao.getSingleUser(user));
        friend2.setPart1(userDao.getSingleUser(user1));
        var fe2 = friendDao.getSingleFriend(friend2);

        if (fe1.size() != 0) {
            System.out.println("fe1.size() is not 0");
            friendDao.deleteFriend(fe1.get(0));
        }
        else if (fe2.size() != 0) {
            System.out.println("fe2.size() is not 0");
            friendDao.deleteFriend(fe2.get(0));

        }

        return new Response(200, true, "成功", null);
    }

    public Response searchFriend(User user, String token) {
        if (redisTemplate.hasKey(token + "$pwd")) {
            return new Response(200, true, "成功", friendDao.getConditionalUser(user));
        }
        return new Response(404, false, "用户未登录", null);
    }

    public Response agreeFriend(User user, String token) {
        Friend friend = new Friend();
        User user1 = new User();
        user1.setUsername(redisTemplate.opsForValue().get(token + "$pwd"));

        user = userDao.getSingleUser(user);
        user1 = userDao.getSingleUser(user1);

        friend.setPart1(userDao.getSingleUser(user));
        friend.setPart2(userDao.getSingleUser(user1));

        Friend friend1 = new Friend();
        friend1.setPart1(userDao.getSingleUser(user1));
        friend1.setPart2(userDao.getSingleUser(user));

//        System.out.println(redisTemplate.hasKey(token + "$pwd"));
//        System.out.println(friendDao.getSingleFriend(friend).size());
//        System.out.println(friendDao.getSingleFriend(friend1).size());

        System.out.println(friend);
        System.out.println(user);
        System.out.println(user1);

        if (redisTemplate.hasKey(token + "$pwd") && friendDao.getSingleFriend(friend).size() == 0 && friendDao.getSingleFriend(friend1).size() == 0) {
            friendDao.addFriend(friend);
            System.out.println(200);
            return new Response(200, true, "成功", null);
        }
        System.out.println(400);
        return new Response(404, false, "用户未登录", null);
    }
}
