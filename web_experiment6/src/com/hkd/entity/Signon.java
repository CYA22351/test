package com.hkd.entity;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/4/15 10:32
 * @description：
 * @modified By：
 * @version:
 */
public class Signon {
    private String  username;
    private String password;

    public Signon() {
    }

    public Signon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String showInfo()
    {
        return username+" "+password;
    }

}