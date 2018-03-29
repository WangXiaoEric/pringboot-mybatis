package com.htschk.tai.controller.restful;

import com.htschk.tai.model.CommonConstant;
import com.htschk.tai.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
//todo:后续关闭跨域访问
public class TaiRestfulFacade extends AbstractRestfulFacade {


    @RequestMapping(value = "/restService", method = {RequestMethod.POST},
            produces = {"application/json;charset=utf-8"})
    public Map<String, Object> process(@RequestBody String requestString, HttpServletRequest httpRequest) {
        Long t1 = System.currentTimeMillis();
        httpRequest.setAttribute(CommonConstant.DETAIL_LOG_STRING, StringUtils.deleteWhitespace(requestString));
        Map requestMap = JsonUtil.readValue(requestString, Map.class);
        Map<String, Object> result = doProcessLogic(httpRequest, requestMap);
        processDetailLog(httpRequest, t1);
        return result;
    }

    protected String validate(Map<String, Object> map, TaiRestfulRequestType requestType) {
        return "";
    }

}
