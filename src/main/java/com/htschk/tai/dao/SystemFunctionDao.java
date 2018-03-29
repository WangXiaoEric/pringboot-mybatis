package com.htschk.tai.dao;

import com.htschk.tai.model.entity.common.Sequence;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yuqikai on 2017/6/19.
 */
@Component
public class SystemFunctionDao extends AbstractDaoImpl{
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private static final String GET_SEQUENCE_BY_NAME = "c.h.tai.dao.sysfunc.getNextSequenceByName";
    private static final String UPDATE_SEQUENCE_BY_NAME = "c.h.tai.dao.sysfunc.updateSeqByName";


    public Sequence getNextSeqByName(String name) {
        return sqlSessionTemplate.selectOne(GET_SEQUENCE_BY_NAME, name);
    }

    public void updateSeqByName(Sequence sequence) {
        sqlSessionTemplate.update(UPDATE_SEQUENCE_BY_NAME, sequence);
    }
}
