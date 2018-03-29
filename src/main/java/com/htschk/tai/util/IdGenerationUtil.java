package com.htschk.tai.util;

import com.htschk.tai.common.TaiException;
import com.htschk.tai.common.TaiExceptionCode;
import com.htschk.tai.dao.SystemFunctionDao;
import com.htschk.tai.model.entity.common.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/*
todo : 增加各种情况的各类测试...

 */
@Component
public class IdGenerationUtil {

    @Autowired
    private SystemFunctionDao sfDao;

    private ConcurrentHashMap<String, Sequence> map = new ConcurrentHashMap();

    //todo : sync 关键字移入下层逻辑, 提升整体性能
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized long getSequenceByName(String name) {
        //根据 name 获取 Sequence 对象
        Sequence sequence = map.get(name);
        if (null == sequence) {
            sequence = getSequenceFromDB(name);
        }

        if (Objects.equals(sequence.getCurrentValue(), sequence.getEnd())) {
            sequence = getSequenceFromDB(name);
        }

        sequence.setCurrentValue(sequence.getCurrentValue() + 1);

        //如果为空, 从数据库中获取, 并且更新数据库值(如果获取失败抛出异常)

        //判断当前值是否和预设最大值相等, 如果相等则从数据库获取, 并且更新值
        //判断数据库中获取的最大值, 是否超出序列预设最大值, 超出则再次更新


        //从 Sequence 中获取当前值+1, 并且获取当前值


        return sequence.getCurrentValue();
    }


    private Sequence getSequenceFromDB(String name) {
        Sequence sequence = sfDao.getNextSeqByName(name);
        if (sequence == null) {
            throw new TaiException(TaiExceptionCode.OTHER_ERROR, "数据库中不包含该名字的序列号...");
        }
        String seqInfo = String.format("Seq(%s) From DB:[currentRoundBegin:%s],[currentRoundEnd:%s],[Incremental:%s]",
                sequence.getName(), sequence.getCurrentValue(), sequence.getEnd(), sequence.getIncrement());
        LogManager.infoSystemLog(seqInfo);
        if (!(sequence.getEnd() + sequence.getIncrement() > sequence.getMaxValue())) {
            sequence.setCurrentValue(sequence.getEnd());
            sequence.setEnd(sequence.getEnd() + sequence.getIncrement());
        } else {
            sequence.setEnd(sequence.getIncrement());
            sequence.setCurrentValue(sequence.getBegin());
        }
        seqInfo = String.format("Seq(%s) To update in DB:[currentRoundBegin:%s],[currentRoundEnd:%s],[Incremental:%s]",
                sequence.getName(), sequence.getCurrentValue(), sequence.getEnd(), sequence.getIncrement());
        LogManager.infoSystemLog(seqInfo);
        sfDao.updateSeqByName(sequence);
        map.put(name, sequence);
        return sequence;
    }


}
