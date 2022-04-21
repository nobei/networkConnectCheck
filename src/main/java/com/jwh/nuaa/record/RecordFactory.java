package com.jwh.nuaa.record;

import com.jwh.nuaa.common.RECORDTYPE;
import com.jwh.nuaa.record.NetworkRecord;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 16:17
 */
@Slf4j
public class RecordFactory {


    public static NetworkRecord getNetworkRecord(RECORDTYPE recordType){
        if(RECORDTYPE.LOGINFO == recordType){
            return new RecordLog();
        }else if(RECORDTYPE.PERSISTANT == recordType){
            return new RecordPersisitant();
        }else{
            log.error("recordNetwork init exception: message");
            throw new RuntimeException("getNetworkRecordException");
        }
    }

}
