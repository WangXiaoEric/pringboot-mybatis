package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

/**

 */
public enum AseetBizOwnTypeEnum {
    PRIMARY_OWNER(1, "PRIMARY_OWNER"),
    STAKEHOLDER(2, "STAKEHOLDER"),

    ;

    private Integer code;
    private String display;

    AseetBizOwnTypeEnum(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<Integer,AseetBizOwnTypeEnum> operModeMap = new HashMap<>();

    static{
        for(AseetBizOwnTypeEnum value : values()){
            operModeMap.put(value.getCode(),value);
        }
    }

    public static AseetBizOwnTypeEnum fromCode(Integer code){
        AseetBizOwnTypeEnum type = operModeMap.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 AseetBizOwnTypeEnum:"+code);
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
