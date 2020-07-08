package com.neu.controller;

import com.neu.service.PatientService;
import com.neu.util.BaseResponse;
import com.neu.util.IDUtil;
import com.neu.util.ListVoUntil;
import com.neu.vo.AppointmentVo;
import com.neu.vo.PatientVo;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("patientController")
@RestController
@RequestMapping("patientController")
public class PatientController {
    @Autowired
    private PatientService patientService;

    private final Log log= LogFactory.getLog(getClass());

    /**
     * 新增患者信息
     * @param
     * @param patientVo
     * @return
     */
    @PostMapping("addPatien")
    public  Object addPatien(@RequestBody PatientVo patientVo){
        try {
            System.out.println("======================================"+patientVo);
            if (!StringUtils.isEmpty(patientVo.getPPhone())){
                if ( patientService.ispPhone(patientVo.getPPhone())){
                    return BaseResponse.generateBadResponseEntity(500,"手机号不能重复","");
                }
            }

                int row = patientService.addUser(patientVo);
                if (row != 0) {
                    int flag = patientService.addPatient(patientVo);
                    if (flag == 1) {
                        return BaseResponse.generateOKResponseEntity("注册成功！", "");
                    } else {
                        return BaseResponse.generateBadResponseEntity(500, "注册失败", "");
                    }

                }
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("PatientController----->>>updateUser",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }

    /**
     * 获取预约挂号列表
     * @param
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/list")
//    public ResponseEntity<BaseResponse<ListVoUntil<AppointmentVo>>>
    public Object getAppointmentList(@RequestParam(defaultValue = "5") String pageSize, @RequestParam(defaultValue = "1")String pageNum){
//    (AppointmentVo appointmentVo, @RequestParam(defaultValue = "1") String pageSize,
//                  @RequestParam(defaultValue = "10") String pageNum) {
        try {
            List<AppointmentVo> appointmentVoList = patientService.getAppointmentList(pageSize,pageNum);
            System.out.println("============================================"+appointmentVoList);
            return BaseResponse.generateOKListResponseEntity(appointmentVoList);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }

    /**
     * 添加预约信息
     * @param
     * @param appointmentVo
     * @return
     */
    @PostMapping("addAppointment")
    public ResponseEntity<BaseResponse<AppointmentVo>> Appointment(HttpServletRequest request, @RequestBody AppointmentVo appointmentVo){
        try {
            if (StringUtils.isEmpty(appointmentVo.getDId())){
                return BaseResponse.generateBadResponseEntity(500,"请选择预约的医生","");
            }
            if (StringUtils.isEmpty(appointmentVo.getFlag())){
                appointmentVo.setFlag(0);
            }
            int flag=patientService.addAppointment(appointmentVo);
            if (flag==1){
                return BaseResponse.generateOKResponseEntity("预约成功！","");
            }else {
                return BaseResponse.generateBadResponseEntity(500,"预约失败","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("PatientController----->>>updateUser",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }


    /**
     * 删除预约信息
     * @param
     * @param appointmentVo
     * @return
     */
    @PostMapping("delAppointment")
    public Object delAppointment(@RequestBody AppointmentVo appointmentVo){
        try {
            int flag=patientService.delAppointment(appointmentVo.getAId());
            if (flag==1){
                return BaseResponse.generateOKResponseEntity("预约删除成功!","");
            }else {
                return BaseResponse.generateBadResponseEntity(500,"删除预约失败","");
            }
        } catch (Exception e) {
            log.debug("",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }

    /***
     * 修改预约信息
     * @param
     * @param appointmentVo
     * @return
     */
    @PostMapping("updateAppointment")
    public Object updateAppointment(@RequestBody AppointmentVo appointmentVo){
        try {
            System.out.println("=================================="+appointmentVo);
            int flag =patientService.updateAppointment(appointmentVo);
            if (flag==1){
                return BaseResponse.generateOKResponseEntity("预约编辑成功！","");
            }else {
                return BaseResponse.generateBadResponseEntity(500,"预约编辑失败！","");
            }
        } catch (Exception e) {
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }

    @GetMapping("getAppointmentByDid")
    public Object getRecordByDid(Integer dId){
        try {
            List<AppointmentVo> appointmentList = patientService.getRecordByAid(dId);
            if (appointmentList!= null){
                return BaseResponse.generateOKListResponseEntity(appointmentList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败","");
        }catch (Exception e){
            log.debug("RecordController-->>RecordService",e);
            return BaseResponse.generateBadResponseEntity(500,"系统异常","");
        }
    }
}
