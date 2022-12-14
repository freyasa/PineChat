package com.freya.controller;

import com.freya.dao.ChatLogDao;
import com.freya.pojo.Admin;
import com.freya.service.ChatLogService;
import com.freya.service.LoginLogService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzq
 * @Description 日志文件获取（保留）
 */

@RestController
@RequestMapping(value = "/api")
public class LogController {
    private LoginLogService loginLogService;
    private ChatLogService chatLogService;

    @Autowired
    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @Autowired
    public void setChatLogService(ChatLogService chatLogService) {
        this.chatLogService = chatLogService;
    }

    /**
     * @Description 获取登录日志
     * @return 登录日志
     */
    @CrossOrigin
    @RequestMapping(value = "/getLoginLogs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLoginLogs() {
        return new Gson().toJson(loginLogService.getLoginLogs());
    }

    /**
     * @Description 获取聊天日志
     * @return 聊天日志
     */
    @CrossOrigin
    @RequestMapping(value = "/getChatLogs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getChatLogs() {
        return new Gson().toJson(chatLogService.getChatLogs());
    }
}
