package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum SoftwareSubClassificationEnum {
    DESKTOP("DESK", "desktop software"),
    LICENSE("LCNS", "license"),
    SYSTEM("SYSTEM", "server system"),
    // etc.

    ;

    private String code;
    private String display;

    SoftwareSubClassificationEnum(String code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<String,SoftwareSubClassificationEnum> map = new HashMap<>();

    static{
        for(SoftwareSubClassificationEnum value : values()){
            map.put(value.getCode(),value);
        }
    }

    public static SoftwareSubClassificationEnum fromCode(String code){
        SoftwareSubClassificationEnum type = map.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 RiskRankingEnum:"+code);
        }
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }
}
