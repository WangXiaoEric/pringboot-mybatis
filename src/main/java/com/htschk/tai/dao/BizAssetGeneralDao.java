package com.htschk.tai.dao;

import com.htschk.tai.model.entity.asset.AssetHostedMappingEntity;
import com.htschk.tai.model.entity.asset.AssetOrgMappingEntity;
import com.htschk.tai.model.entity.asset.AssetSoftwareEntity;
import com.htschk.tai.model.entity.asset.AssetUserMappingEntity;
import com.htschk.tai.model.entity.common.Sequence;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yuqikai on 2017/6/19.
 */
@Component
public class BizAssetGeneralDao extends AbstractDaoImpl{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

//    org mapping
    private static final String INSERT_ASSET_ORG_MAPPING = "c.h.tai.dao.biz.asset.insertNewAssetOrgMapping";
    private static final String DELETE_ASSET_ORG_MAPPING = "c.h.tai.dao.biz.asset.deleteOrgMappingByCriteria";
    private static final String SELECT_ASSET_ORG_MAPPING_BY_CRITERIA = "c.h.tai.dao.biz.asset.selectAssetOrgMappingByCriteria";


    public int insertNewAssetOrgMapping(AssetOrgMappingEntity  asset) {
        return sqlSessionTemplate.insert(INSERT_ASSET_ORG_MAPPING, asset);
    }
    public int deleteOrgMappingByCriteria(AssetOrgMappingEntity asset) {
        return sqlSessionTemplate.update(DELETE_ASSET_ORG_MAPPING, asset);
    }
    public List<AssetOrgMappingEntity> selectAssetOrgMappingByCriteria(AssetOrgMappingEntity  criteria) {
        return sqlSessionTemplate.selectList(SELECT_ASSET_ORG_MAPPING_BY_CRITERIA, criteria);
    }



    private static final String INSERT_ASSET_USER_MAPPING = "c.h.tai.dao.biz.asset.insertNewAssetUserMapping";
    private static final String DELETE_ASSET_USER_MAPPING = "c.h.tai.dao.biz.asset.deleteAssetUserMappingByCriteria";
    private static final String SELECT_ASSET_USER_MAPPING_BY_CRITERIA = "c.h.tai.dao.biz.asset.selectAssetUserMappingListByCriteria";


    public int insertNewAssetUserMapping(AssetUserMappingEntity userMapping) {
        return sqlSessionTemplate.insert(INSERT_ASSET_USER_MAPPING, userMapping);
    }
    public int deleteAssetUserMappingByCriteria(AssetUserMappingEntity userMapping) {
        return sqlSessionTemplate.update(DELETE_ASSET_USER_MAPPING, userMapping);
    }
    public List<AssetUserMappingEntity> selectAssetUserMappingListByCriteria(AssetUserMappingEntity  criteria) {
        return sqlSessionTemplate.selectList(SELECT_ASSET_USER_MAPPING_BY_CRITERIA, criteria);
    }

    private static final String INSERT_SF_HD_ASSET_MAPPING = "c.h.tai.dao.biz.asset.insertSfHdAssetMapping";
    private static final String DELETE_SF_HD_ASSET_MAPPING = "c.h.tai.dao.biz.asset.deleteSfHdAssetByCriteria";
    private static final String SELECT_SF_HD_ASSET_MAPPING_BY_CRITERIA = "c.h.tai.dao.biz.asset.selectSfHdAssetListByCriteria";

    public int insertSfHdAssetMapping(AssetHostedMappingEntity userMapping) {
        return sqlSessionTemplate.insert(INSERT_SF_HD_ASSET_MAPPING, userMapping);
    }
    public int deleteSfHdAssetByCriteria(AssetHostedMappingEntity userMapping) {
        return sqlSessionTemplate.update(DELETE_SF_HD_ASSET_MAPPING, userMapping);
    }
    public List<AssetHostedMappingEntity> selectSfHdAssetListByCriteria(AssetHostedMappingEntity criteria) {
        return sqlSessionTemplate.selectList(SELECT_SF_HD_ASSET_MAPPING_BY_CRITERIA, criteria);
    }

}
