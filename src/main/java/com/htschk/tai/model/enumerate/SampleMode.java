package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * todo : 基础数据可以从字典表中获取, 也可以从此 enum 中获取
 *
 *
 * Created by yuqikai on 2017/6/21.
 */
public enum SampleMode {
    NONE(0, "无"),
    PRODUCE(1, "生产型"),
    TRADE(2, "贸易型"),
    SERVICE(3, "服务型"),
    ;

    private Integer code;
    private String display;

    SampleMode(Integer code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<Integer,SampleMode> operModeMap = new HashMap<>();

    static{
        for(SampleMode value : values()){
            operModeMap.put(value.getCode(),value);
        }
    }

    public static SampleMode fromCode(Integer code){
        SampleMode type = operModeMap.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 SampleMode:"+code);
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
