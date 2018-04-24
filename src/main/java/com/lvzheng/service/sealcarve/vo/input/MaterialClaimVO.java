package com.lvzheng.service.sealcarve.vo.input;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class MaterialClaimVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private long enterpriseID;

    private int[] materialIds;

    @Pattern(regexp = "[0-9a-z]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "code错误")
    private String dingTalkCode;

    /**
     * 获得以逗号分隔的材料id
     * @return
     */
    public String getStrMaterialIds() {
        if (materialIds == null || materialIds.length == 0) {
            return null;
        }

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < materialIds.length; i++) {
            if (i > 0) {
                ids.append(',');
            }
            ids.append(materialIds[i]);
        }
        return ids.toString();
    }


    public String getDingTalkCode() {
        return dingTalkCode;
    }

    public void setDingTalkCode(String dingTalkCode) {
        this.dingTalkCode = dingTalkCode;
    }

    public int[] getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(int[] materialIds) {
        this.materialIds = materialIds;
    }

    public long getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }
}
