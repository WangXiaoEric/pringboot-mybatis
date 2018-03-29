package com.htschk.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htschk.tai.dao.BizAssetCoreDao;
import com.htschk.tai.dao.BizAssetGeneralDao;
import com.htschk.tai.model.request.AssetSoftwareEntityRequest;

@Service
public class AssetSoftwareServiceImpl{

    @Autowired
    private BizAssetCoreDao bizAssetCoreDao;
    
    @Autowired
    private BizAssetGeneralDao bizAssetGeneralDao;
    
    public void getAssetSoftware() {
    	
    }
    
	public void addAssetSoftware(AssetSoftwareEntityRequest assetSoftwareEntityRequest) {
		bizAssetCoreDao.insertNewAssetCore(assetSoftwareEntityRequest.getAssetSoftwareEntity());
		bizAssetCoreDao.insertNewAssetSoftware(assetSoftwareEntityRequest.getAssetSoftwareEntity());
	}
	
	public void updateAssetSoftware() {
		
	}
	
	public void deleteAssetSoftware() {
		
	}

}
