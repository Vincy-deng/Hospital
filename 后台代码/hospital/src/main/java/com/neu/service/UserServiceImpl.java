package com.neu.service;

import com.neu.dao.UserDao;
import com.neu.vo.RecordVo;
import com.neu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public UserVo login(String username, String password) {
       UserVo userVo = userDao.Login(username,password);
       if (userVo != null){
           return userVo;
       }
        return null;
    }

    @Override
    public int Logout() {
        List<UserVo> userVoList = userDao.Logout();
        if (userVoList.size() != 0){
            return 1;
        }
        return 0;
    }


}
