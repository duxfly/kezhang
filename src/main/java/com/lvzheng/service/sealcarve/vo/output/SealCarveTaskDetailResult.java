package com.lvzheng.service.sealcarve.vo.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.vo.input.EnterpriseInfoVO;

import java.util.Date;
import java.util.List;

public class SealCarveTaskDetailResult {

    private long taskID;

    private EnterpriseInfoVO enterpriseInfo;

    private List<MaterialLog> materialLog;

    private List<RemarkLog> remarkLog;


    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public EnterpriseInfoVO getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(EnterpriseInfoVO enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }

    public List<MaterialLog> getMaterialLog() {
        return materialLog;
    }

    public void setMaterialLog(List<MaterialLog> materialLog) {
        this.materialLog = materialLog;
    }

    public List<RemarkLog> getRemarkLog() {
        return remarkLog;
    }

    public void setRemarkLog(List<RemarkLog> remarkLog) {
        this.remarkLog = remarkLog;
    }


    public static class MaterialLog {

        private long flowID;

        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
        private Date addTime;

        private String takeOverEmpName;

        private long takeOverEmpID;

        private String handOverEmpName;

        private long handOverEmpID;

        private List<Material> materials;


        public long getFlowID() {
            return flowID;
        }

        public void setFlowID(long flowID) {
            this.flowID = flowID;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }

        public String getTakeOverEmpName() {
            return takeOverEmpName;
        }

        public void setTakeOverEmpName(String takeOverEmpName) {
            this.takeOverEmpName = takeOverEmpName;
        }

        public long getTakeOverEmpID() {
            return takeOverEmpID;
        }

        public void setTakeOverEmpID(long takeOverEmpID) {
            this.takeOverEmpID = takeOverEmpID;
        }

        public String getHandOverEmpName() {
            return handOverEmpName;
        }

        public void setHandOverEmpName(String handOverEmpName) {
            this.handOverEmpName = handOverEmpName;
        }

        public long getHandOverEmpID() {
            return handOverEmpID;
        }

        public void setHandOverEmpID(long handOverEmpID) {
            this.handOverEmpID = handOverEmpID;
        }

        public List<Material> getMaterials() {
            return materials;
        }

        public void setMaterials(List<Material> materials) {
            this.materials = materials;
        }
    }

    public static class RemarkLog {

        private long remarkID;

        private long empID;

        private Date addTime;

        private String empName;

        private String remark;


        public long getRemarkID() {
            return remarkID;
        }

        public void setRemarkID(long remarkID) {
            this.remarkID = remarkID;
        }

        public long getEmpID() {
            return empID;
        }

        public void setEmpID(long empID) {
            this.empID = empID;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}
