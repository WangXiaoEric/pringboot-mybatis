package com.htschk.tai.model.entity.asset;

import com.htschk.tai.model.enumerate.BizOwnTypeEnum;
import com.htschk.tai.model.enumerate.BizUserMappingTypeEnum;

import java.util.Date;

public class AssetUserMappingEntity {
    private String assetId;

    private String userId;

    private BizUserMappingTypeEnum type;

    private String createUser;

    private Date createTime;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BizUserMappingTypeEnum getType() {
        return type;
    }

    public void setType(BizUserMappingTypeEnum type) {
        this.type = type;
    }

    public void setTypeCode(Integer typeCode) {
        this.type = BizUserMappingTypeEnum.fromCode(typeCode);
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
}
