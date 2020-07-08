package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.RecordDao;
import com.neu.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordDao recordDao;
    @Override
    //分页查询病历表
    public List<RecordVo> selectExample(String pageSize, String pageNum) {
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        PageHelper.startPage(num,size);
        List<RecordVo> recordVoList = recordDao.selectExample();
            if (recordVoList.size() != 0){
                return recordVoList;
            }
        return null;
    }

    //添加病历
    @Override
    public int addRecord(RecordVo recordVo) {
        //判断病历表记录是否重复

        //添加病历，判断患者是否存在患者表
        //存在能添加,
        int flag=0;
        RecordVo records = recordDao.getPidByNameAndPhone(recordVo);
        //存在记录不能重复添加
        RecordVo rec = recordDao.getRecordByID(recordVo);
        //不等于null说明该患者已存在患者表，可以添加病历
        // 等于null说明患者表不存在该患者，不能添加
        if (records != null && rec == null){
            recordVo.setpId(records.getpId());
            flag = recordDao.addRecord(recordVo);
            return flag;
        }else if (records == null){
//            等于null说明患者表不存在该患者，不能添加
            return 2;
        }else if (rec != null){
            //rec != null说明已存在该病历不能重复添加
            return 3;
        }
        return 0;
    }

    //根据RID修改病历表
    @Override
    public int updateRecord(RecordVo recordVo) {
       return recordDao.updateRecord(recordVo);
    }

    //删除病历表
    @Override
    public int delRecord(Integer id) {
        return recordDao.delRecord(id);
    }
    //根据当前患者登录查看他自己的病历表
    @Override
    public List<RecordVo> getRecordByUid(Integer uId) {
        List<RecordVo> recordVoList = recordDao.getRecordByUid(uId);
        if (recordVoList.size() != 0){
            return recordVoList;
        }
        return null;
    }

}
