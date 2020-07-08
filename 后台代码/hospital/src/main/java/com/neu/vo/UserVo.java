package com.neu.vo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * Created by Administrator on 2019/10/21.
 */

public class UserVo {
    private Integer uId;
    private String username;
    private String password;
    private Integer role;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
