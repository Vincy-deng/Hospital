package com.neu.dao;

import com.neu.vo.RecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDao {
    //分页查询病历表
    List<RecordVo> selectExample();
    //添加病历表
    int addRecord(@Param("RecordVo")RecordVo recordVo);
    //根据患者名字,手机号查出患者id（判断添加病历的患者是否在患者表）
    RecordVo getPidByNameAndPhone(@Param("RecordVo")RecordVo recordVo);
    //判断病历表记录是否重复
    RecordVo getRecordByID(@Param("RecordVo")RecordVo recordVo);
    //修改病历表
    int updateRecord(@Param("RecordVo")RecordVo recordVo);
    //删除病历表
    int delRecord(@Param("id")Integer id);
    //根据当前患者登录查看他自己的病历表
    List<RecordVo> getRecordByUid(Integer uId);

}
