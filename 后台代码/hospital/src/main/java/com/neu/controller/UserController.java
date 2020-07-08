package com.neu.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.neu.service.UserService;
import com.neu.vo.UserVo;
import com.neu.util.*;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2019/10/21.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private final Log log = LogFactory.getLog(getClass());

    @PostMapping("/login")
    @ApiOperation(value = "/登录",notes = "登录")
    public  Object login(@RequestParam String username,@RequestParam String password){
        try{
            UserVo userVo = userService.login(username,password);
            if (userVo != null){
                return BaseResponse.generateOKResponseEntity(userVo);
            }
            return BaseResponse.generateBadResponseEntity(500,"登录失败,用户名或密码不正确","");

        }catch (Exception e){
            log.debug("UserController-->>updateUserById",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }

      }
    @PostMapping("/logout")
    @ApiOperation(value = "/注销登录",notes = "注销登录")
    public Object logout(){
        try {
            int flag = userService.Logout();
            if (flag == 1){
                return BaseResponse.generateOKResponseEntity('0');
            }
            return BaseResponse.generateBadResponseEntity(500,"退出登录失败","");
        }catch (Exception e){
            log.debug("UserController-->>updateUserById",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
}
