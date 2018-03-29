package com.htschk.tai.controller.restful;

import java.util.Map;

public interface TaiRestfulRequestHandler {
    String validate(Map<String, Object> map);
    Object getResult(Map<String, Object> map) throws Exception;
}
