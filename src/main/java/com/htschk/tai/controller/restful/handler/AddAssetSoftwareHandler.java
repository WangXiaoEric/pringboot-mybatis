package com.htschk.tai.controller.restful.handler;

import com.htschk.tai.controller.restful.AbstractTaiRequestHandler;
import com.htschk.tai.controller.restful.TaiRestfulRequestType;
import com.htschk.tai.model.entity.asset.AssetSoftwareEntity;
import com.htschk.tai.model.request.AssetSoftwareEntityRequest;
import com.htschk.tai.service.AssetSoftwareServiceImpl;
import com.htschk.tai.util.IdGenerationUtil;
import com.htschk.tai.util.LogManager;
import com.htschk.tai.util.PpBeanUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddAssetSoftwareHandler extends AbstractTaiRequestHandler {

    @Autowired
    private AssetSoftwareServiceImpl assetSoftwareServiceImpl;
    
    @Autowired
    private IdGenerationUtil idGenerationUtil;

    @Override
    protected TaiRestfulRequestType getRequestType() {
        return TaiRestfulRequestType.addAssetSoftware;
    }

    @Override
    public Object getResult(Map<String, Object> map) {
    	try {
    		LogManager.infoSystemLog("SampleRequestHandler.gerResult ...");
            
            AssetSoftwareEntityRequest assetSoftwareEntityRequest = new AssetSoftwareEntityRequest();
            this.populateParams(map, assetSoftwareEntityRequest);
            
            assetSoftwareServiceImpl.addAssetSoftware(assetSoftwareEntityRequest);
            Long id = idGenerationUtil.getSequenceByName("General");

            return "Success";
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return "";
    }
    
    @Override
    public String validate(Map<String, Object> map){
        return "";
    }
    
    private void populateParams(Map<String, Object> map, AssetSoftwareEntityRequest assetSoftwareEntityRequest) {
    	AssetSoftwareEntity assetSoftwareEntity = new AssetSoftwareEntity();
    	assetSoftwareEntity.setAssetId((String) map.get("assetId"));
    	assetSoftwareEntityRequest.setAssetSoftwareEntity(assetSoftwareEntity);
    	//PpBeanUtil.populateProperties(assetSoftwareEntityRequest, map);
    }
    
    
}
