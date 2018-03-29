package com.htschk.tai.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by qikai.yu on 2016/6/13.
 */
public class JsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static final ObjectMapper objectMapperExcludeNull = new ObjectMapper();

    static {
        objectMapperExcludeNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public JsonUtil() {
    }

    public static String writeValueAsStringExcludeNull(Object object) {
        try {
            return objectMapperExcludeNull.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            LogManager.errorLog("对象(without Null)Json序列化出错, Object:" + object, ex);
            return null;
        }
    }

    public static String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            LogManager.errorLog("对象Json序列化出错, Object:" + object, ex);
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            //todo: not set everytime
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(content, valueType);
        } catch (IOException ex) {
            String errorMsg = String.format("Json字符串反序列化失败, content: %s , clase: %s", content, valueType.getName());
            LogManager.errorLog(errorMsg, ex);
            return null;
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            //todo: not set everytime
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(content, valueTypeRef);
        } catch (Exception ex) {
            String errorMsg = String.format("Json字符串反序列化失败, content: %s , valueType: %s", content, valueTypeRef);
            LogManager.errorLog(errorMsg, ex);
            return null;
        }
    }

}
