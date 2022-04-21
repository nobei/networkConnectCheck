package com.jwh.nuaa;

import com.jwh.nuaa.record.RecordPing;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 22:10
 */
@Slf4j
public class NetworkHandleByPing {
    private static final long keepTime = 3000;
    private static final String ipAddress = "www.baidu.com";
    private RecordPing recordPing;
    private boolean beforeStatus = false;

    public NetworkHandleByPing(){
        recordPing = new RecordPing();
    }

    public void doConnect(){
        boolean status = checkConnect();
        if(status != beforeStatus) {
            recordPing.networkUpdate(status);
            beforeStatus = status;
        }
    }

    public boolean checkConnect(){
        try {
            int timeOut = 3000;  //超时应该在3钞以上
            boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
            return status;
        }catch (UnknownHostException e){
            log.error("unknowHostException:{}, DNS error",e);
            return false;
        }catch (IOException e){
            log.error("unknow Exception during ping");
            return false;
        }


    }
}
