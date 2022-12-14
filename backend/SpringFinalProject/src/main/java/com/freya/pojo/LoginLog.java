package com.freya.pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy = false)
@Table(name = "loginLog")
public class LoginLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date loginTime;
    @OneToOne(targetEntity = User.class)
    private User user;

    public LoginLog() {
    }

    public LoginLog(Date loginTime, User user) {
        this.loginTime = loginTime;
        this.user = user;
    }

    public LoginLog(Integer id, Date loginTime, User user) {
        this.id = id;
        this.loginTime = loginTime;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
