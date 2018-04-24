package com.lvzheng.service.sealcarve.enumeration;

/**
 * 经办人类型
 */
public enum AgentTypeEnum {

    Unknow(-1),

    /**
     * 客户经办人
     */
    CustomerAgent(1),

    /**
     * 小薇经办人
     */
    XiaoWeiAgent(2);


    private int type;

    AgentTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    /**
     * 跟据type值获取枚举对像
     * @param type 类型值
     * @return AgentTypeEnum
     */
    public static AgentTypeEnum fromType(int type) {
        AgentTypeEnum[] values = AgentTypeEnum.values();
        for (AgentTypeEnum at : values) {
            if (at.getType() == type) {
                return at;
            }
        }

        return Unknow;
    }
}
