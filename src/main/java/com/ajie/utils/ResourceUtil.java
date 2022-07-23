package com.ajie.utils;

import java.util.ResourceBundle;

/**
 * @ClassName ResourceUtil
 * @Description
 * @Author septzhang
 * @Date 2022/7/23 13:10
 * @Version 1.0
 **/
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        //配置文件的名称： *。properties
        resourceBundle = ResourceBundle.getBundle("setting");
    }

    /**
     *  获取配置文件参数
     * @param key key值
     * @return value值
     */
    public static String getKey(String key){
        return resourceBundle.getString(key);
    }
}
