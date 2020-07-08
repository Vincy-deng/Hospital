package com.neu.dao;

import com.neu.vo.DoctorVo;
import com.neu.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by Administrator on 2019/10/21.
 * 用户DAO操作接口
 */
@Repository
public interface UserDao {
    /**
     * 通过用户名获取用户信息
     */
    UserVo Login(@Param("username")String username,@Param("password")String password);
    //注销登录 返会任意值
    List<UserVo> Logout();

}
