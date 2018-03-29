package com.htschk.tai.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yuqikai on 2017/6/22.
 */
public class EnvUtil {
    public static void preparePropertiesByEnv() {
        //如果环境已经设置为 integration 则不变(用于集成测试)
        String currentConfig = System.getProperty("spring.config.name");
        if (StringUtils.isNotEmpty(currentConfig) && currentConfig.contains("application-integration")) {
            return;
        }
        String env = System.getProperty("TAI_ENV");
        if (env == null) {
            env = "dev";
        }
        String envConfigFile = "application-" + env;

        //todo: log system current env
        LogManager.infoSystemLog("System Env:" + env);

        System.setProperty("spring.config.name", "application," + envConfigFile);
    }
}
