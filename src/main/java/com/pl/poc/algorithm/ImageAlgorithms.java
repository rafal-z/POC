package com.pl.poc.algorithm;

/**
 * Created by Rafał on 2017-07-13.
 */
public class ImageAlgorithms {
    public static int jrgb(int r, int g, int b) { return (r << 16) + (g << 8) + b; }

    public static int jred(int rgb)   { return ((rgb >> 16) & 0xff); }
    public static int jgreen(int rgb) { return ((rgb >> 8) & 0xff); }
    public static int jblue(int rgb)  { return (rgb & 0xff); }

    public static int clamp(int v, int min, int max)
    {
        if (v <= min) return min;
        if (v >= max) return max;
        return v;
    }
}
