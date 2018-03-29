package com.htschk.tai;


import com.htschk.tai.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * 请求触发: 发送 Post请求到指定 url, 将 json 格式的数据, 组装为 post 形式, 发送到指定 url 中
 * 从ModelAndView 中获取了结果集之后, 进行数据校验(后续可以切换为从其他来源中获取数据)
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Ignore
public class SampleScenarioTest extends PpCommonWebIntegrationTest {

    private boolean isFirstInitialized = false;


    private String token;


    @BeforeClass
    public static void init() {
        String env = "integration";
        System.setProperty("spring.config.name", "application,application-" + env);
    }

    @AfterClass
    public static void uninit() {
        ServletInitializerForTest.stop();
        applicationContext = null;
    }

    @Before
    public void setUp() {
        super.setUp();
        if (!isFirstInitialized) {

            isFirstInitialized = true;
        }
        httpSender.setReadTimeOut(30*1000);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void testSampleRest() {
        System.out.println("-----------testSampleRest");

        String requestString = "{\n" +
                "    \"requestType\": \"addDictTypeInfo\"\n" +
                "}";
        String response = httpSender.sendHttpRequest(baseUrl + "/restService", requestString);
        System.out.println(response);

        Map map = JsonUtil.readValue(response, Map.class);
        checkRestfulResultSuccess(map);
    }

}
