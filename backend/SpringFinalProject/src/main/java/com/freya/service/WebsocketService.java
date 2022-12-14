package com.freya.service;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.freya.pojo.ChatLog;
import com.freya.pojo.Request;
import com.freya.pojo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class WebsocketService {
    public static Configuration config = new Configuration();
    public static SocketIOServer server;

    private UserService userService;
    private ChatService chatService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    static {
        config.setHostname("0.0.0.0");
        config.setPort(8979);
        server = new SocketIOServer(config);

        server.addConnectListener(client -> {
            System.out.println("server: " + client.getRemoteAddress() + "客户端连接成功");
        });

        server.addEventListener("sub", String.class, (client, data, ackRequest) -> {
            String c = client.getRemoteAddress().toString();

            Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
            System.out.println("server: " + c + "：客户端：订阅成功，订阅信息為->" + data);

            client.sendEvent("sub", c + "客户端你好，我是服务端，你订阅成功了");
        });

        server.addEventListener("friend", String.class, (client, data, ackRequest) -> {
            System.out.println(client);
            System.out.println(data);
            System.out.println(ackRequest);
        });

        server.addDisconnectListener(client -> {
            String c = client.getRemoteAddress().toString();
            String clientIp = c.substring(1, c.indexOf(":"));
            System.out.println("server: " + clientIp + "-------------------------" + "客户端已断开连接");
        });

        server.start();
    }

    @PostConstruct
    public void start() {
        server.addEventListener("verify", String.class, (client, data, ackRequest) -> {
            System.out.println("verify");
            User user = new Gson().fromJson(data, User.class);
            System.out.println(user);
            userService.addSocketRedis(user, client.getSessionId());
            System.out.println("add");
            System.out.println(ackRequest);
        });

        server.addEventListener("msg", String.class, (client, data, ackRequest) -> {
            Request request = new Gson().fromJson(data, Request.class);
            System.out.println("request: " + request);
            chatService.sendMessage(request);
        });

        server.addEventListener("msgR", String.class, (client, data, ackRequest) -> {
            ChatLog chatLog = new Gson().fromJson(data, ChatLog.class);
            System.out.println("chatLog" + chatLog);
        });
    }
}
