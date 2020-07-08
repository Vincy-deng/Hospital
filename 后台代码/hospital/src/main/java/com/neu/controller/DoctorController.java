package com.neu.controller;

import com.neu.service.DoctorService;
import com.neu.util.BaseResponse;
import com.neu.vo.DoctorVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    private final Log log = LogFactory.getLog(getClass());
    @ApiOperation(value = "分页查询医生管理表",notes ="分页查询医生管理表" )
    @GetMapping("listRecord")
    public  Object listRecord(@RequestParam(defaultValue = "5") String pageSize, @RequestParam(defaultValue = "1")String pageNum){
        try {
            List<DoctorVo> doctorVoList = doctorService.selectExample(pageSize,pageNum);
            if (doctorVoList.size() != 0){
                return BaseResponse.generateOKListResponseEntity(doctorVoList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败，信息不存在","");
        }catch (Exception e){
            log.debug("RecordController-->>listRecord",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "添加医生管理表",notes ="添加医生管理表" )
    @PostMapping("addDoctor")
    public  Object listRecord(@RequestBody DoctorVo doctorVo){
        try {
            int flag = doctorService.addUser(doctorVo);
            if (flag == 1){
                int row = doctorService.addDoctor(doctorVo);
                if (row == 1){
                    return BaseResponse.generateOKResponseEntity("添加成功");
                }else if (row == 0){
                    return BaseResponse.generateBadResponseEntity(500,"添加失败","");
                }
        }
            return BaseResponse.generateBadResponseEntity(500,"添加失败,该医生已存在","");
        }catch (Exception e){
            log.debug("RecordController-->>listRecord",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");

        }
    }
    @ApiOperation(value = "修改医生管理表",notes ="修改医生管理表" )
    @PostMapping("updateDoctor")
    public  Object updateDoctor(@RequestBody DoctorVo doctorVo){
        try {
            int flag = doctorService.udpateDoctor(doctorVo);
            if (flag == 1){
                return BaseResponse.generateOKResponseEntity("修改成功");
            }else if (flag == 0){
                return BaseResponse.generateBadResponseEntity(500,"修改失败,用户名已存在","");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }catch (Exception e){
            log.debug("RecordController-->>listRecord",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
    @ApiOperation(value = "删除医生管理表",notes ="删除医生管理表" )
    @PostMapping("delDoctor")
    public Object delDoctor(Integer did,Integer uid){
        try {
           int flag = doctorService.delDoctor(did,uid);
           if (flag == 1){
               return BaseResponse.generateOKResponseEntity("删除成功");
           }
           return BaseResponse.generateBadResponseEntity(500,"删除失败，已删除","");
        }catch (Exception e){
            log.debug("RecordController-->>listRecord",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");

        }
    }
//    @PostMapping("/addUser")
//    @ApiOperation(value = "/addUser",notes = "添加用户（医生）")
//    public Object addUser(@RequestBody DoctorVo doctorVo){
//        try {
//            int flag = doctorService.addUser(doctorVo);
//            if (flag == 1){
//                return BaseResponse.generateOKResponseEntity("添加成功");
//            }
//            return BaseResponse.generateBadResponseEntity(500,"添加失败,该医生已存在","");
//        }catch (Exception e){
//            log.debug("UserController-->>updateUserById",e);
//            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
//
//        }
//    }
}
