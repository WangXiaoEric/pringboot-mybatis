package com.htschk.tai;


import com.htschk.tai.service.ScheduleService;
import com.htschk.tai.util.DBTestHelper;
import com.htschk.tai.util.HttpSender;
import com.htschk.tai.util.IdGenerationUtil;
import com.htschk.tai.util.IdGenerationUtilMock;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 请求触发: 发送 Post请求到指定 url, 将 json 格式的数据, 组装为 post 形式, 发送到指定 url 中
 * 从ModelAndView 中获取了结果集之后, 进行数据校验(后续可以切换为从其他来源中获取数据)
 */
abstract public class PpCommonWebIntegrationTest {

    static {
        String env = "integration";
        System.setProperty("spring.config.name", "application,application-" + env);
    }

    protected static ApplicationContext applicationContext;
    protected static DBTestHelper dbTestHelper;
    protected static IdGenerationUtil idGenerationUtil;
    protected static ScheduleService scheduleService;
    protected static HttpSender httpSender;

    protected String baseUrl = "http://127.0.0.1:8080";

    public void setUp() {
        if (applicationContext == null) {
            ServletInitializerForTest.main(new String[]{});
            applicationContext = ServletInitializerForTest.getApplicationContext();
            dbTestHelper = applicationContext.getBean(DBTestHelper.class);
            idGenerationUtil = applicationContext.getBean(IdGenerationUtil.class);
            scheduleService = applicationContext.getBean(ScheduleService.class);
            httpSender = applicationContext.getBean(HttpSender.class);
        }

    }

    public void tearDown() {
        ((IdGenerationUtilMock) idGenerationUtil).resetAllSequence();
    }

    protected HttpSession generateMockHttpSession() {
        HttpSession session = new MockHttpSession();
        return session;
    }

    protected HttpServletRequest generateMockHttpServletRequest() {
        HttpServletRequest request = new MockHttpServletRequest();
        return request;
    }

    protected void setSequence(String name, Long startId) {
        ((IdGenerationUtilMock) idGenerationUtil).setSequence(name, startId);
    }

    protected <T> T processModelAndViewResult(Object obj, String fieldName) {
        Object o = ((ModelAndView) obj).getModel().get(fieldName);
        if (o != null) {
            return (T) o;
        }
        return null;
    }

    protected <T> T processModelAndViewResult(Object obj) {
        Object o = ((ModelAndView) obj).getModel().get("result");
        if (o != null) {
            return (T) o;
        }
        return null;
    }


    protected <T> T processRestfulResult(Object obj) {
        return processRestfulResult(obj, "result");
    }

    protected <T> T processRestfulResult(Object obj, String fieldName) {
        Object o = ((Map) obj).get(fieldName);
        if (o != null) {
            return (T) o;
        }
        return null;
    }

    protected void checkRestfulResultSuccess(Object obj) {
        String returnCode = processRestfulResult(obj, "return_code");
        String returnMessage = processRestfulResult(obj, "return_message");
        Assert.assertEquals("0", returnCode);
        Assert.assertEquals("Success", returnMessage);
    }

    protected void checkRestfulResultFail(Object obj) {
        String returnCode = processRestfulResult(obj, "return_code");
        String returnMessage = processRestfulResult(obj, "return_message");
        Assert.assertNotEquals("0", returnCode);
        Assert.assertNotEquals("Success", returnMessage);
    }


}

