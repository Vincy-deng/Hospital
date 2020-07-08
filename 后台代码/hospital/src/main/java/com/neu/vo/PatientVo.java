package com.neu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PatientVo {
    @ApiModelProperty("患者id")
    private int pId;

    @ApiModelProperty("姓名")
    private String pName;

    @ApiModelProperty("生日")
    private String pBirth;

    @ApiModelProperty("性别")
    private String pSex;

    @ApiModelProperty("患者登录用户名")
    private String pUserName;

    @ApiModelProperty("患者登录密码")
    private String pPassWord;

    @ApiModelProperty("地址")
    private String pAddress;

    @ApiModelProperty("电话")
    private String pPhone;

    @ApiModelProperty("登录（用户）id")
    private int pUid;


}
