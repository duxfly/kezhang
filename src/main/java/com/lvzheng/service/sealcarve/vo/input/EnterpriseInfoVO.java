package com.lvzheng.service.sealcarve.vo.input;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class EnterpriseInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long taskID;

    private int agentType;

    @Pattern(regexp = "[0-9a-z\\s\\*\\u4e00-\\u9fa5]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "经办人名错误")
    private String agentName;

    @Pattern(regexp = "[0-9]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "经办人电话错误")
    @Size(max = 15)
    private String agentTel;

    private Date appointmentTime;

    @Pattern(regexp = "[0-9a-z]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "统一社会识别码错误")
    @Size(max = 18)
    private String unitSocietyCode;

    @Pattern(regexp = "[0-9a-z\\s\\u4e00-\\u9fa5]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "法人名错误")
    private String legalPerson;

    @Pattern(regexp = "[0-9]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "电话错误")
    @Size(max = 15)
    private String tel;

    private int certificateType;

    @Pattern(regexp = "[0-9]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "证件号码错误")
    @Size(max = 20)
    private String certificateNum;

    @Size(max = 500)
    private String censusRegister;


    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public int getAgentType() {
        return agentType;
    }

    public void setAgentType(int agentType) {
        this.agentType = agentType;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getUnitSocietyCode() {
        return unitSocietyCode;
    }

    public void setUnitSocietyCode(String unitSocietyCode) {
        this.unitSocietyCode = unitSocietyCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(int certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCensusRegister() {
        return censusRegister;
    }

    public void setCensusRegister(String censusRegister) {
        this.censusRegister = censusRegister;
    }
}
