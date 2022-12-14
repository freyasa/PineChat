package com.freya.service;

import com.freya.dao.ChatLogDao;
import com.freya.pojo.Admin;
import com.freya.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChatLogService {
    private ChatLogDao chatLogDao;

    @Autowired
    public void setChatLogDao(ChatLogDao chatLogDao) {
        this.chatLogDao = chatLogDao;
    }

    public Response getChatLogs() {
        try {
            return new Response(200, true, "成功", chatLogDao.getChatLogs());
        } catch (Exception ignored) {

        }
        return new Response(404, false, "失败", null);
    }
}
