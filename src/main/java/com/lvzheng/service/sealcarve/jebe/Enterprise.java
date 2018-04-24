package com.lvzheng.service.sealcarve.jebe;

import java.util.Date;

public class Enterprise {

    /*lv_enterprise相关字段*/
    //企业ID
    private long enterpriseID;

    //企业名
    private String name;

    //经办人类型
    private int transactorType;

    //经办人名字
    private String transactorName;

    //经办人电话
    private String transactorPhone;

    //修改用户类型
    private String modifyUserType;

    //最后修改用户ID
    private long modifyUserID;

    //最后修改时间
    private Date modifyDate;


    /*lv_enterprise_role_relation*/
    private long relationID;


    /*lv_enterprise_ext相关字段*/
    private long extID;

    //统一社会代码
    private String unifiedSocialCreditCode;


    /*lv_enterprise_person相关字段*/
    //法人ID
    private long legalPersonID;

    //法人名称
    private String legalPersonName;

    //法人电话
    private String legalPersonTel;

    //法人证件类型
    private String legalPersonIDType;

    //法人证件号
    private String legalPersonIDNum;

    //法人户籍地址地址
    private String legalPersonResidenceAddress;


    public long getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTransactorType() {
        return transactorType;
    }

    public void setTransactorType(int transactorType) {
        this.transactorType = transactorType;
    }

    public String getTransactorName() {
        return transactorName;
    }

    public void setTransactorName(String transactorName) {
        this.transactorName = transactorName;
    }

    public String getTransactorPhone() {
        return transactorPhone;
    }

    public void setTransactorPhone(String transactorPhone) {
        this.transactorPhone = transactorPhone;
    }

    public String getModifyUserType() {
        return modifyUserType;
    }

    public void setModifyUserType(String modifyUserType) {
        this.modifyUserType = modifyUserType;
    }

    public long getModifyUserID() {
        return modifyUserID;
    }

    public void setModifyUserID(long modifyUserID) {
        this.modifyUserID = modifyUserID;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getLegalPersonID() {
        return legalPersonID;
    }

    public void setLegalPersonID(long legalPersonID) {
        this.legalPersonID = legalPersonID;
    }

    public String getLegalPersonTel() {
        return legalPersonTel;
    }

    public void setLegalPersonTel(String legalPersonTel) {
        this.legalPersonTel = legalPersonTel;
    }

    public String getLegalPersonIDType() {
        return legalPersonIDType;
    }

    public void setLegalPersonIDType(String legalPersonIDType) {
        this.legalPersonIDType = legalPersonIDType;
    }

    public String getLegalPersonIDNum() {
        return legalPersonIDNum;
    }

    public void setLegalPersonIDNum(String legalPersonIDNum) {
        this.legalPersonIDNum = legalPersonIDNum;
    }

    public String getLegalPersonResidenceAddress() {
        return legalPersonResidenceAddress;
    }

    public void setLegalPersonResidenceAddress(String legalPersonResidenceAddress) {
        this.legalPersonResidenceAddress = legalPersonResidenceAddress;
    }

    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public long getExtID() {
        return extID;
    }

    public void setExtID(long extID) {
        this.extID = extID;
    }
}
