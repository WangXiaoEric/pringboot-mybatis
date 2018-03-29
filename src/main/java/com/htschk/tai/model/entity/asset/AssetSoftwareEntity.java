package com.htschk.tai.model.entity.asset;

import java.util.Date;

public class AssetSoftwareEntity extends AssetCoreEntity{
    private String resourceLocation;

    private String sdlcPhase;

    private Integer internalUserLimit;

    private String provideDataToExt;

    private String useMarketData;

    private String techRecoveryTime;

    private String applicationRto;

    private String drMode;

    public String getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public String getSdlcPhase() {
        return sdlcPhase;
    }

    public void setSdlcPhase(String sdlcPhase) {
        this.sdlcPhase = sdlcPhase;
    }

    public Integer getInternalUserLimit() {
        return internalUserLimit;
    }

    public void setInternalUserLimit(Integer internalUserLimit) {
        this.internalUserLimit = internalUserLimit;
    }

    public String getProvideDataToExt() {
        return provideDataToExt;
    }

    public void setProvideDataToExt(String provideDataToExt) {
        this.provideDataToExt = provideDataToExt;
    }

    public String getUseMarketData() {
        return useMarketData;
    }

    public void setUseMarketData(String useMarketData) {
        this.useMarketData = useMarketData;
    }

    public String getTechRecoveryTime() {
        return techRecoveryTime;
    }

    public void setTechRecoveryTime(String techRecoveryTime) {
        this.techRecoveryTime = techRecoveryTime;
    }

    public String getApplicationRto() {
        return applicationRto;
    }

    public void setApplicationRto(String applicationRto) {
        this.applicationRto = applicationRto;
    }

    public String getDrMode() {
        return drMode;
    }

    public void setDrMode(String drMode) {
        this.drMode = drMode;
    }

}