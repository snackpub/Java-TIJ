package com.snackpub.design.adapter.sample$2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author snackpub
 * @date 2020/4/21
 */
public class LogClient {

    public static void main(String[] args) {
        LogBean logBean = new LogBean();
        logBean.setLogId("1");
        logBean.setOpeUserId("001");
        List<LogBean> list = new ArrayList<LogBean>();
        LogFileOpterate logFileOperateApi = new LogFileOpterate("");
        /*logFileOperateApi.writeLogFile(list);
        logFileOperateApi.readLogFile();*/
        LogDbOpeApi logDbOpeApi = new LogAdapter(logFileOperateApi);
        logDbOpeApi.createLog(logBean);
    }
}
