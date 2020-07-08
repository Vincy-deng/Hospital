package com.neu.service;
import com.neu.vo.AppointmentVo;
import com.neu.vo.PatientVo;

import java.util.List;

public interface PatientService {

    int addPatient(PatientVo patientVo);

    boolean ispPhone(String pPhone);

    List<AppointmentVo> getAppointmentList(String pageSize, String pageNum);

    int addAppointment(AppointmentVo appointmentVo);

    int delAppointment(int aId);

    int updateAppointment(AppointmentVo appointmentVo);

    List<AppointmentVo> getRecordByAid(Integer dId);

    int addUser(PatientVo patientVo);
}
