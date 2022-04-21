package com.jwh.nuaa.common;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 11:05
 */
public enum RecordMessage {
    UPDATE("[%s]:NetworkName:%s, DistplayName:%s, Update Status To %b \n"),
    REMOVE("[%s]:NetworkName:%s, DistplayName:%s, Rmove \n"),
    ADD("[%s]:NetworkName:%s, DistplayName:%s, ADD \n");


    private String value;

    RecordMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }}
