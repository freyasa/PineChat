package com.freya.service;

import com.freya.dao.AdminDao;
import com.freya.dao.UserDao;
import com.freya.pojo.Admin;
import com.freya.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AdminService {
    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Response login(Admin admin) {
        try{
            Admin admin1 = adminDao.getAdmin(admin);
            if (Objects.equals(admin1.getPassword(), admin.getPassword()))
                return new Response(200, true, "成功", null);
        } catch (Exception ignored) {

        }
        return new Response(404, false, "失败", null);
    }
}
