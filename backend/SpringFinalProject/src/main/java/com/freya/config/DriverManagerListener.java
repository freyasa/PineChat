package com.freya.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author xzq
 * @Description 关闭servletContext时注销jdbc驱动
 */

@Slf4j
@WebListener("关闭servletContext时注销jdbc驱动")
public class DriverManagerListener implements ServletContextListener {
    @Override
    public void contextInitialized (ServletContextEvent sce ){
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //这里如果Web应用拥有多个数据库的连接，可以一并关闭
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver;
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ignored) {
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
