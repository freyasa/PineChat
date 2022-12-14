package com.freya.service;

import com.freya.dao.AdminDao;
import com.freya.dao.LoginLogDao;
import com.freya.pojo.Admin;
import com.freya.pojo.LoginLog;
import com.freya.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginLogService {
    private LoginLogDao loginLogDao;

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public Response addLoginLog(LoginLog loginLog) {
        loginLogDao.addLoginLog(loginLog);
        return new Response(200, true, "成功", null);
    }

    public Response getLoginLogs() {
        try {
            return new Response(200, true, "成功", loginLogDao.getLoginLogs());
        } catch (Exception ignored) {

        }
        return new Response(404, false, "失败", null);
    }
}
