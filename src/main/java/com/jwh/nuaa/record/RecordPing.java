package com.jwh.nuaa.record;

import com.jwh.nuaa.NetworkEntity;
import com.jwh.nuaa.Utils.DateUtil;
import com.jwh.nuaa.Utils.FileUtils;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 23:04
 */
public class RecordPing extends RecordAbstract {
    public RecordPing() {
        recordFileCreate();
    }


    public boolean networkUpdate(boolean isConnected) {
        String time = DateUtil.getTime();
        String logTemplate = "[%s]: network status update:[%b] \n";
        String logMessage = String.format(logTemplate, time, isConnected);
        return FileUtils.wirteFileByString(file, logMessage);
    }


}
