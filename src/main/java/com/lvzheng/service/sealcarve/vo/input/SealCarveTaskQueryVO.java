package com.lvzheng.service.sealcarve.vo.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class SealCarveTaskQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;


    //刻章状态
    private int status = -1;
    
    //提交日期:开始
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    
    //提交日期:结束
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    //客户公司名（模糊搜索）
    @Pattern(regexp = "[0-9a-z\\s\\*\\u4e00-\\u9fa5]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "公司名非法")
    private String enterpriseName;
    
    //经办人（模糊搜索）
    @Pattern(regexp = "[0-9a-z\\s\\*\\u4e00-\\u9fa5]*", flags = Pattern.Flag.CASE_INSENSITIVE, message = "经办人非法")
    private String agentName;
    
    //页码
    private int pageIndex = 1;
    
    //每页显示记录数
    private int pageSize = 30;


    /**
     * 安全获取公司名
     * @return
     */
    public String getEnterpriseName() {
        return StringEscapeUtils.escapeHtml4(this.enterpriseName);
    }

    /**
     * 安全获取代理人
     * @return
     */
    public String getAgentName() {
        return StringEscapeUtils.escapeHtml4(this.agentName);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 获取当前页，如果小于等于0则返回1
     * @return
     */
    public int getPageIndex() {
        if (pageIndex <= 0) {
            return 1;
        }
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
