package com.ajie.utils;

/**
 * @ClassName SystemUtil
 * @Description
 * @Author septzhang
 * @Date 2022/7/23 13:20
 * @Version 1.0
 **/
public class SystemUtil {

    //判断Linux
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    //判断Windows
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    //返回系统类型
    public static String JudgeSystem() {
        if (isLinux()) {
            return "linux";
        } else if (isWindows()) {
            return "windows";
        } else {
            return "other system";
        }
    }
}
