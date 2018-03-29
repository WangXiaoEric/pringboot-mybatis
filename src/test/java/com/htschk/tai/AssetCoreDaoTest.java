package com.htschk.tai;


import com.htschk.tai.dao.BizAssetCoreDao;
import com.htschk.tai.dao.BizAssetGeneralDao;
import com.htschk.tai.model.entity.asset.*;
import com.htschk.tai.model.enumerate.AssetClassificationEnum;
import com.htschk.tai.model.enumerate.BizOwnTypeEnum;
import com.htschk.tai.model.enumerate.BizUserMappingTypeEnum;
import com.htschk.tai.model.enumerate.SoftwareSubClassificationEnum;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
    todo: to be removed later ...
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Ignore
public class AssetCoreDaoTest extends PpCommonWebIntegrationTest {

    private boolean isFirstInitialized = false;

    private BizAssetCoreDao assetCoreDao;
    private BizAssetGeneralDao assetGeneralDao;


    private String token;


    @BeforeClass
    public static void init() {
        String env = "integration";
        System.setProperty("spring.config.name", "application,application-" + env);
    }

    @AfterClass
    public static void uninit() {
        ServletInitializerForTest.stop();
        applicationContext = null;
    }

    @Before
    public void setUp() {
        super.setUp();
        if (!isFirstInitialized) {
            assetCoreDao = applicationContext.getBean(BizAssetCoreDao.class);
            assetGeneralDao = applicationContext.getBean(BizAssetGeneralDao.class);
            isFirstInitialized = true;
        }
        httpSender.setReadTimeOut(30*1000);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
//    @Ignore
    public void testAssetCoreDaoExecution() {
        dbTestHelper.executeSql("delete from t_biz_asset_core where asset_id = 'TEST001'");

        AssetCoreEntity entity = generateAssetCoreEntity();
        assetCoreDao.insertNewAssetCore(entity);

        entity.setAssetName("Crm New Software...");
        assetCoreDao.updateAssetCore(entity);

        AssetCoreEntity newEntity = assetCoreDao.selectAssetCoreById(entity.getAssetId());

        System.out.println(entity);
        System.out.println(newEntity);

        assetCoreDao.deleteAssetCore(entity.getAssetId());
        dbTestHelper.executeSql("delete from t_biz_asset_core where asset_id = 'TEST001'");
    }

    @Test
//    @Ignore
    public void testAssetSoftwareDaoExecution() {
        dbTestHelper.executeSql("delete from t_biz_asset_software where asset_id = 'TEST001'");

        AssetSoftwareEntity entity = new AssetSoftwareEntity();
        entity.setAssetId("TEST001");
        entity.setSdlcPhase("DEV");
        entity.setResourceLocation("C:\\abc\\test\\");
        assetCoreDao.insertNewAssetSoftware(entity);

        entity.setResourceLocation("C:\\abc\\test12345\\");
        assetCoreDao.updateAssetCore(entity);

        AssetSoftwareEntity newEntity = assetCoreDao.selectAssetSoftwareById(entity.getAssetId());

        System.out.println(entity);
        System.out.println(newEntity);

        dbTestHelper.executeSql("delete from t_biz_asset_software where asset_id = 'TEST001'");
    }

    @Test
//    @Ignore
    public void testAssetOrgMappingDaoExecution() {
        dbTestHelper.executeSql("delete from t_biz_asset_org_mapping");

        AssetOrgMappingEntity entity = new AssetOrgMappingEntity();
        entity.setAssetId("TEST001");
        entity.setBizOwnType(BizOwnTypeEnum.OWNER);
        entity.setBuId("IT");
        entity.setCreateUser("admin");
        assetGeneralDao.insertNewAssetOrgMapping(entity);

        AssetOrgMappingEntity criteria = new AssetOrgMappingEntity();
        criteria.setAssetId("TEST001");

        List<AssetOrgMappingEntity> list = assetGeneralDao.selectAssetOrgMappingByCriteria(criteria);
        Assert.assertEquals(1, list.size());

        assetGeneralDao.deleteOrgMappingByCriteria(criteria);

        dbTestHelper.executeSql("delete from t_biz_asset_org_mapping");
    }

    @Test
//    @Ignore
    public void testAssetUserMappingDaoExecution() {
        dbTestHelper.executeSql("delete from t_biz_asset_user_mapping");

        AssetUserMappingEntity entity = new AssetUserMappingEntity();
        entity.setAssetId("TEST001");
        entity.setType(BizUserMappingTypeEnum.TECH);
        entity.setUserId("zhangsan");
        entity.setCreateUser("admin");
        assetGeneralDao.insertNewAssetUserMapping(entity);

        AssetUserMappingEntity criteria = new AssetUserMappingEntity();
        criteria.setAssetId("TEST001");

        List<AssetUserMappingEntity> list = assetGeneralDao.selectAssetUserMappingListByCriteria(criteria);
        Assert.assertEquals(1, list.size());

        assetGeneralDao.deleteAssetUserMappingByCriteria(criteria);

        dbTestHelper.executeSql("delete from t_biz_asset_user_mapping");
    }

    @Test
//    @Ignore
    public void testSfHdAssetMappingDaoExecution() {
        dbTestHelper.executeSql("delete from t_biz_sf_hd_asset_mapping");

        AssetHostedMappingEntity entity = new AssetHostedMappingEntity();
        entity.setId(1);
        entity.setSfAssetId("SF0001");
        entity.setHdAssetId("HD0001");
        entity.setCreateUser("admin");
        assetGeneralDao.insertSfHdAssetMapping(entity);

        AssetHostedMappingEntity criteria = new AssetHostedMappingEntity();
        criteria.setSfAssetId("SF0001");

        List<AssetHostedMappingEntity> list = assetGeneralDao.selectSfHdAssetListByCriteria(criteria);
        Assert.assertEquals(1, list.size());

        assetGeneralDao.deleteSfHdAssetByCriteria(criteria);

        dbTestHelper.executeSql("delete from t_biz_sf_hd_asset_mapping");
    }


    private AssetCoreEntity generateAssetCoreEntity() {
        AssetCoreEntity entity  = new AssetCoreEntity();
        entity.setAssetId("TEST001");
        entity.setAssetCls(AssetClassificationEnum.SOFTWARE);
        entity.setAssetSubCls(SoftwareSubClassificationEnum.DESKTOP.getCode());
        entity.setAssetName("CRM Software");
        entity.setPlannedCost(1000000.0);
        entity.setActualCost(1000000.0);
        entity.setIsEnable(1);
        entity.setCreateUser("admin");

        return entity;
    }

}
