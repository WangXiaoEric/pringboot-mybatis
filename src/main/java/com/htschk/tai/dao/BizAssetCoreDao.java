package com.htschk.tai.dao;

import com.htschk.tai.model.entity.asset.AssetCoreEntity;
import com.htschk.tai.model.entity.asset.AssetSoftwareEntity;
import com.htschk.tai.model.entity.common.Sequence;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yuqikai on 2017/6/19.
 */
@Component
public class BizAssetCoreDao extends AbstractDaoImpl{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    //
    private static final String INSERT_NEW_ASSET_CORE = "c.h.tai.dao.biz.asset.insertNewAssetCore";
    private static final String UPDATE_ASSET_CORE = "c.h.tai.dao.biz.asset.updateAssetCore";
    private static final String UPDATE_ASSET_CORE_AS_DELETED = "c.h.tai.dao.biz.asset.updateAssetCoreAsDeleted";
    private static final String SELECT_ASSET_CORE_DETAIL_BY_ID = "c.h.tai.dao.biz.asset.selectAssetCoreDetailById";

    public int insertNewAssetCore(AssetCoreEntity asset) {
        return sqlSessionTemplate.insert(INSERT_NEW_ASSET_CORE, asset);
    }
    public int updateAssetCore(AssetCoreEntity asset) {
        return sqlSessionTemplate.update(UPDATE_ASSET_CORE, asset);
    }
    public int deleteAssetCore(String assetId) {
        return sqlSessionTemplate.delete(UPDATE_ASSET_CORE_AS_DELETED, assetId);
    }
    public AssetCoreEntity selectAssetCoreById(String assetId) {
        return sqlSessionTemplate.selectOne(SELECT_ASSET_CORE_DETAIL_BY_ID, assetId);
    }

    private static final String INSERT_NEW_ASSET_SOFTWARE = "c.h.tai.dao.biz.asset.insertNewAssetSoftware";
    private static final String UPDATE_ASSET_SOFTWARE = "c.h.tai.dao.biz.asset.updateAssetSoftware";
    private static final String SELECT_ASSET_DETAIL_BY_ID_SOFTWARE = "c.h.tai.dao.biz.asset.selectAssetSoftwareDetailById";


    public int insertNewAssetSoftware(AssetSoftwareEntity asset) {
        return sqlSessionTemplate.insert(INSERT_NEW_ASSET_SOFTWARE, asset);
    }
    public int updateAssetSoftware(AssetSoftwareEntity asset) {
        return sqlSessionTemplate.update(UPDATE_ASSET_SOFTWARE, asset);
    }
    public AssetSoftwareEntity selectAssetSoftwareById(String assetId) {
        return sqlSessionTemplate.selectOne(SELECT_ASSET_DETAIL_BY_ID_SOFTWARE, assetId);
    }


    private static final String INSERT_NEW_ASSET_HARDWARE = "c.h.tai.dao.biz.asset.insertNewAssetCore";
    private static final String UPDATE_ASSET_HARDWARE = "c.h.tai.dao.biz.asset.insertNewAssetCore";
    private static final String SELECT_ASSET_DETAIL_BY_ID_HARDWARE = "c.h.tai.dao.biz.asset.insertNewAssetCore";


    private static final String SELECT_SOFTWARE_ASSET_LIST = "c.h.tai.dao.biz.asset.insertNewAssetCore";
    private static final String SELECT_HARDWARE_ASSET_LIST = "c.h.tai.dao.biz.asset.insertNewAssetCore";
    private static final String SELECT_SERVICE_ASSET_LIST = "c.h.tai.dao.biz.asset.insertNewAssetCore";





}
