package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.PatientDao;
import com.neu.util.BaseResponse;
import com.neu.vo.AppointmentVo;
import com.neu.vo.DoctorVo;
import com.neu.vo.PatientVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;

    private final Log log= LogFactory.getLog(getClass());


    @Override
    public int addPatient(PatientVo patientVo) {
        try {
            return patientDao.addPatient(patientVo);
        } catch (Exception e) {
            log.debug("PatientServiceImpl----->>>addPatient",e);
        }
        return 0;
    }

    @Override
    public boolean ispPhone(String pPhone) {
        try {
            PatientVo patientVo=patientDao.getPatientByPhone(pPhone);
            if (patientVo!=null){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            log.debug("PatientServiceImpl----->>>isPhone",e);
            return true;
        }
    }

    @Override
    public List<AppointmentVo> getAppointmentList(String pageSize, String pageNum) {
        try {
            int pageIndex=Integer.parseInt(pageNum);
            int pageSizeInt=Integer.parseInt(pageSize);
            PageHelper.startPage(pageIndex,pageSizeInt);
            List<AppointmentVo> AppointmentVoList = patientDao.selectExample();
            if (AppointmentVoList.size() != 0){
                return AppointmentVoList;
            }
            return patientDao.getAppointmentList();
        } catch (NumberFormatException e) {
            log.debug("PatientServiceImpl----->>>isPhone",e);
        }
        return null;
    }

    @Override
    public int addAppointment(AppointmentVo appointmentVo) {
        try {
            return patientDao.addAppointment(appointmentVo);
        } catch (Exception e) {
            log.debug("PatientServiceImpl----->>>addPatient",e);
        }
        return 0;
    }

    @Override
    public int delAppointment(int aId){
        try {
            return patientDao.delAppointment(aId);
        } catch (Exception e) {
            log.debug("PatientServiceImpl----->>>delAppointment",e);
        }
        return 0;
    }

    @Override
    public int updateAppointment(AppointmentVo appointmentVo) {
        int flag=0;
        try {
//            System.out.println("----------------------------------------------"+appointmentVo.getAId());
            if (appointmentVo.getDId() != 0){
                List<DoctorVo> getDid=patientDao.getDIdByDoctor(appointmentVo.getDId());
                if (!StringUtils.isEmpty(getDid)){
                    flag=patientDao.updateAppointment(appointmentVo);
                    System.out.println("-------------------------------------------"+flag);
                    return flag;
                    }
            }
        } catch (Exception e) {
            log.debug("PatientServiceImpl----->>>updateAppointment",e);
        }
        return flag;
    }

    @Override
    public List<AppointmentVo> getRecordByAid(Integer dId) {
        List<AppointmentVo> appointmentVoList = patientDao.getAppointmentByDid(dId);
        System.out.println("==============================================="+appointmentVoList);
        if (appointmentVoList.size() != 0){
            return appointmentVoList;
        }
        return null;
    }

    @Override
    public int addUser(PatientVo patientVo) {
        PatientVo p = patientDao.getUserByUserName(patientVo.getPUserName());
        if (p == null){
            return patientDao.addUser(patientVo);
        }
        return 0;
    }
}
