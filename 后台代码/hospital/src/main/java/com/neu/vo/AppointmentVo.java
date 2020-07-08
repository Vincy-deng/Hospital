package com.neu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppointmentVo {
    @ApiModelProperty("预约id")
    private Integer aId;

    @ApiModelProperty("医生id")
    private Integer dId;

    @ApiModelProperty("医生姓名")
    private String dName;



    @ApiModelProperty("患者姓名")
    private String pName;

    @ApiModelProperty("患者id")
    private Integer pId;

    @ApiModelProperty("预约描述")
    private String aDescription;

    @ApiModelProperty("创建时间")
    private String aCreatedate;

    @ApiModelProperty("生效时间")
    private String aEffectdate;

    @ApiModelProperty("失效时间")
    private String aExpiredate;

    @ApiModelProperty("是否接诊")
    private Integer Flag;



}
