package com.ajie.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @ClassName TextUtil
 * @Description 文本工具包
 * @Author septzhang
 * @Date 2022/7/23 12:25
 * @Version 1.0
 **/
public class TextUtil {
    //春节
    public static int getChunJie(LocalDateTime now){
        //2022 2 1
        LocalDateTime nextZhongQiu = LocalDateTime.of(now.getYear()+1,2,1,0,0);
        return (int) Duration.between(now,nextZhongQiu).toDays();
    }


    //端午
    public static int getNextDuanWu(LocalDateTime now){
        //2022 6 3
        LocalDateTime nextZhongQiu = LocalDateTime.of(now.getYear()+1,6,3,0,0);
        return (int) Duration.between(now,nextZhongQiu).toDays();
    }

    //中秋
    public static int getNextZhongQiu(LocalDateTime now){
        //2022 9 10
        LocalDateTime nextZhongQiu = LocalDateTime.of(now.getYear()+1,9,10,0,0);
        return (int) Duration.between(now,nextZhongQiu).toDays();
    }

    //周末
    public static int getFixDay(LocalDateTime now,int month,int day){
        if(now.getMonthValue()>month){
            LocalDateTime nextFixDay = LocalDateTime.of(now.getYear()+1,month,day,0,0);
            return (int) Duration.between(now,nextFixDay).toDays();
        }else{
            LocalDateTime nextFixDay = LocalDateTime.of(now.getYear(),month,day,0,0);
            return (int) Duration.between(now,nextFixDay).toDays();
        }
    }

    //清明
    public static int getQingMing(LocalDateTime now) {
        //判断是否已过
        int year = now.getYear();
        if(now.getMonthValue()>4){
            year++;
        }else if(now.getMonthValue() == 4){
            int day = getQingMingDay(year);
            if( day >now.getDayOfMonth() ){
                //返回下一年的清明
                year++;
                day = getQingMingDay(year);
                LocalDateTime nextQingMing = LocalDateTime.of(year,4,day,0,0);
                return (int) Duration.between(now, nextQingMing).toDays();
            }
        }
        //小于等于4
        LocalDateTime nextQingMing = LocalDateTime.of(year, 4, getQingMingDay(year), 0, 0);
        return (int) Duration.between(now, nextQingMing).toDays();
    }

    public static int getQingMingDay(int year){
        if (year == 2232) {
            return 4;
        }
        if (year < 1700) {
            throw new RuntimeException("1700年之前暂时不支持");
        }
        if (year >= 3100) {
            throw new RuntimeException("3100年之后暂时不支持");
        }
        double[] coefficient = { 5.15, 5.37, 5.59, 4.82, 5.02, 5.26, 5.48, 4.70, 4.92, 5.135, 5.36, 4.60, 4.81, 5.04,
                5.26 };
        int mod = year % 100;
        return (int) (mod * 0.2422 + coefficient[year / 100 - 17] - mod / 4);
    }
    //开头雨
    public static String getHello(){
        String a1 = "摸鱼能增加工作动力，摸鱼能放松筋骨舒展神经。人都是被摸鱼摸大的，摸鱼是人的天性，无论是顺境还是逆境，工作摸鱼，才不会被工作抛弃。";
        String a2 = "工作再累，一定不要忘记摸鱼哦！有事没事起身去茶水间，去厕所，去廊道走走别老在工位上坐着，钱是老板的,但命是自己的。";
        String a3 = "生活需要摸鱼，而不是认真工作。有时候你需要摸鱼，让工作顺其自然，不要过分担心，也不要过于细致的规划。学会摸鱼，不要逼自己逼得太紧。深呼吸。尘埃落定时你会再次看见森林中的树木。";
        List<String> hellos = new ArrayList<>();
        hellos.add(a1);
        hellos.add(a2);
        hellos.add(a3);
        Random r = new Random();
        return hellos.get(r.nextInt(hellos.size()));
    }
    //结束语
    public static String getEnd(){
        String a1 = "上班是帮老板赚钱，摸鱼是赚老板的钱！最后，祝愿天下所有摸鱼人，都能愉快的渡过每一天！！！！";
//        String a2 = "工作再累，一定不要忘记摸鱼哦！有事没事起身去茶水间，去厕所，去廊道走走别老在工位上坐着，钱是老板的,但命是自己的。";
//        String a3 = "生活需要摸鱼，而不是认真工作。有时候你需要摸鱼，让工作顺其自然，不要过分担心，也不要过于细致的规划。学会摸鱼，不要逼自己逼得太紧。深呼吸。尘埃落定时你会再次看见森林中的树木。";
        List<String> hellos = new ArrayList<>();
        hellos.add(a1);
//        hellos.add(a2);
//        hellos.add(a3);
        Random r = new Random();
        return hellos.get(r.nextInt(hellos.size()));
    }

    //时间段
    public static String amOrPm(int a){
        if (a >= 0 && a <= 6) {
            return "凌晨";
        }
        if (a > 6 && a < 12) {
            return "上午";
        }
        if (a == 13) {
            return "中午";
        }
        if (a > 13 && a <= 18) {
            return "下午";
        }else{
            return "晚上";
        }
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取摸鱼文案
     * @return 文案
     */
    public static String getText(){
        //初始化字符串
        StringBuilder sb = new StringBuilder();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.with(TemporalAdjusters.next(DayOfWeek.of(6)));
        LocalDateTime newYear = LocalDateTime.of(now.getYear()+1,1,1,0,0);
        LocalDateTime workOver = LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),17,30);
        Duration newYearDays = Duration.between(now,newYear);
        int overWorkTime = (int) Duration.between(now, workOver).toMinutes();

        //拼接字符串
        System.out.println("【摸鱼办】提醒您: "+now.getMonthValue()+"月" +now.getDayOfMonth()+"日"+TextUtil.amOrPm(now.getHour())+"好,摸鱼人!" +TextUtil.getHello());
        sb.append( "\n【摸鱼办】提醒您: "+now.getMonthValue()+"月" +now.getDayOfMonth()+"日"+TextUtil.amOrPm(now.getHour())+"好,摸鱼人!" +TextUtil.getHello());
        if(overWorkTime>0) {
            System.out.println("距离下班还有: " + overWorkTime + "分钟");
            sb.append("\n距离下班还有: " + overWorkTime + "分钟");
        }else{
            System.out.println("已经下班, 快走快走, 在公司多待一分钟就亏一分钟.");
            sb.append("\n已经下班, 快走快走, 在公司多待一分钟就亏一分钟.");
        }
        System.out.println("距离周末还有: "+Duration.between(now,nextWeek).toDays()+"天");
        sb.append("\n距离周末还有: "+Duration.between(now,nextWeek).toDays()+"天") ;
        System.out.println("距离元旦还有: "+newYearDays.toDays()+"天");
        sb.append("\n距离元旦还有: "+newYearDays.toDays()+"天");
        System.out.println("距离春节还有: "+ TextUtil.getChunJie(now)+"天");
        sb.append("\n距离春节还有: "+ TextUtil.getChunJie(now)+"天");
        System.out.println("距离清明还有: "+TextUtil.getQingMing(now)+"天");
        sb.append("\n距离清明还有: "+TextUtil.getQingMing(now)+"天") ;
        System.out.println("距离劳动节还有: "+TextUtil.getFixDay(now,5,1)+"天");
        sb.append("\n距离劳动节还有: "+TextUtil.getFixDay(now,5,1)+"天");
        System.out.println("距离端午节还有: "+TextUtil.getNextDuanWu(now)+"天");
        sb.append("\n距离端午节还有: "+TextUtil.getNextDuanWu(now)+"天");
        System.out.println("距离中秋节还有: "+TextUtil.getNextZhongQiu(now)+"天");
        sb.append("\n距离中秋节还有: "+TextUtil.getNextZhongQiu(now)+"天");
        System.out.println("距离国庆节还有: "+TextUtil.getFixDay(now,10,1)+"天");
        sb.append("\n距离国庆节还有: "+TextUtil.getFixDay(now,10,1)+"天");
        System.out.println(TextUtil.getEnd());
        sb.append("\n"+TextUtil.getEnd());

        return sb.toString();
    }
    public static boolean wirteFile(){
        //获取当前日期，并创建文件名
        String filedate = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String filepath = null;
        //根据系统环境选取文件存储路径
        switch (SystemUtil.JudgeSystem()){
            case "linux":{
                filepath = ResourceUtil.getKey("linux_filepath");
                break;
            }
            case "windows":{
                filepath = ResourceUtil.getKey("win_filepath");
                break;
            }
        }
        if (null == filepath || filepath.isEmpty()){
            return false;
        }
        //创建文件对象
        File file = new File(filepath + "/my"+filedate+".txt");
        if (!FileUtil.isEmpty(file)){
            FileUtil.del(file);
        }
        //获取file对象
        File touch = FileUtil.touch(file);
        FileWriter fileWriter = new FileWriter(touch);
        fileWriter.append(TextUtil.getText());
        return true;
    }
}
