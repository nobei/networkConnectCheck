package com.jwh.nuaa;

import com.jwh.nuaa.Utils.TypeConvert;
import com.jwh.nuaa.common.RECORDTYPE;
import com.jwh.nuaa.record.NetworkRecord;
import com.jwh.nuaa.record.RecordFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.*;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 13:14
 */
@Data
@Slf4j
public class NetworkHandle {
    private Map<String, NetworkEntity> networkEntityMap = new HashMap<String, NetworkEntity>();
    private NetworkRecord networkRecord;
    private RECORDTYPE recordtype = null;

    public NetworkHandle(RECORDTYPE recordType) {
        this.recordtype = recordType;
        this.networkRecord = RecordFactory.getNetworkRecord(recordType);
    }

    public Map<String, NetworkEntity> getNetworkInterfaceMap() {
        return networkEntityMap;
    }

    public Enumeration<NetworkInterface> getAllInterface() throws SocketException {
        try {
            return NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            log.error("service socket exception{}", e);
            throw e;
        }
    }

    public void updateInterfaceInformation() throws SocketException {
       //  log.info("thread start monitor network status");
        Enumeration<NetworkInterface> networkInterfaces = getAllInterface();
        if (networkRecord == null) {
            networkRecord = RecordFactory.getNetworkRecord(this.getRecordtype());
        }
        if (networkInterfaces == null) {
            networkInterfaces = waitInit();
        }
        updateInterface(networkInterfaces);

    }

    public Enumeration waitInit() throws SocketException {
        Enumeration<NetworkInterface> enumeration = null;
        while (true) {
            enumeration = getAllInterface();
            if (enumeration != null && enumeration.hasMoreElements()) {
                break;
            }
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                log.error("thread:{} sleep is interrupted", Thread.currentThread());
            }
        }

        log.info("thread start update status");
        return enumeration;
    }


    public void updateInterface(Enumeration<NetworkInterface> interfaceEnumeration) {
        List<NetworkInterface> networkEntities = TypeConvert.interfaceConvert(interfaceEnumeration);
        List<NetworkEntity> entities = networkEntities.stream().filter(Objects::nonNull).map((entity) -> convert(entity))
                .collect(Collectors.toList());
        for (NetworkEntity networkEntity : entities) {
            if (networkEntityMap.containsKey(networkEntity.getName())) {
                NetworkEntity entity = networkEntityMap.get(networkEntity.getName());
                if (!networkEntity.equals(entity)) {   // 更新
                    networkRecord.networkUpdate(networkEntity);
                    networkEntityMap.put(networkEntity.getName(), networkEntity);
                }
            } else {    // 增加
                networkRecord.networkAdd(networkEntity);
                networkEntityMap.put(networkEntity.getName(), networkEntity);
            }
        }

        Set<String> networkEntitySet = networkEntityMap.keySet();
        for (String name : networkEntitySet) {
            NetworkEntity networkEntity = networkEntityMap.get(name);
            if (!entities.contains(networkEntity)) {  // 移除失效记录
                networkRecord.networkRemove(networkEntity);
                networkEntityMap.remove(networkEntity.getName());
            }
        }

    }


    public NetworkEntity convert(NetworkInterface networkInterface) {
        NetworkEntity networkEntity = new NetworkEntity();
        try {
            networkEntity.setDisplayName(networkInterface.getDisplayName());
            networkEntity.setIndex(networkInterface.getIndex());
            networkEntity.setName(networkInterface.getName());
            networkEntity.setVirtual(networkInterface.isVirtual());
            networkEntity.setAddrs(TypeConvert.interfaceConvert(networkInterface.getInetAddresses()));
            networkEntity.setUp(networkInterface.isUp());
        } catch (SocketException e) {
            log.error("check Interface is up failed:{}", e);
        }
        return networkEntity;
    }
}
