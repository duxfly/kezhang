package com.lvzheng.service.sealcarve.enumeration;

/**
 * 刻章任务状态枚举
 *
 */
public enum SealCarveTaskStatusEnum {

    Unknow(-1, "未知"),

    Init(0, "未开始"),
    
    Entered(1, "已录入"),
    
    Carried(2, "已承接"),
    
    Engraved(3, "已刻制"),
    
    Uploaded(4, "已上传"),
    
    Delivered(5, "已交付"),
    
    CustomerRecaption(51, "已交付:客户取回"),
    
    CompanyRecaption(52, "已交付:公司取回");
    
    
    private int status;
    private String description;
    
    SealCarveTaskStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    /**
     * 根据状态值获取枚举实例
     * @param status 状态值
     * @return
     */
    public static SealCarveTaskStatusEnum fromStatusValue(int status) {
        SealCarveTaskStatusEnum[] values = SealCarveTaskStatusEnum.values();
        for (SealCarveTaskStatusEnum em : values) {
            if (em.getStatus() == status) {
                return em;
            }
        }

        return Unknow;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
