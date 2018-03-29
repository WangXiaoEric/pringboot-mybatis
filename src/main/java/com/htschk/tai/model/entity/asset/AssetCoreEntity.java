package com.htschk.tai.model.entity.asset;

import com.htschk.tai.model.enumerate.AssetClassificationEnum;
import com.htschk.tai.model.enumerate.HardwareSubClassificationEnum;
import com.htschk.tai.model.enumerate.SoftwareSubClassificationEnum;

import java.util.Date;

public class AssetCoreEntity {
    private String assetId;

    private String parentAssetId;

    private AssetClassificationEnum assetCls;

    private String assetSubCls;

    private String sourceType;

    private String platformType;

    private String vendorName;

    private String vendorContactInfo;

    private String assetName;

    private String assetUniqueName;

    private String homePage;

    private String description;

    private String lifecyclePhase;

    private String procurementDate;

    private String productionDate;

    private Double plannedCost;

    private Double actualCost;

    private Double quantity;

    private Double pricePerUnit;

    private String currency;

    private String usefulLife;

    private String usefulLifeStartDate;

    private String usefulLifeEndDate;

    private String techOwnOrg;

    private String techMgmtLocation;

    private String primTechOwner;

    private String devMailGroup;

    private String bizOwnOrg;

    private String bizMgmtLocation;

    private String primBizOwner;

    private String primSupport;

    private String primSupportExt;

    private String secSupport;

    private String secSupportExt;

    private String qaSupport;

    private String qaSupportExt;

    private String bizProcessPrim;

    private Double bizProcessPrimRatio;

    private String bizProcessSecText;

    private String infoSensitivityCls;

    private String criticalInfraAsset;

    private String riskRanking;

    private Integer isEnable;

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

    public String getParentAssetId() {
        return parentAssetId;
    }

    public void setParentAssetId(String parentAssetId) {
        this.parentAssetId = parentAssetId;
    }

    public AssetClassificationEnum getAssetCls() {
        return assetCls;
    }

    public void setAssetCls(AssetClassificationEnum assetCls) {
        this.assetCls = assetCls;
    }

    public void setAssetClsCode(String assetClsCode) {
        this.assetCls = AssetClassificationEnum.fromCode(assetClsCode);
    }

    public String getAssetSubCls() {
        return assetSubCls;
    }

    public void setAssetSubCls(String assetSubCls) {
        this.assetSubCls = assetSubCls;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactInfo() {
        return vendorContactInfo;
    }

    public void setVendorContactInfo(String vendorContactInfo) {
        this.vendorContactInfo = vendorContactInfo;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetUniqueName() {
        return assetUniqueName;
    }

    public void setAssetUniqueName(String assetUniqueName) {
        this.assetUniqueName = assetUniqueName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifecyclePhase() {
        return lifecyclePhase;
    }

    public void setLifecyclePhase(String lifecyclePhase) {
        this.lifecyclePhase = lifecyclePhase;
    }

    public String getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(String procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public Double getPlannedCost() {
        return plannedCost;
    }

    public void setPlannedCost(Double plannedCost) {
        this.plannedCost = plannedCost;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getUsefulLifeStartDate() {
        return usefulLifeStartDate;
    }

    public void setUsefulLifeStartDate(String usefulLifeStartDate) {
        this.usefulLifeStartDate = usefulLifeStartDate;
    }

    public String getUsefulLifeEndDate() {
        return usefulLifeEndDate;
    }

    public void setUsefulLifeEndDate(String usefulLifeEndDate) {
        this.usefulLifeEndDate = usefulLifeEndDate;
    }

    public String getTechOwnOrg() {
        return techOwnOrg;
    }

    public void setTechOwnOrg(String techOwnOrg) {
        this.techOwnOrg = techOwnOrg;
    }

    public String getTechMgmtLocation() {
        return techMgmtLocation;
    }

    public void setTechMgmtLocation(String techMgmtLocation) {
        this.techMgmtLocation = techMgmtLocation;
    }

    public String getPrimTechOwner() {
        return primTechOwner;
    }

    public void setPrimTechOwner(String primTechOwner) {
        this.primTechOwner = primTechOwner;
    }

    public String getDevMailGroup() {
        return devMailGroup;
    }

    public void setDevMailGroup(String devMailGroup) {
        this.devMailGroup = devMailGroup;
    }

    public String getBizOwnOrg() {
        return bizOwnOrg;
    }

    public void setBizOwnOrg(String bizOwnOrg) {
        this.bizOwnOrg = bizOwnOrg;
    }

    public String getBizMgmtLocation() {
        return bizMgmtLocation;
    }

    public void setBizMgmtLocation(String bizMgmtLocation) {
        this.bizMgmtLocation = bizMgmtLocation;
    }

    public String getPrimBizOwner() {
        return primBizOwner;
    }

    public void setPrimBizOwner(String primBizOwner) {
        this.primBizOwner = primBizOwner;
    }

    public String getPrimSupport() {
        return primSupport;
    }

    public void setPrimSupport(String primSupport) {
        this.primSupport = primSupport;
    }

    public String getPrimSupportExt() {
        return primSupportExt;
    }

    public void setPrimSupportExt(String primSupportExt) {
        this.primSupportExt = primSupportExt;
    }

    public String getSecSupport() {
        return secSupport;
    }

    public void setSecSupport(String secSupport) {
        this.secSupport = secSupport;
    }

    public String getSecSupportExt() {
        return secSupportExt;
    }

    public void setSecSupportExt(String secSupportExt) {
        this.secSupportExt = secSupportExt;
    }

    public String getQaSupport() {
        return qaSupport;
    }

    public void setQaSupport(String qaSupport) {
        this.qaSupport = qaSupport;
    }

    public String getQaSupportExt() {
        return qaSupportExt;
    }

    public void setQaSupportExt(String qaSupportExt) {
        this.qaSupportExt = qaSupportExt;
    }

    public String getBizProcessPrim() {
        return bizProcessPrim;
    }

    public void setBizProcessPrim(String bizProcessPrim) {
        this.bizProcessPrim = bizProcessPrim;
    }

    public Double getBizProcessPrimRatio() {
        return bizProcessPrimRatio;
    }

    public void setBizProcessPrimRatio(Double bizProcessPrimRatio) {
        this.bizProcessPrimRatio = bizProcessPrimRatio;
    }

    public String getBizProcessSecText() {
        return bizProcessSecText;
    }

    public void setBizProcessSecText(String bizProcessSecText) {
        this.bizProcessSecText = bizProcessSecText;
    }

    public String getInfoSensitivityCls() {
        return infoSensitivityCls;
    }

    public void setInfoSensitivityCls(String infoSensitivityCls) {
        this.infoSensitivityCls = infoSensitivityCls;
    }

    public String getCriticalInfraAsset() {
        return criticalInfraAsset;
    }

    public void setCriticalInfraAsset(String criticalInfraAsset) {
        this.criticalInfraAsset = criticalInfraAsset;
    }

    public String getRiskRanking() {
        return riskRanking;
    }

    public void setRiskRanking(String riskRanking) {
        this.riskRanking = riskRanking;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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