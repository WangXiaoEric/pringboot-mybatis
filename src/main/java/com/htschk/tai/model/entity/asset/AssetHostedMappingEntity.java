package com.htschk.tai.model.entity.asset;

import com.htschk.tai.model.enumerate.BizUserMappingTypeEnum;

import java.util.Date;

public class AssetHostedMappingEntity {
    private Integer id;

    private String sfAssetId;

    private String hdAssetId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSfAssetId() {
        return sfAssetId;
    }

    public void setSfAssetId(String sfAssetId) {
        this.sfAssetId = sfAssetId;
    }

    public String getHdAssetId() {
        return hdAssetId;
    }

    public void setHdAssetId(String hdAssetId) {
        this.hdAssetId = hdAssetId;
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
