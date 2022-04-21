package com.jwh.nuaa;

import com.jwh.nuaa.Utils.FileUtils;
import com.jwh.nuaa.common.RECORDTYPE;

import java.io.File;
import java.net.SocketException;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 20:01
 */
public class Main {
    public static void main(String args[]){

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

        new Thread(()->{
            NetworkHandleByPing networkHandleByPing = new NetworkHandleByPing();
            while(true){
                networkHandleByPing.doConnect();
            }
        }).start();
    }
}
