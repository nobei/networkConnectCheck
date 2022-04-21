package com.jwh.nuaa.Utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 9:58
 */
@Slf4j
public class FileUtils {
    private final static int BYTESIZE = 1024;
    public static ResourceBundle readPropertiesFile(String path) {
        ResourceBundle rb = ResourceBundle.getBundle(path); //读取resources目录下的b.properties
        return rb;
    }

    public static File creatFile(String filePath,String name) {
        File file = new File(filePath+name);
        try {
            if (file.createNewFile())
                System.out.println("文件创建成功！");
            else
                System.out.println("出错了，该文件已经存在。");
        } catch (IOException ioe) {
            log.error("file create failed");
            ioe.printStackTrace();
        }
        return file;
    }

    public static boolean wirteFileByString(File file, String context){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);
            fw.write(context);
        } catch (IOException e) {
            log.error("write file error:{}",e);
            e.printStackTrace();
            return false;
        }finally {
            if(fw != null){
                try {
                    fw.flush();
                    fw.close();
                }catch (IOException e){
                    log.error("close file error:{} ",e);
                    return false;
                }
            }
        }
        return true;
    }

}
