package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum RiskRankingEnum {
    LOW("L", "LOW"),
    MEDIUM("M", "MEDIUM"),
    HIGH("H", "HIGH"),

    ;

    private String code;
    private String display;

    RiskRankingEnum(String code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<String,RiskRankingEnum> map = new HashMap<>();

    static{
        for(RiskRankingEnum value : values()){
            map.put(value.getCode(),value);
        }
    }

    public static RiskRankingEnum fromCode(String code){
        RiskRankingEnum type = map.get(code);
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
