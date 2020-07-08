package com.neu.vo;

import lombok.Data;

@Data
public class DoctorVo {
    //医生表ID
    private Integer dId;
    //医生名
    private String dName;
    //医生登录用户名
    private String uUserName;
    //医生登录密码
    private String uPassWord;
    //医生职称
    private String dTitle;
    //医生性别
    private String dSex;
    //医生科室
    private Object dSubject;
    //医生学历
    private String dEducation;
    //uid、
    private Integer uId;


    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getuUserName() {
        return uUserName;
    }

    public void setuUserName(String uUserName) {
        this.uUserName = uUserName;
    }

    public String getuPassWord() {
        return uPassWord;
    }

    public void setuPassWord(String uPassWord) {
        this.uPassWord = uPassWord;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public String getdSex() {
        return dSex;
    }

    public void setdSex(String dSex) {
        this.dSex = dSex;
    }

    public Object getdSubject() {
        return dSubject;
    }

    public void setdSubject(Object dSubject) {
        this.dSubject = dSubject;
    }

    public void setdSubject(String dSubject) {
        this.dSubject = dSubject;
    }

    public String getdEducation() {
        return dEducation;
    }

    public void setdEducation(String dEducation) {
        this.dEducation = dEducation;
    }
}
