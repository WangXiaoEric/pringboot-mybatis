package com.htschk.tai.util;

import com.htschk.tai.model.entity.common.TreeStructureEntity;

import java.util.*;

/**
 * Created by yuqikai on 2017/7/5.
 */
public class TreeStructureUtil {
    public static List<TreeStructureEntity> process(List<TreeStructureEntity> list) {
        List<TreeStructureEntity> resultList = new ArrayList<>();

        Map<Long, TreeStructureEntity> erEntityIdMap = new HashMap<>();
        Map<Long, List<Long>> parentIdMap = new HashMap<>();

        list.forEach(it -> {
            it.setCustomizedIds();//将其他各自实体的 id/parentId/level 设置到父类的属性中来
            erEntityIdMap.put(it.getId(), it);
            List<Long> childIds = parentIdMap.get(it.getParentId());
            if (childIds == null) {
                childIds = new ArrayList<>();
                parentIdMap.put(it.getParentId(), childIds);
            }
            childIds.add(it.getId());
        });

        //只取level=1的数据作为返回的根节点属性
        list.forEach(it -> {
            if (it.getLevel() == 1) {
                processSingleNode(it, erEntityIdMap, parentIdMap);
                resultList.add(it);
            }
        });
        return resultList;
    }

    //对当前需要处理的节点进行递归处理
    private static void processSingleNode(TreeStructureEntity entity,
                                          Map<Long, TreeStructureEntity> erEntityIdMap, Map<Long, List<Long>> parentIdMap) {
        List<Long> childIds = parentIdMap.get(entity.getId());
        if (childIds == null || childIds.size() == 0) {
            return;
        }
        for (Long childId : childIds) {
            TreeStructureEntity childEntity = erEntityIdMap.get(childId);
            if (childEntity != null) {
                entity.addChildEntity(childEntity);
                processSingleNode(childEntity, erEntityIdMap, parentIdMap);
            }
        }
    }

    public static Map<Long, Set<Long>> parseChildrenNodesMap(TreeStructureEntity entity) {
        Map<Long, Set<Long>> resultMap = new HashMap<>();
        if (entity != null) {
            parseChildrenNodesForSingleNode(entity, resultMap);
        }
        return resultMap;
    }

    public static Map<Long, Set<Long>> parseChildrenNodesMap(List<TreeStructureEntity> list) {
        Map<Long, Set<Long>> resultMap = new HashMap<>();
        for (TreeStructureEntity treeStructureEntity : list) {
//            Set<Long> set = parseChildrenNodesForSingleNode(treeStructureEntity, resultMap);
//            Set<Long> newSet = new HashSet<>(set);
//            newSet.add(treeStructureEntity.getId());
//            resultMap.put(treeStructureEntity.getId(), newSet);
            parseChildrenNodesForSingleNode(treeStructureEntity, resultMap);
        }
        return resultMap;
    }

    private static Set<Long> parseChildrenNodesForSingleNode(
            TreeStructureEntity entity, Map<Long, Set<Long>> resultMap) {
        Set<Long> set = new HashSet<>();
        set.add(entity.getId());

        List<TreeStructureEntity> childrenNodes = entity.getChildren();
        if (childrenNodes != null && childrenNodes.size() > 0) {
            childrenNodes.forEach(it -> {
                Set childSet = parseChildrenNodesForSingleNode(it, resultMap);
                set.addAll(childSet);
            });
        }

        resultMap.put(entity.getId(), set);
        return set;
    }

}
