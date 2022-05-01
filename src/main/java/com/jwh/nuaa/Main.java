package com.jwh.nuaa;

import com.jwh.nuaa.Utils.DateUtil;
import com.jwh.nuaa.Utils.FileUtils;
import com.jwh.nuaa.common.RECORDTYPE;

import java.io.File;
import java.net.SocketException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 20:01
 */
public class Main {
    public static void main(String args[]) throws ExecutionException, InterruptedException {

//        new Thread(()->{
//            NetworkHandle networkHandle = new NetworkHandle(RECORDTYPE.PERSISTANT);
//            try {
//                while(true) {
//                    networkHandle.updateInterfaceInformation();
//                }
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }
//        }).start();

        CompletableFuture completableFuture = CompletableFuture.runAsync(()->{
            NetworkHandleByPing networkHandleByPing = new NetworkHandleByPing();
            NetworkHandle networkHandle = new NetworkHandle(RECORDTYPE.PERSISTANT);
            long endTime = DateUtil.getTomrrowTime(new Date());
            long nowTime = DateUtil.getTimeStamp();
            while(nowTime < endTime){
                networkHandleByPing.doConnect();
                try {
                    networkHandle.updateInterfaceInformation();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (SocketException e){
                    e.printStackTrace();
                }
                nowTime = DateUtil.getTimeStamp();
            }
            System.out.println(nowTime);
            System.out.println(endTime);
        });

        completableFuture.get();



    }
}
