package com.pl.poc.algorithm;

import javax.swing.*;

/**
 * Created by Rafał on 2017-08-06.
 */
public class Time {
    private static long startTime;

    public static void start(){
        startTime = System.currentTimeMillis();
    }

    public static void stop(JLabel timeLabel){
        timeLabel.setText("Czas operacji: " + (double) (System.currentTimeMillis() - startTime) / 1000 + "s");
    }
}
