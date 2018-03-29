package com.htschk.tai.controller.restful.handler;

import com.htschk.tai.controller.restful.AbstractTaiRequestHandler;
import com.htschk.tai.controller.restful.TaiRestfulRequestType;
import com.htschk.tai.dao.SystemFunctionDao;
import com.htschk.tai.util.IdGenerationUtil;
import com.htschk.tai.util.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SampleRequestHandler extends AbstractTaiRequestHandler {

    @Autowired
    private IdGenerationUtil idGenerationUtil;

    @Override
    protected TaiRestfulRequestType getRequestType() {
        return TaiRestfulRequestType.addDictTypeInfo;
    }

    @Override
    public Object getResult(Map<String, Object> map) {
        LogManager.infoSystemLog("SampleRequestHandler.gerResult ...");

        System.out.println(map);
        System.out.println(map);

        Long id = idGenerationUtil.getSequenceByName("General");
        System.out.println(id);

        return null;
    }
}
