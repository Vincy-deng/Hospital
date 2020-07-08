package com.neu.dao;

import com.neu.vo.AppointmentVo;
import com.neu.vo.DoctorVo;
import com.neu.vo.PatientVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatientDao {

    int addPatient(@Param("PatientVo") PatientVo patientVo);

    PatientVo getPatientByPhone(String pPhone);

    List<AppointmentVo> getAppointmentList();

    int addAppointment(@Param("AppointmentVo") AppointmentVo appointmentVo);

    int delAppointment(@Param("aId") int aId);

    AppointmentVo getAppointmentByFlag(@Param("aId") int aId);

    int updateAppointment(@Param("AppointmentVo") AppointmentVo appointmentVo);

    List<DoctorVo> getDIdByDoctor(@Param("dId") int dId);

    List<AppointmentVo> getAppointmentByDid(@Param("dId") Integer dId);

    String getDnaemByDoctor(@Param("appointmentVo") AppointmentVo appointmentVo);

    List<DoctorVo> getDoctorByName(@Param("getDname") List<DoctorVo> getDname);

    Integer getDoctorId(@Param("dName") String dName);

    List<AppointmentVo> selectExample();

    PatientVo getUserByUserName(@Param("pUserName") String pUserName);

    int addUser(@Param("PatientVo") PatientVo patientVo);
}
