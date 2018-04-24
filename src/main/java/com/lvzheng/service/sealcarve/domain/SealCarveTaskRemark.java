package com.lvzheng.service.sealcarve.domain;

import java.io.Serializable;
import java.util.Date;

public class SealCarveTaskRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    //自增ID
    private long id;

    //任务ID
    private long taskID;
    
    //添加时间
    private Date addTime;
    
    //添加用户
    private long empID;

    //备注
    private String remark;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public long getEmpID() {
        return empID;
    }

    public void setEmpID(long empID) {
        this.empID = empID;
    }
    
}
