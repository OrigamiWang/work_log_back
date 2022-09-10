package com.wyx.testspringboot.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Worklog implements Serializable {
    @ApiModelProperty(value = "主键自增")
    private Integer no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String empId;

    @ApiModelProperty(value = "创建或修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "日志记录的是哪一天")
    private Date logDay;

    @ApiModelProperty(value = "日志内容")
    private String content;

    private Date workTime;

    private static final long serialVersionUID = 1L;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLogDay() {
        return logDay;
    }

    public void setLogDay(Date logDay) {
        this.logDay = logDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", no=").append(no);
        sb.append(", name=").append(name);
        sb.append(", empId=").append(empId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", logDay=").append(logDay);
        sb.append(", content=").append(content);
        sb.append(", workTime=").append(workTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}