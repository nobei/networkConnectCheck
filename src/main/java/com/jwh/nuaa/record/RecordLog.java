package com.jwh.nuaa.record;

import com.jwh.nuaa.NetworkEntity;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 16:32
 */
@Slf4j
public class RecordLog implements NetworkRecord {
    @Override
    public boolean networkUpdate(NetworkEntity networkEntity) {
        log.info("networkUpdate:{}", networkEntity.toString());
        return true;
    }

    @Override
    public boolean networkRemove(NetworkEntity networkEntity) {
        log.info("networkRemove:{}", networkEntity.toString());
        return false;
    }

    @Override
    public boolean networkAdd(NetworkEntity networkEntity) {
        log.info("networkAdd:{}", networkEntity.toString());
        return false;
    }
}
