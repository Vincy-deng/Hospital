package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.DoctorDao;
import com.neu.dao.UserDao;
import com.neu.vo.DoctorVo;
import com.neu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorDao doctorDao;
    private UserDao userDao;
    @Override
    public List<DoctorVo> selectExample(String pageSize, String pageNum) {
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        PageHelper.startPage(num,size);
        List<DoctorVo> doctorVoList = doctorDao.selectExample();
        if (doctorVoList.size() != 0){
            return doctorVoList;
        }
        return null;
    }
    //添加医生表
    @Override
    public int addDoctor(DoctorVo doctorVo) {
        DoctorVo doctorVo1 = doctorDao.getUidByUserName(doctorVo.getuUserName());
        if (doctorVo1 != null){
            doctorVo.setuId(doctorVo1.getuId());
            return doctorDao.addDoctor(doctorVo);
        }
        return 0;
    }
    //添加用户（医生）
    @Override
    public int addUser(DoctorVo doctorVo) {
        DoctorVo u = doctorDao.getUserByUserName(doctorVo.getuUserName());
        if (u == null){
            return doctorDao.addUser(doctorVo);
        }
        return 0;
    }

    //修改医生
    @Override
    public int udpateDoctor(DoctorVo doctorVo) {
        int a = doctorDao.udpateDoctor(doctorVo);
        int b = doctorDao.updateUserDoctor(doctorVo);
        DoctorVo u = doctorDao.getUserByUserName(doctorVo.getuUserName());
        if (a == 1 && b  == 1 || u==null){
            return 1;
        }
        return 0;
    }

    //删除
    @Override
    public int delDoctor(Integer did, Integer uid) {
        int a = doctorDao.delDoctor(did);
        int b = doctorDao.delUserDoctor(uid);
        if (a==1 && b==1){
            return 1;
        }
        return 0;
    }
}
