package com.snackpub.design.adapter.sample$2;

import java.util.List;

/**
 * 适配器
 *
 * @author snackpub
 * @date 2020/4/21
 */
public class LogAdapter implements LogDbOpeApi {

    /**
     * 被适配者
     */
    private LogFileOperateApi adaptee;

    public LogAdapter(LogFileOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void createLog(LogBean logbean) {
        List<LogBean> logBeans = adaptee.readLogFile();
        logBeans.add(logbean);
        adaptee.writeLogFile(logBeans);
    }
}
