package com.jwh.nuaa;

import lombok.Data;

import java.net.InetAddress;
import java.util.List;
import java.util.Objects;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 13:39
 */
@Data
public class NetworkEntity {
    private String name;
    private String displayName;
    private int index;
    private List<InetAddress> addrs;
    private boolean virtual = false;
    private boolean isUp = false;

    @Override
    public boolean equals(Object obj) {
        NetworkEntity networkEntity = (NetworkEntity) obj;
        return name.equals(networkEntity.getName()) && index == networkEntity.index
                                            && isUp == networkEntity.isUp;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index, isUp);
    }
}
