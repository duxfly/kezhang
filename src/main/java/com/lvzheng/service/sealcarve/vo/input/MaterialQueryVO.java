package com.lvzheng.service.sealcarve.vo.input;

import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class MaterialQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private int pageIndex = 1;

    private int pageSize = 30;

    @Pattern(regexp = "[0-9a-z\\s\\*\\u4e00-\\u9fa5]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "公司名非法")
    private String enterpriseName;

    @Pattern(regexp = "[0-9a-z]+", flags = Pattern.Flag.CASE_INSENSITIVE, message = "code错误")
    private String dingTalkCode;


    /**
     * 安全获取公司名
     * @return
     */
    public String getEnterpriseName() {
        return StringEscapeUtils.escapeHtml4(this.enterpriseName);
    }

    /**
     * 获取页码，pageIndex小于等于0则返回1
     * @return
     */
    public int getPageIndex() {
        if (pageIndex <= 0) {
            return 1;
        }

        return pageIndex;
    }

    /**
     * 获取每页显示记录数，pageSize小于等于0则返回0，大于200则返回30
     * @return
     */
    public int getPageSize() {
        if (pageSize <= 0) {
            return 0;
        }
        if (pageSize > 200) {
            return 30;
        }

        return pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDingTalkCode() {
        return dingTalkCode;
    }

    public void setDingTalkCode(String dingTalkCode) {
        this.dingTalkCode = dingTalkCode;
    }
}
