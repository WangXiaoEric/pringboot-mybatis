package com.htschk.tai.controller.restful;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import com.htschk.tai.model.CommonConstant;
import com.htschk.tai.util.JsonUtil;
import com.htschk.tai.util.LogManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

abstract public class AbstractRestfulFacade {
    @Value("${config.needPerformanceLog:false}")
    protected boolean needPerformanceLog;

    @Autowired
    protected HandlerMappingUtil handlerMappingUtil;

    protected Map<String, Object> doProcessLogic(HttpServletRequest httpRequest, Map requestMap) {
        String typeStr = (String) requestMap.get("requestType");
        TaiRestfulRequestType type = TaiRestfulRequestType.valueOf(typeStr);

        //0. Map 转换
        long t1 = System.currentTimeMillis();
        try {
            //注:文件上传避免File对象序列化异常信息
//            String requestString;
//            if (requestMap.containsKey("requestString")) {
//                Object obj = requestMap.get("requestString");
//                requestString = obj != null ? obj.toString() : "";
//            } else {
//                requestString = JsonUtil.writeValueAsString(requestMap);
//            }

            //1. 公共校验
            String validateString = validate(requestMap, type);
            if (StringUtils.isNotEmpty(validateString)) {
                //todo : exception update
                throw new TaiException(TaiExceptionCode.OTHER_ERROR, validateString);
            }
            //2. 根据 type 获取到具体的 processor
            TaiRestfulRequestHandler handler = handlerMappingUtil.getHandler(type);
            if (handler == null) {
                //todo : exception update
                throw new TaiException(TaiExceptionCode.OTHER_ERROR,
                        "不支持的请求类型(" + type.name() + ")");
            }

            //3. 调用具体 process 进行业务校验
            String bizErrorMessage = handler.validate(requestMap);
            if (StringUtils.isNotEmpty(bizErrorMessage)) {
                throw new TaiException(TaiExceptionCode.OTHER_ERROR,
                        "业务参数校验失败,原因:" + bizErrorMessage);
            }

            Object result = handler.getResult(requestMap);
            //4. 如果成功则进行真正业务处理
            return generateNormalResult(result, type, t1);
        } catch (Exception e) {
            //todo : exception update
            TaiException ppExp;
            if (e instanceof TaiException) {
                ppExp = (TaiException) e;
            } else {
                ppExp = new TaiException(TaiExceptionCode.OTHER_ERROR.OTHER_ERROR, "未知异常", e);
            }
            LogManager.errorLog(ppExp);
            return generateResultForException(ppExp, type, t1);
        }
    }

    abstract String validate(Map<String, Object> map, TaiRestfulRequestType requestType);

    protected void processDetailLog(HttpServletRequest httpRequest, Long t1) {
        Long t2 = System.currentTimeMillis();
        if (needPerformanceLog) {
            Object detailInfo = httpRequest.getAttribute(CommonConstant.DETAIL_LOG_STRING);
            String detailLog = "[" + (t2 - t1) + "ms]" + detailInfo;
            httpRequest.removeAttribute(CommonConstant.DETAIL_LOG_STRING);
            LogManager.detailLog(detailLog);
        }
    }

    protected Map<String, Object> generateNormalResult(Object obj, TaiRestfulRequestType type, Long t1) {
        Map map = new HashMap<>();
        map.put(CommonConstant.RETURN_CODE, CommonConstant.RETURN_CODE_SUCCESS);
        map.put(CommonConstant.RETURN_MSG, "Success");
        int count = 0;
        if (obj != null) {
            if (obj instanceof List) {
                count = ((List) obj).size();
                map.put(CommonConstant.RESULT, obj);
            } else {
                count = 1;
                List l = new ArrayList<>();
                l.add(obj);
                map.put(CommonConstant.RESULT, l);
            }
        } else {
            map.put(CommonConstant.RESULT, null);
        }
        map.put(CommonConstant.RESULT_COUNT, count);
        return map;
    }

    protected Map<String, Object> generateResultForException(TaiException exception, TaiRestfulRequestType type, Long t1) {
        return generateResultWithErrorMessage(exception.getErrorCode().getResponseCode(),
                StringUtils.isEmpty(exception.getErrorInfo()) ? exception.getMessage() : exception.getErrorInfo()
                , type, t1);
    }

    protected Map<String, Object> generateResultWithErrorMessage(String responseCode, String errorMessage,
                                                                 TaiRestfulRequestType type, Long t1) {
        Map map = new HashMap<>();
        map.put(CommonConstant.RETURN_CODE, responseCode);
        map.put(CommonConstant.RETURN_MSG, errorMessage);
        map.put(CommonConstant.RESULT_COUNT, 0);
        map.put(CommonConstant.RESULT, Collections.EMPTY_LIST);
//        PerformanceUtil.logPerformance(type, t1, false);
        return map;
    }
}
