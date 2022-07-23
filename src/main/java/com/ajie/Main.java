package com.ajie;

import com.ajie.utils.ResourceUtil;
import com.ajie.utils.SystemUtil;
import com.ajie.utils.TextUtil;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName Main
 * @Description 启动类
 * @Author septzhang
 * @Date 2022/7/23 12:24
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        //System.out.println(SystemUtil.JudgeSystem());
        System.out.print(TextUtil.wirteFile());
    }
}
