package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.pl.poc.algorithm.ImageAlgorithms.jrgb;

/**
 * Created by Rafał on 2017-08-22.
 */
public class MedianFilterAlgorithms extends NonlinearFilters {

    public BufferedImage execute(BufferedImage srcImage, int[] mat) {
        BufferedImage workImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Comparator<Integer> comp = Comparator.naturalOrder();

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                super.findNeighbors(x,y,mat,srcImage);
                workImage.setRGB(x, y, jrgb(
                        (int)median(super.getChannelRed(), comp),
                        (int)median(super.getChannelGreen(), comp),
                        (int)median(super.getChannelBlue(), comp)
                ));
            }
        }
        return workImage;
    }

    public static <T extends Number> double median(List<T> coll, Comparator<T> comp) {
        double result;
        int n = coll.size()/2;

        if (coll.size() % 2 == 0)
            result = (nth(coll, n-1, comp).doubleValue() + nth(coll, n, comp).doubleValue()) / 2.0;
        else
            result = nth(coll, n, comp).doubleValue();

        return result;
    }


    public static <T> T nth(List<T> coll, int n, Comparator<T> comp) {
        T result, pivot;
        ArrayList<T> underPivot = new ArrayList<>(), overPivot = new ArrayList<>(), equalPivot = new ArrayList<>();

        pivot = coll.get(n/2);

        for (T obj : coll) {
            int order = comp.compare(obj, pivot);

            if (order < 0)
                underPivot.add(obj);
            else if (order > 0)
                overPivot.add(obj);
            else
                equalPivot.add(obj);
        }

        if (n < underPivot.size())
            result = nth(underPivot, n, comp);
        else if (n < underPivot.size() + equalPivot.size())
            result = pivot;
        else
            result = nth(overPivot, n - underPivot.size() - equalPivot.size(), comp);

        return result;
    }
}
