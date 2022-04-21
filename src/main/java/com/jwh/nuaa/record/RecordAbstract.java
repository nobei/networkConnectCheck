package com.jwh.nuaa.record;

import com.jwh.nuaa.Utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 23:05
 */
@Slf4j
public abstract class RecordAbstract{
    protected String recordPath = null;
    protected File file = null;

    public void recordFileCreate(){
        ResourceBundle rb = FileUtils.readPropertiesFile("record");
        if (rb.containsKey("logFilePath")) {
            recordPath = rb.getString("logFilePath");
        } else {
            log.warn("there is no record properties for record");
            recordPath = "./";
        }
        Date date = new Date();
        long timeStamp = date.getTime();
        String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        file = FileUtils.creatFile(recordPath, nowtime + " " + timeStamp + ".txt");
    }


}
