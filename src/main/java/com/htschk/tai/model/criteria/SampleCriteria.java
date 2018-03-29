package com.htschk.tai.model.criteria;

/**
 * Created by yuqikai on 2017/6/19.
 */
public class SampleCriteria extends CommonSearchCriteria{

    private Long arId;
    private Long piId;
    private String arIds;

    private Long supplierEntId;


    public SampleCriteria() {
    }

    public Long getArId() {
        return arId;
    }

    public void setArId(Long arId) {
        this.arId = arId;
    }

    public Long getPiId() {
        return piId;
    }

    public void setPiId(Long piId) {
        this.piId = piId;
    }

    public String getArIds() {
        return arIds;
    }

    public void setArIds(String arIds) {
        this.arIds = arIds;
    }

    public Long getSupplierEntId() {
        return supplierEntId;
    }

    public void setSupplierEntId(Long supplierEntId) {
        this.supplierEntId = supplierEntId;
    }
}
