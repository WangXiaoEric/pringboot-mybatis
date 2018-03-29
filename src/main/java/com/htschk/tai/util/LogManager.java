package com.htschk.tai.util;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yuqikai on 2017/6/12.
 */
public class LogManager {
    private static final Logger infoLogger = LoggerFactory.getLogger(LogManager.class);
    private static final Logger performLogger = LoggerFactory.getLogger(log.PerformanceAppender.class);
    private static final Logger errorLogger = LoggerFactory.getLogger(log.ErrorAppender.class);
    private static final Logger detailLogger = LoggerFactory.getLogger(log.DetailAppender.class);

    public static void errorLog(TaiException exception) {
        String message = String.format("[errorCode:%s]:%s", exception.getErrorCode(), exception.getErrorInfo());
        if (exception.getErrorCode().isNeedStackPrint()) {
            if (exception.getErrorCode() == TaiExceptionCode.UNEXPECTED) {
                errorLogger.error(message, exception.getSourceException());
            } else {
                errorLogger.error(message, exception);
            }
        } else {
            errorLogger.error(message);
        }

    }

    public static void errorLog(String message, Exception exception) {
        errorLogger.error(message, exception);
    }

    public static void errorLog(Exception exception) {
        errorLogger.error("", exception);
    }

    public static void errorLog(String s) {
        errorLogger.error(s);
    }

    public static void infoSystemLog(Logger logger, String content) {
        logger.info(content);
    }

    public static void infoSystemLog(String content) {
//        System.out.println(content);
        infoLogger.info(content);
    }

    public static void performLog(Logger logger, String s) {
        logger.debug(s);
    }

    public static void performLog(String s) {
        performLogger.debug(s);
    }

    public static void detailLog(Logger logger, String s) {
        logger.debug(s);
    }

    public static void detailLog(String s) {
        detailLogger.debug(s);
    }

    public static void warnLog(String s) {
        errorLogger.warn(s);
    }
}
