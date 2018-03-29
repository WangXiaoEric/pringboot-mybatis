package com.htschk.tai.service;

import com.htschk.tai.util.IdGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuqikai on 2017/6/20.
 */
@Service
public class IdService {
    @Autowired
    private IdGenerationUtil util;

    public Long generateGeneralId() {
        return util.getSequenceByName("General");
    }

}
