package com.lvzheng.service.sealcarve.enumeration;

/**
 * 证件类型
 */
public enum CertificateTypeEnum {

    Unknow(-1),

    /**
     * 身份证
     */
    IdentityCard(1),

    /**
     * 户照
     */
    Passport(2);


    private int type;

    CertificateTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    /**
     * 跟据type值获取枚举对像
     * @param type 类型值
     * @return CertificateTypeEnum
     */
    public static CertificateTypeEnum fromType(int type) {
        CertificateTypeEnum[] values = CertificateTypeEnum.values();
        for (CertificateTypeEnum at : values) {
            if (at.getType() == type) {
                return at;
            }
        }

        return Unknow;
    }

}
