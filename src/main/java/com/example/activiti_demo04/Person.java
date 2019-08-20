package com.example.activiti_demo04;

import java.io.Serializable;

/**
 * @author caikangsheng
 * @date 2019/7/9 15:08
 */
public class Person implements Serializable {
    /**
     * 版本固定
     */
    private static final long serialVersionUID = -3943950228585942450L;
    private String id;
    private String username;
    private String password;
    private String asdafafaf;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAsdafafaf() {
        return asdafafaf;
    }

    public void setAsdafafaf(String asdafafaf) {
        this.asdafafaf = asdafafaf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
