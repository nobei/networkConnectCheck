package com.jwh.nuaa.record;

import com.jwh.nuaa.NetworkEntity;
import com.jwh.nuaa.Utils.DateUtil;
import com.jwh.nuaa.Utils.FileUtils;
import com.jwh.nuaa.common.RecordMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 16:33
 */
@Slf4j
public class RecordPersisitant extends RecordAbstract implements NetworkRecord {

    public RecordPersisitant() {
        recordFileCreate();
    }


    @Override
    public boolean networkUpdate(NetworkEntity networkEntity) {
        String time = DateUtil.getTime();
        String recordMessage = String.format(RecordMessage.UPDATE.getValue(), time, networkEntity.getName(),
                                                               networkEntity.getDisplayName(), networkEntity.isUp());
        return FileUtils.wirteFileByString(this.file, recordMessage);
    }

    @Override
    public boolean networkRemove(NetworkEntity networkEntity) {
        String time = DateUtil.getTime();
        String recordMessage = String.format(RecordMessage.REMOVE.getValue(), time, networkEntity.getName(), networkEntity.getDisplayName());
        return FileUtils.wirteFileByString(file, recordMessage);
    }

    @Override
    public boolean networkAdd(NetworkEntity networkEntity) {
        String time = DateUtil.getTime();
        String recordMessage = String.format(RecordMessage.ADD.getValue(), time, networkEntity.getName(), networkEntity.getDisplayName());
        return FileUtils.wirteFileByString(file, recordMessage);
    }
}
