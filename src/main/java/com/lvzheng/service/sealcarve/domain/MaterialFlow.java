package com.lvzheng.service.sealcarve.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 材料认证流程
 */
public class MaterialFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    //自增ID
    private long id;

    //认领时间
    private Date claimTime;

    //公司ID
    private long enterpriseID;

    //公司名
    private String enterpriseName;

    //员工ID
    private long empID;

    //认领的材料ID,以逗号分隔
    private String materialIds;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
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

    public long getEmpID() {
        return empID;
    }

    public void setEmpID(long empID) {
        this.empID = empID;
    }

    public String getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(String materialIds) {
        this.materialIds = materialIds;
    }

}
