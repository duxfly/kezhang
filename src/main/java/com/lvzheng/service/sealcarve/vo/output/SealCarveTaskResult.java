package com.lvzheng.service.sealcarve.vo.output;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class SealCarveTaskResult {

    private int totalCount;

    private List<Integer> buttons;

    private List<SealCarveTaskItem> items;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getButtons() {
        return buttons;
    }

    public void setButtons(List<Integer> buttons) {
        this.buttons = buttons;
    }

    public List<SealCarveTaskItem> getItems() {
        return items;
    }

    public void setItems(List<SealCarveTaskItem> items) {
        this.items = items;
    }


    public static class SealCarveTaskItem {

        private long taskID;

        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
        private Date addTime;

        private String productName;

        private String enterpriseName;

        private long enterpriseID;

        private String status;

        private String agentType;

        private String agentName;

        private String agentTel;

        private Date appointmentTime;

        private String remark;


        public long getTaskID() {
            return taskID;
        }

        public void setTaskID(long taskID) {
            this.taskID = taskID;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public long getEnterpriseID() {
            return enterpriseID;
        }

        public void setEnterpriseID(long enterpriseID) {
            this.enterpriseID = enterpriseID;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAgentType() {
            return agentType;
        }

        public void setAgentType(String agentType) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
