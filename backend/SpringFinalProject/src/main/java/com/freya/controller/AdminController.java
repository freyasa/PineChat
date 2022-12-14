package com.freya.controller;

import com.freya.pojo.Admin;
import com.freya.pojo.Request;
import com.freya.service.AdminService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzq
 * @Description 管理员端代码，预留接口
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @CrossOrigin
    @RequestMapping(value = "/adminLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String adminLogin(@RequestBody Admin admin) {
        return new Gson().toJson(adminService.login(admin));
    }
}
