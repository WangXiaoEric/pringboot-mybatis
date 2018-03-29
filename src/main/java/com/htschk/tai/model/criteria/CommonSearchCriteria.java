package com.htschk.tai.model.criteria;

import com.htschk.tai.model.CommonConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Created by yuqikai on 2017/6/26.
 */
public class CommonSearchCriteria {
    private String provinceId;
    private String cityId;
    private String districtId;

    private Integer pageNumber;
    private Integer pageSize;
    private Integer offset;
    private Integer limit;
    private Long totalCountForPassValue = 0L;//用于传递查询总数量

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        if (StringUtils.isNotEmpty(provinceId)) {
            this.provinceId = provinceId;
        }
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        if (StringUtils.isNotEmpty(cityId)) {
            this.cityId = cityId;
        }
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        if (StringUtils.isNotEmpty(districtId)) {
            this.districtId = districtId;
        }
    }

    public Long getTotalCountForPassValue() {
        return totalCountForPassValue;
    }

    public void setTotalCountForPassValue(Long totalCountForPassValue) {
        this.totalCountForPassValue = totalCountForPassValue;
    }

    public void setPage(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageNumber == 0) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
        if ((!Objects.equals(pageSize, CommonConstant.HARDCODE_NUMBER_FOR_ALL_DATA_IN_ONE_PAGE)) &&
                (pageSize == null || (pageSize < 1 || pageSize > 50))) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
        this.offset = (getPageNumber() - 1) * getPageSize();
        this.limit = getPageSize();
    }

    protected void processAreaCondition() {//如果以区搜索, 忽略城市和省份, 如果以城市搜索, 忽略省份
        if (StringUtils.isNotEmpty(this.getDistrictId())) {
            this.cityId = null;
            this.provinceId = null;
        }
        if (StringUtils.isNotEmpty(this.getCityId())) {
            this.provinceId = null;
        }
    }

    protected String filterEmptyString(String obj) {
        if (StringUtils.isEmpty(obj)) {
            return null;
        } else {
            return obj;
        }
    }

}
