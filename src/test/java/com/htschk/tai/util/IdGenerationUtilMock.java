package com.htschk.tai.util;

import java.util.concurrent.ConcurrentHashMap;

public class IdGenerationUtilMock extends IdGenerationUtil {


    private Boolean useRealSeq =false;

    public void setUseRealSeq(Boolean useRealSeq) {
        this.useRealSeq = useRealSeq;
    }

    private ConcurrentHashMap<String, Long> map = new ConcurrentHashMap();

    public synchronized long getSequenceByName(String name) {
        if(useRealSeq){
            return super.getSequenceByName(name);
        }

        if (map.get(name) != null) {
            Long tempL = map.get(name);
            map.put(name, tempL + 1);
            return tempL + 1;
        } else {
            map.put(name, 1l);
            return 1;
        }
    }
    public void setSequence(String name, Long id) {
        map.put(name, id);
    }

    public void resetAllSequence() {
        map.entrySet().forEach(
            it -> {
                it.setValue(0l);
            }
        );
    }

}
