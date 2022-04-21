package com.jwh.nuaa.record;

import com.jwh.nuaa.NetworkEntity;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 13:36
 */
public interface NetworkRecord {
    boolean networkUpdate(NetworkEntity networkEntity);
    boolean networkRemove(NetworkEntity networkEntity);
    boolean networkAdd(NetworkEntity networkEntity);
}
