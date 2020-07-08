package com.neu.controller;

import com.neu.service.RecordService;
import com.neu.util.BaseResponse;
import com.neu.vo.RecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController(value = "病历管理")
@RequestMapping("/Record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    private final Log log = LogFactory.getLog(getClass());
    @ApiOperation(value = "分页查询病历表",notes ="分页查询病历表" )
    @GetMapping("listRecord")
    public Object listRecord(@RequestParam(defaultValue = "5") String pageSize, @RequestParam(defaultValue = "1")String pageNum){
        try {
            List<RecordVo> recordVoList = recordService.selectExample(pageSize,pageNum);
            if (recordVoList.size() !=0){
                return BaseResponse.generateOKListResponseEntity(recordVoList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败，信息不存在","");
        }catch (Exception e){
            log.debug("RecordController-->>listRecord",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "新增病历表",notes ="新增病历表" )
    @PostMapping("addRecord")
    public Object addRecord(@RequestBody RecordVo recordVo){
        try {
           int flag = recordService.addRecord(recordVo);
           if (flag == 1){
               return BaseResponse.generateOKResponseEntity("添加成功");
           }else if (flag == 2){
               return BaseResponse.generateBadResponseEntity(500,"该患者不存在，请勿添加病历","");
           }else if (flag ==3){
               return BaseResponse.generateBadResponseEntity(500,"该病历已存在，请勿重复添加病历","");
           }
            return BaseResponse.generateBadResponseEntity(500,"添加失败","");
         }catch (Exception e){
            log.debug("RecordController-->>RecordService",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "修改病历表",notes ="修改病历表" )
    @PostMapping("updateRecord")
    public  Object updateRecord(@RequestBody RecordVo recordVo){
        try{
            int flag = recordService.updateRecord(recordVo);
            if (flag == 1){
                return BaseResponse.generateOKResponseEntity("修改成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }catch (Exception e){
            log.debug("RecordController-->>RecordService",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "删除病历表",notes ="删除病历表" )
    @PostMapping("delRecord")
    public Object delRecord(@RequestParam Integer id){
        try {
           int flag = recordService.delRecord(id);
           if (flag == 1){
               return BaseResponse.generateOKResponseEntity("删除成功");
           }
           return BaseResponse.generateBadResponseEntity(500,"删除失败,病历表不存在","");
        }catch (Exception e){
            log.debug("RecordController-->>RecordService",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "查看病历",notes ="查看病历" )
    @GetMapping("getRecordByUid")
    public Object getRecordByUid(Integer uId){
        try {
            List<RecordVo> recordVoList = recordService.getRecordByUid(uId);
            if (recordVoList!= null){
                return BaseResponse.generateOKListResponseEntity(recordVoList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败","");
        }catch (Exception e){
            log.debug("RecordController-->>RecordService",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
}
