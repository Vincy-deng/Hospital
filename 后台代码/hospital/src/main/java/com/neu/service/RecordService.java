package com.neu.service;

import com.neu.vo.RecordVo;

import java.util.List;

public interface RecordService {
    List<RecordVo> selectExample(String pageSize, String pageNum);
    int addRecord(RecordVo recordVo);
    int updateRecord(RecordVo recordVo);
    int delRecord(Integer id);
    List<RecordVo> getRecordByUid(Integer uId);
}
