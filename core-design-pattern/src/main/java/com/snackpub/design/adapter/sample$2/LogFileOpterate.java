package com.snackpub.design.adapter.sample$2;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现对日志文件的操作
 *
 * @author snackpub
 * @date 2020/4/21
 */
@Slf4j
public class LogFileOpterate implements LogFileOperateApi {

    /**
     * 设置日志文件的路径和文件名称
     *  
     */
    private String logFileName = "file.log";

    public LogFileOpterate(String logFileName) {
        if (logFileName != null) {
            this.logFileName = logFileName;
        }
    }

    @Override
    public List<LogBean> readLogFile() {
        // TODO Auto-generated method stub
        List<LogBean> list = new ArrayList<>();
        ObjectInputStream oin = null;
        log.info("读取文件");
        //业务代码
        return list;
    }

    @Override
    public void writeLogFile(List<LogBean> list) {
        // TODO Auto-generated method stub
        File file = new File(logFileName);
        ObjectOutputStream oout = null;
        log.info("写入文件");
        //业务代码
    }
}
