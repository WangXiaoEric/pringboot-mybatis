package com.htschk.tai.model.enumerate;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum HardwareSubClassificationEnum {
    DESKTOP("DESKTOP", "desktop"),
    LAPTOP("LAPTOP", "LAPTOP"),
    SERVER("SERVER", "SERVER"),
    VM_SERVER("VM", "VM_SERVER"),
    PRINTER("PRINTER", "PRINTER"),
    SWITCH_DEVICE("SWITCH", "SWITCH"),
    PHONE_DEVICE("PHONE", "PHONE"),

    ;

    private String code;
    private String display;

    HardwareSubClassificationEnum(String code, String display) {
        this.code = code;
        this.display = display;
    }

    private static final Map<String,HardwareSubClassificationEnum> map = new HashMap<>();

    static{
        for(HardwareSubClassificationEnum value : values()){
            map.put(value.getCode(),value);
        }
    }

    public static HardwareSubClassificationEnum fromCode(String code){
        HardwareSubClassificationEnum type = map.get(code);
        if(type == null){
            throw new TaiException(TaiExceptionCode.UNSUPPORTED_TYPE, "不支持的 HardwareSubClassificationEnum:"+code);
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
