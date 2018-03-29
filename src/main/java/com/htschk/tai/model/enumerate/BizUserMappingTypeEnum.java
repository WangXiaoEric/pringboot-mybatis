package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum BizUserMappingTypeEnum {
    TECH(1, "TECH"),
    BIZ(2, "BIZ"),

    ;

    private Integer code;
    private String display;

    BizUserMappingTypeEnum(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<Integer,BizUserMappingTypeEnum> operModeMap = new HashMap<>();

    static{
        for(BizUserMappingTypeEnum value : values()){
            operModeMap.put(value.getCode(),value);
        }
    }

    public static BizUserMappingTypeEnum fromCode(Integer code){
        BizUserMappingTypeEnum type = operModeMap.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 LifecyclePhaseEnum:"+code);
        }
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }
}
