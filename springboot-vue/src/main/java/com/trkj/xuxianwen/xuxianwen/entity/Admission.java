package com.trkj.xuxianwen.xuxianwen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
@TableName("admission")
public class Admission {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Integer socialSecurity;
    private String company;
    private Integer phone;
    private String adress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GKT+8")
    private Date donmtime;
    private String diagnosis;
    private Integer inpatientNumber;
    private String placeOfOccurrence;
    private String declarant;
    private String relationship;
    private String preparer;
    private String patient;
}
