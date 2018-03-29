package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum BizOwnTypeEnum {
    OWNER(1, "Ownership"),
    STAKEHOLDER(2, "Stakeholder"),

    ;

    private Integer code;
    private String display;

    BizOwnTypeEnum(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<Integer,BizOwnTypeEnum> operModeMap = new HashMap<>();

    static{
        for(BizOwnTypeEnum value : values()){
            operModeMap.put(value.getCode(),value);
        }
    }

    public static BizOwnTypeEnum fromCode(Integer code){
        BizOwnTypeEnum type = operModeMap.get(code);
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
