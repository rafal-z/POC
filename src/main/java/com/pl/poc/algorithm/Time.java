package com.pl.poc.algorithm;

/**
 * Created by Rafał on 2017-08-06.
 */
public class Time {
    private static long startTime;

    public static void start(){
        startTime = System.currentTimeMillis();
    }

    public static void stop(){
        System.out.println("Czas operacji: " + (double) (System.currentTimeMillis() - startTime) / 1000 + "s");
    }
}
