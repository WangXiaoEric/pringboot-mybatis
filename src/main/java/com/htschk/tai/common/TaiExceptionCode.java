package com.htschk.tai.common;

/**
 * Created by yuqikai on 2017/6/19.
 */
public enum TaiExceptionCode {
    //系统级别
    UNEXPECTED("WARN_SYS0001", "-1", true),
    UNSUPPORTED_TYPE("UNSUPPORTED_TYPE", "-1", true),
    OTHER_ERROR("WARN_SYS0002", "-1", true),;


    private String code;
    private String responseCode;
    private boolean needStackPrint;

    TaiExceptionCode(String code, String responseCode, boolean needStackPrint) {
        this.code = code;
        this.responseCode = responseCode;
        this.needStackPrint = needStackPrint;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean isNeedStackPrint() {
        return needStackPrint;
    }

}
