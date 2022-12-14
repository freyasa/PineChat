package com.freya.pojo;

public class Request {
    private String token;
    private User user;
    private Friend friend;
    private ChatLog chatLog;

    public Request() {
    }

    public Request(String token, User user) {
        this.token = token;
        this.user = user;
    }
    public Request(String token, Friend friend) {
        this.token = token;
        this.friend = friend;
    }
    public Request(String token, ChatLog chatLog) {
        this.token = token;
        this.chatLog = chatLog;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public ChatLog getChatLog() {
        return chatLog;
    }

    public void setChatLog(ChatLog chatLog) {
        this.chatLog = chatLog;
    }

    @Override
    public String toString() {
        return "Request{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", friend=" + friend +
                ", chatLog=" + chatLog +
                '}';
    }
}
