package com.neu.service;

import com.neu.vo.DoctorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorService {
    //分页查询
    List<DoctorVo> selectExample(String pageSize,String pageNum);
    //添加
    int addDoctor(DoctorVo doctorVo);
    //添加用户（医生）
    int addUser(DoctorVo doctorVo);
    //修改用户
    int udpateDoctor(DoctorVo doctorVo);
    //删除
    int delDoctor(Integer did,Integer uid);
}
