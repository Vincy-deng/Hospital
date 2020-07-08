package com.neu.dao;

import com.neu.vo.DoctorVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDao {
    //分页查询医生管理
    List<DoctorVo> selectExample();
    //新增医生管理信息
    int addDoctor(@Param("DoctorVo")DoctorVo doctorVo);
    //添加用户
    int addUser(@Param("DoctorVo")DoctorVo doctorVo);
    //判断用户是否重复
    DoctorVo getUserByUserName(@Param("username")String username);
    //查出刚刚新增的这一条信息的uid
    DoctorVo getUidByUserName(@Param("username")String username);
    //修改医生表的记录
    int udpateDoctor(@Param("DoctorVo")DoctorVo doctorVo);
    //修改医生的用户表记录
    int updateUserDoctor(@Param("DoctorVo")DoctorVo doctorVo);
    //删除医生表记录
    int delDoctor(@Param("did")Integer did);
    //删除医生得用户表记录
    int delUserDoctor(@Param("uid")Integer uid);

}
