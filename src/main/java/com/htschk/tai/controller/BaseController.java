package com.htschk.tai.controller;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import com.htschk.tai.model.request.BaseRequest;
import com.htschk.tai.util.JsonUtil;
import com.htschk.tai.util.LogManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuqikai on 2017/6/20.
 */

public abstract class BaseController {

    @ExceptionHandler
    public String processException(HttpServletRequest request, Exception ex) {
        TaiException e;
        if (ex instanceof TaiException) {//区分异常进行处理, 原则上应该均为 TaiException
            e = (TaiException) ex;
        } else {//如果为 Exception 则说明为系统意料外异常, 需要特殊处理
            e = new TaiException(TaiExceptionCode.UNEXPECTED, "获取到未知异常", ex);
        }

        request.setAttribute("errorMessage", e.getMessage());

        LogManager.errorLog(e);

        request.setAttribute("ex", ex.toString());
        return "errorPage";
    }


    protected void processSession(BaseRequest request, HttpServletRequest httpRequest) {
        //根据 session 获取到用户, 并且获取用户相关信息, 设置到 request 中
        /*
            获取 sessionId , 根据 id 获取到登录用户的 UserEntity 缓存对象

            如果为空, 抛出异常
         */

        if (StringUtils.isNotEmpty(request.getBizModule())) {//用于采购平台, BizModule的区分(采购中心/销售中心等)
            httpRequest.getSession().setAttribute("bizModule", request.getBizModule());
        }
        String requestString = JsonUtil.writeValueAsString(request);
    }

}
