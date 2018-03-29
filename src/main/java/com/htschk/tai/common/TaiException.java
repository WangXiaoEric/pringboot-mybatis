package com.htschk.tai.common;

/**
 * Created by yuqikai on 2017/6/19.
 */
public class TaiException extends RuntimeException {
    private TaiExceptionCode errorCode;
    private String errorInfo;
    private Exception exception;

    public TaiException(String errorInfo){
        super(errorInfo);
        this.errorCode = TaiExceptionCode.OTHER_ERROR;
        this.errorInfo = errorInfo.replace("\n","");
    }

    public TaiException(TaiExceptionCode errorCode, String errorInfo){
        super(errorInfo);
        this.errorCode = errorCode;
        this.errorInfo = errorInfo.replace("\n","");
    }

    public TaiException(TaiExceptionCode errorCode, String errorInfo,
                        Exception sourceException){
        super(sourceException);
        this.exception = sourceException;
        this.errorCode = errorCode;
        this.errorInfo = errorInfo.replace("\n","");
    }

    public String getErrorInfo(){
        return errorInfo;
    }

    public TaiExceptionCode getErrorCode(){
        return errorCode;
    }

    public Exception getSourceException() {
        return exception;
    }
}
