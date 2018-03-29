package com.htschk.tai.controller.restful;


import com.htschk.tai.common.TaiException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HandlerMappingUtil {

    private Map<TaiRestfulRequestType, TaiRestfulRequestHandler> mappings = new ConcurrentHashMap<>();

    public TaiRestfulRequestHandler getHandler(TaiRestfulRequestType type){
        return mappings.get(type);
    }

    public synchronized void addHandler(TaiRestfulRequestType type, TaiRestfulRequestHandler handler){

        //todo:
        if(mappings.containsKey(type)){
            throw new TaiException("Restful Type Error");
            // TODO: 2016/6/16 new exception
        }
        mappings.put(type, handler);
    }


}
