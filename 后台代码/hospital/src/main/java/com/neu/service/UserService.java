package com.neu.service;

import com.neu.vo.RecordVo;
import com.neu.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import sun.awt.geom.AreaOp;

import java.util.List;

/**
 * Created by Administrator on 2019/10/21.
 */
public interface UserService {
    UserVo login(String username,String password);
    int Logout();
  }
