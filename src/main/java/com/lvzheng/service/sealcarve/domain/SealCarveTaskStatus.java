package com.lvzheng.service.sealcarve.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 刻章流程(状态)记录
 *
 */
public class SealCarveTaskStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    //自增ID
    private long id;
    
    //任务ID
    private long taskID;
    
    //状态
    private int status;
    
    //添加时间
    private Date addTime;
    
    //添加用户
    private long empID;
    

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
