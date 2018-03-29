package com.htschk.tai.model.request;

/**
 * Created by yuqikai on 2017/6/21.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRequest {
    private Long startMill;

    private Boolean logTrace = true;//是否记录该请求操作记录, 如果是内部跳转, 则设置为 false , 不需要记录

    private Integer isShowSearchPanel;
    private Integer isEditable;//用于页面传递是否可以编辑
    private String bizModule;//用于判断 采购平台中 归属那个中心(销售中心 or 采购中心)

    private Long dummyUserId; //for test usage

    private String dynamicPassword;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItemCount;

    public Integer getIsShowSearchPanel() {
        return isShowSearchPanel;
    }

    public void setIsShowSearchPanel(Integer isShowSearchPanel) {
        this.isShowSearchPanel = isShowSearchPanel;
    }

    public Boolean getLogTrace() {
        return logTrace;
    }

    public void setLogTrace(Boolean logTrace) {
        this.logTrace = logTrace;
    }

    public Integer getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Integer isEditable) {
        this.isEditable = isEditable;
    }

    public Long getDummyUserId() {
        return dummyUserId;
    }

    public void setDummyUserId(Long dummyUserId) {
        this.dummyUserId = dummyUserId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getBizModule() {
        return bizModule;
    }

    public void setBizModule(String bizModule) {
        this.bizModule = bizModule;
    }

    public Integer filterZeroValue(Integer i) {
        return i == null ? null : i == 0 ? null : i;
    }

    public Long getStartMill() {
        return startMill;
    }

    public void setStartMill(Long startMill) {
        this.startMill = startMill;
    }

    public Long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(Long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getDynamicPassword() {
        return dynamicPassword;
    }

    public void setDynamicPassword(String dynamicPassword) {
        this.dynamicPassword = dynamicPassword;
    }
}
