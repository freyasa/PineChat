package com.freya.controller;

import com.freya.dao.UserDao;
import com.freya.pojo.Request;
import com.freya.pojo.Response;
import com.freya.pojo.User;
import com.freya.service.UserService;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author xzq
 * @Description 用户信息部分
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signIn(@RequestBody User user) {
        System.out.println(user);
        return new Gson().toJson(userService.signIn(user));
    }

    /**
     * @Description 注册
     * @param user 注册的用户信息
     * @return 成功 200，失败 404
     */
    @CrossOrigin
    @RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody User user) {
        return new Gson().toJson(userService.register(user));
    }

    /**
     * @Description 自动登录检测
     * @param user 登录的用户信息
     * @return 成功 200，失败 404
     */
    @CrossOrigin
    @RequestMapping(value = "/isSignIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String isSignIn(@RequestBody User user) {
        return new Gson().toJson(userService.isSignIn(user));
    }

    /**
     * @Description 退出登录
     * @param user 退出的用户信息
     * @return 成功 200，失败 404
     */
    @CrossOrigin
    @RequestMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String logout(@RequestBody User user) {
        return new Gson().toJson(userService.logout(user));
    }

    /**
     * @Description 获取用户信息
     * @param request 获取的用户信息，包含token
     * @return 用户信息
     */
    @CrossOrigin
    @RequestMapping(value = "/getSingleUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSingleUser(@RequestBody Request request) {
        return new Gson().toJson(userService.getSingleUser(request.getUser()));
    }

    /**
     * @Description 修改用户信息
     * @param user 修改的用户信息
     * @return 成功 200，失败 404
     */
    @CrossOrigin
    @RequestMapping(value = "/modifySingleUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String modifySingleUser(@RequestBody User user) {
        return new Gson().toJson(userService.modifyUser(user));
    }

    /**
     * @Description 用户上传头像
     * @param file 用户上传的头像
     * @param request ServletHandler request
     * @return 成功 头像的url，失败 404
     */
    @CrossOrigin
    @PostMapping("/userUpload")
    public String oneFileUpload(@ModelAttribute MultipartFile file, HttpServletRequest request) {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setHeaderEncoding("UTF-8");

        String finalPath = request.getServletContext().getRealPath("/");
        String fileFolderPath = finalPath.concat("icon/" + file.hashCode() + ".jpg");

        File folder = new File(fileFolderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }

        try {
            file.transferTo(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://" + "freya.icu" + "/pinechat/icon/" + file.hashCode() + ".jpg";
    }
}
