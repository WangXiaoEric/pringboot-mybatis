package com.htschk.tai.model.entity.asset;

import com.htschk.tai.model.enumerate.BizOwnTypeEnum;

import java.util.Date;

public class AssetOrgMappingEntity {
    private String assetId;

    private String buId;

    private BizOwnTypeEnum bizOwnType;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public BizOwnTypeEnum getBizOwnType() {
        return bizOwnType;
    }

    public void setBizOwnType(BizOwnTypeEnum bizOwnType) {
        this.bizOwnType = bizOwnType;
    }

    public void setBizOwnTypeCode(Integer bizOwnTypeCode) {
        this.bizOwnType = BizOwnTypeEnum.fromCode(bizOwnTypeCode);
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
