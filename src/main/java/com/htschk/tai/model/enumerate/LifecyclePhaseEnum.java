package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum LifecyclePhaseEnum {
    PREPARATION(0, "PREPARATION"),
    PRODUCTION(1, "PRODUCTION"),
    DECOMMISSION(-1, "DECOMMISSION"),

    ;

    private Integer code;
    private String display;

    LifecyclePhaseEnum(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<Integer,LifecyclePhaseEnum> operModeMap = new HashMap<>();

    static{
        for(LifecyclePhaseEnum value : values()){
            operModeMap.put(value.getCode(),value);
        }
    }

    public static LifecyclePhaseEnum fromCode(Integer code){
        LifecyclePhaseEnum type = operModeMap.get(code);
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
