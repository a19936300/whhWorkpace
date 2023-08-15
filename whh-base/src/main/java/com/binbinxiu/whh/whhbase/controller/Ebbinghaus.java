package com.binbinxiu.whh.whhbase.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Ebbinghaus {

    public static List<Calendar> getForgettingTimes(Calendar startTime) {
        List<Calendar> forgettingTimes = new ArrayList<>();

        int[] intervals = {0, 5, 30, 12, 24, 7, 15};
        for (int interval : intervals) {
            Calendar currentTime = (Calendar) startTime.clone();
            forgettingTimes.add(currentTime);
            currentTime.add(Calendar.MINUTE, interval);
            //forgettingTimes.add(currentTime);
        }

        return forgettingTimes;
    }

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = instance.getTime();

        System.out.println("输入时间："+simpleDateFormat.format(instance.getTime()));

        List<Calendar> forgettingTimes = getForgettingTimes(instance);
        for (Calendar forgettingTime : forgettingTimes) {
            System.out.println("输出时间："+simpleDateFormat.format(forgettingTime.getTime()));
        }

    }
}
