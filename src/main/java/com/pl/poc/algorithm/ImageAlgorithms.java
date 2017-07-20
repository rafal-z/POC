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

    // Funkcja zmieniajaca jasnosc koloru rgb o wartosc db
    public static int changeBrightness(int rgb, int db)
    {
        // Rozbior koloru na skladowe R, G, B
        int r = jred(rgb);
        int g = jgreen(rgb);
        int b = jblue(rgb);

        // Modyfikacja skladowych razem z przypilnowaniem zakresow 0-255
        r = clamp(r + db, 0, 255);
        g = clamp(g + db, 0, 255);
        b = clamp(b + db, 0, 255);

        // Zlozenie skladowych R, G, B w jeden kolor i zwrocenie jego wartosci
        return jrgb(r, g, b);
    }

    public static int changeContrast(int rgb, int dc) {return 0;}
    public static int rgb2hsl(int rgb) {return 0;}
    public static int hsl2rgb(int hsl) {return 0;}
}
