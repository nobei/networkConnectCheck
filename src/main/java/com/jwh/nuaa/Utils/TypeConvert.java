package com.jwh.nuaa.Utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/20 13:52
 */
@Slf4j
public class TypeConvert {
    public <T, P> T convertEntity(P entrty) throws Exception {
        try {
            T target = (T) entrty.getClass().newInstance();
            Field[] fieldTarge = target.getClass().getDeclaredFields();
            for (Field field : fieldTarge) {
                Field sourceField = entrty.getClass().getDeclaredField(field.getName());
                if (sourceField.getType() == field.getType()) {
                    field.setAccessible(true);
                    field.set(target, sourceField);
                }else {
                    log.warn("set var:{} failed, message:", sourceField.toGenericString());
                }
            }
            return target;
        } catch (IllegalAccessException e) {
            log.error("create Object Exception: {}", e);
            throw e;
        }
    }


    public static <T> List<T> interfaceConvert(Enumeration<T> enumeration) {
        List<T> list = new ArrayList<>();
        if (enumeration != null)
            while (enumeration.hasMoreElements()) {
                list.add(enumeration.nextElement());
            }
        return list;
    }
}
