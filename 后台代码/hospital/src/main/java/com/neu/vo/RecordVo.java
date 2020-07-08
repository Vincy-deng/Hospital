package com.neu.vo;

/**
 * 分页查询病历实体
 */
public class RecordVo {
    //医生名称
    private String dName;
    //患者名称
    private String pName;
    //手机号
    private long rPhone;
    //病历表Id
    private Integer rId;
    //医生ID
    private Integer dId;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    //患者ID
    private Integer pId;
    //症状
    private String rSymptom;
    //诊断
    private String rDiagnosis;
    //处方
    private String rPrescription;
    //创建时间
    private String rCreatedate;
    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getdName() {
        return dName;
    }
    public long getrPhone() {
        return rPhone;
    }

    public void setrPhone(long rPhone) {
        this.rPhone = rPhone;
    }
    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getrSymptom() {
        return rSymptom;
    }

    public void setrSymptom(String rSymptom) {
        this.rSymptom = rSymptom;
    }

    public String getrDiagnosis() {
        return rDiagnosis;
    }

    public void setrDiagnosis(String rDiagnosis) {
        this.rDiagnosis = rDiagnosis;
    }

    public String getrPrescription() {
        return rPrescription;
    }

    public void setrPrescription(String rPrescription) {
        this.rPrescription = rPrescription;
    }

    public String getrCreatedate() {
        return rCreatedate;
    }

    public void setrCreatedate(String rCreatedate) {
        this.rCreatedate = rCreatedate;
    }
}
