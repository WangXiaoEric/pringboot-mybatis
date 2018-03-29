package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum AssetClassificationEnum {
    SOFTWARE("SF", "SOFTWARE"),
    HARDWARE("HD", "HARDWARE"),
    SERVICE("SV", "SERVICE"),

    ;

    private String code;
    private String display;

    AssetClassificationEnum(String code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<String,AssetClassificationEnum> map = new HashMap<>();

    static{
        for(AssetClassificationEnum value : values()){
            map.put(value.getCode(),value);
        }
    }

    public static AssetClassificationEnum fromCode(String code){
        AssetClassificationEnum type = map.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 AssetClassificationEnum:"+code);
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
