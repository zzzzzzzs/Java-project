package com.me.log;

import org.apache.log4j.Logger;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

/**
 * @author zs
 * @date 2023/1/2
 *
 * 指定日志写入到不同文件中
 * TODO 注意：web 项目读取 resources 路径下的文件要更换一下代码
 */
public class SpecifyLog {
    static {
        URL url = SpecifyLog.class.getResource("/specify-log4j.properties");
        PropertyConfigurator.configure(url);
    }
    private static final Logger test1Logger = Logger.getLogger("test1");
    public static void main(String[] args) {
        SpecifyLog specifyLog = new SpecifyLog();
        specifyLog.test1("这是一个测试日志.log");
        specifyLog.test1("这是一个测试日志xxx.log");
    }


    public void test1(String fileName) {
        FileAppender appender = (FileAppender) test1Logger.getAppender("TEST1_FILE");
        appender.setFile(fileName);
        appender.activateOptions();

        test1Logger.debug("This is a debug message for test1");
        test1Logger.info("This is an info message for test1");
    }

}
