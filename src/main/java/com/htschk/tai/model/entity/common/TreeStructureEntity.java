package com.htschk.tai.model.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuqikai on 2017/7/5.
 */
abstract public class TreeStructureEntity {
    private Long id;
    private Long parentId;
    private Integer level;
    private List<TreeStructureEntity> children;

    public abstract void setCustomizedIds();

    public TreeStructureEntity() {
    }

    public TreeStructureEntity(Long id, Long parentId, Integer level) {
        this.id = id;
        this.parentId = parentId;
        this.level = level;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    @JsonIgnore
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TreeStructureEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeStructureEntity> children) {
        this.children = children;
    }

    public void addChildEntity(TreeStructureEntity childEntity) {
        if (getChildren() == null) {
            setChildren(new ArrayList<>());
        }
        getChildren().add(childEntity);
    }


}
