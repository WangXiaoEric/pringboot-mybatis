package com.htschk.tai.controller;

import com.htschk.tai.model.request.BaseRequest;
import com.htschk.tai.util.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuqikai on 2017/6/14.
 */
@Controller
public class SampleController extends BaseController {

    /*
        考虑后期前后端分离的接口方案:
            使用同样的 Request 类, service 方法中进行实体数据的返回
            目前: form 表单数据 -> Request 类
            以后: json 数据 -> Request 类
            后续需要 restful 接口方式下, 新增 facade 接口类进行实现
     */

    @RequestMapping(value = "/testSample.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView queryUserList(BaseRequest request, HttpServletRequest httpRequest) {//done for test
        LogManager.infoSystemLog("in queryUserList ...");
        processSession(request, httpRequest);



        ModelAndView model = new ModelAndView();
        model.setViewName("buReport");
        return model;
    }

}
