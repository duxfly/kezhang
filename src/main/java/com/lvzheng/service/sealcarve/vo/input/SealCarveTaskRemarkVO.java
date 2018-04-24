package com.lvzheng.service.sealcarve.vo.input;

import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.Size;

public class SealCarveTaskRemarkVO {

    private long taskID;

    @Size(min = 1, max = 500)
    private String remark;


    /**
     * 安全获取备注
     * @return
     */
    public String getRemark() {
        return StringEscapeUtils.escapeHtml4(this.remark);
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
