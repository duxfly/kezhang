package com.lvzheng.service.sealcarve.vo.input;

import javax.validation.constraints.NotNull;

public class SealCarveTaskStatusVO {

    @NotNull
    private long[] taskIds;

    private int status;


    public long[] getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(long[] taskIds) {
        this.taskIds = taskIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
