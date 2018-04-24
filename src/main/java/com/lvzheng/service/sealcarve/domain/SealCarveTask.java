package com.lvzheng.service.sealcarve.domain;

import java.io.Serializable;
import java.util.Date;

public class SealCarveTask implements Serializable {

    private static final long serialVersionUID = 1L;

    //自增ID
    private long id;
    
    //公司ID
    private long enterpriseID;

    //公司名（冗余字段，避免联表）
    private String enterpriseName;

    //经办人（冗余字段，避免联表）
    private String agentName;

    //母任务ID
    private long masterTaskID;
    
    //订单ID
    private long orderID;

    //产品ID
    private long productID;

    //预约时间
    private Date appointmentTime;
    
    //状态
    private int status;

    //添加时间
    private Date addTime;

    //备注，与备注表中该任务最后一条备注一至
    private String remark;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getMasterTaskID() {
        return masterTaskID;
    }

    public void setMasterTaskID(long masterTaskID) {
        this.masterTaskID = masterTaskID;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public long getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
