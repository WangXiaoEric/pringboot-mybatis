package com.htschk.tai.controller.restful;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by qikai.yu on 2016/6/16.
 */
abstract public class AbstractTaiRequestHandler implements TaiRestfulRequestHandler {
    @Autowired
    private HandlerMappingUtil handlerMappingUtil;

    protected abstract TaiRestfulRequestType getRequestType();

    public String validate(Map<String, Object> map){
        return "";
    }

    @PostConstruct
    public void init(){
        handlerMappingUtil.addHandler(getRequestType(),this);
    }

    protected String notNullValidator(Map map, String[] fields){
        StringBuilder sb = new StringBuilder();
        for(String fieldName:fields){
            Object obj = map.get(fieldName);
            if(obj==null|| StringUtils.isEmpty(obj.toString().trim())){
                sb.append("field:").append(fieldName).append(" is missing");
            }
        }
        return sb.toString();
    }

    protected String idValidate(Map map){
//        String[] id = {CrmConstant.ID};
//        String result = notNullValidator(map,id);
//        if(CrmConstant.BLANK.equals(result)){
//            result += notNumberValidator(map,id);
//        }
        return "";
    }

    protected String notNumberValidator(Map map,String[] fields){
        return "";
    }

    protected void handleException(Exception e)throws Exception{

    }
}
