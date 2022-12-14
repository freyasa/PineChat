package com.freya.service;

import com.freya.dao.UserDao;
import com.freya.pojo.LoginLog;
import com.freya.pojo.Request;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class UserService extends WebsocketService {
    private UserDao userDao;
    private LoginLogService loginLogService;

    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Response signIn(User user) {
        User dbUser = userDao.getSingleUser(user);
        if (Objects.equals(dbUser.getUsername(), user.getUsername()) && Objects.equals(user.getPassword(), dbUser.getPassword())) {
            String key = dbUser.getUsername();
            loginLogService.addLoginLog(new LoginLog(new Date(), user));
            if (redisTemplate.hasKey(key + "$usr")) {
                System.out.println(redisTemplate.opsForValue().get(key + "$usr"));
            } else {
                redisTemplate.opsForValue().set(key + "$usr", dbUser.getPassword(), 30, TimeUnit.DAYS);
                redisTemplate.opsForValue().set(dbUser.getPassword() + "$pwd", key, 30, TimeUnit.DAYS);
            }
            return new Response(200, true, "登录成功", dbUser);
        }
        return new Response(500, false, "登录失败", null);
    }

    public Response register(User user) {
        userDao.addUser(user);
        return new Response(200, true, "注册成功", null);
    }

    public Response isSignIn(User user) {
        String key = user.getUsername();
        if (redisTemplate.hasKey(key + "$usr")) {
            loginLogService.addLoginLog(new LoginLog(new Date(), user));
            return new Response(200, true, "已经登录", userDao.getSingleUser(user));
        } else {
            return new Response(404, false, "未登录", null);
        }
    }

    public Response modifyUser(User user) {
        String key = user.getUsername();
        if (redisTemplate.hasKey(key + "$usr")) {
            userDao.modifyUser(user);
            return new Response(200, true, "修改成功", null);
        } else {
            return new Response(404, false, "未登录", null);
        }
    }

    public Response logout(User user) {
        String key = user.getUsername();
        if (redisTemplate.hasKey(key + "$usr")) {
            redisTemplate.delete(key + "$usr");
            redisTemplate.delete(userDao.getSingleUser(user).getPassword() + "$pwd");
        }
        return new Response(200, true, "退出成功", userDao.getSingleUser(user));
    }

    public void addSocketRedis(User user, UUID token) {
        System.out.println("addSocketRedis");
        String key = user.getUsername();
//        if(!redisTemplate.hasKey(key + "$socket")) {
        System.out.println("finish!");
        redisTemplate.opsForValue().set(user.getUsername() + "$socket", token.toString());
//        }
        System.out.println(user.getUsername() + "$socket");
        System.out.println(redisTemplate.opsForValue().get(user.getUsername() + "$socket"));
    }

    public Response getSingleUser(User user) {
        return new Response(200, true, "查询成功", userDao.getSingleUser(user));
    }
}
