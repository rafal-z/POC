package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.pl.poc.algorithm.ImageAlgorithms.*;

/**
 * Created by Rafał on 2017-08-29.
 */
public class AdaptiveMedianAlgorithms {
    private List<Float> channelRed = new ArrayList<Float>();
    private List<Float> channelGreen = new ArrayList<Float>();
    private List<Float> channelBlue = new ArrayList<Float>();

    private float min;
    private float max;
    private float median;

    public BufferedImage execute(int size, int sizeMax, int shapeMask, BufferedImage srcImage){
        int rgb;
        BufferedImage workImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        ColorFromRgb commandRed = new ColorFromRgb() {
            @Override
            public int execute(int rgb) {
                return jred(rgb);
            }
        };

        ColorFromRgb commandGreen = new ColorFromRgb() {
            @Override
            public int execute(int rgb) {
                return jgreen(rgb);
            }
        };

        ColorFromRgb commandBlue = new ColorFromRgb() {
            @Override
            public int execute(int rgb) {
                return jblue(rgb);
            }
        };

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                rgb = jrgb(
                        stepA(x,y,size,sizeMax,channelRed,commandRed, srcImage, shapeMask),
                        stepA(x,y,size,sizeMax,channelGreen,commandGreen, srcImage, shapeMask),
                        stepA(x,y,size,sizeMax,channelBlue,commandBlue, srcImage, shapeMask)
                );
                workImage.setRGB(x,y,rgb);
            }
        }
        return workImage;
    }

    private int stepA(int x, int y, int size, int sizeMax, List<Float> list, ColorFromRgb colorFromRgb, BufferedImage srcImage, int shapeMask){
        float a1, a2;

        list = findNeighbors(y,x,size,list,colorFromRgb, srcImage, shapeMask);
        if(list.size() != 0) {
            max = Collections.max(list);
            min = Collections.min(list);
            median = (float) MedianFilterAlgorithms.median(list, Comparator.naturalOrder());
        }
        else {
            max=0;
            min=0;
            median=0;
        }

        a1 = median - min;
        a2 = median - max;

        if(a1>0 && a2<0)
            return stepB(x,y,srcImage, colorFromRgb);
        else
            size += 2;

        if(size <= sizeMax)
            return stepA(x,y,size,sizeMax,list,colorFromRgb, srcImage, shapeMask);
        else
            return (int)median;
    }

    private int stepB(int x, int y, BufferedImage srcImage, ColorFromRgb colorFromRgb){
        float b1, b2;
        int rgb;

        rgb = srcImage.getRGB(x,y);
        b1 = colorFromRgb.execute(rgb) - min;
        b2 = colorFromRgb.execute(rgb) - max;

        if(b1>0 && b2<0)
            return colorFromRgb.execute(rgb);
        else
            return (int)median;
    }

    private List<Float> findNeighbors(int yr, int xr, int size, List<Float> list, ColorFromRgb colorFromRgb, BufferedImage srcImage, int shapeMask){
        list.clear();
        int[] mask = (shapeMask == 1) ? NonlinearFilters.makeSquareMask(size, size) : NonlinearFilters.makeRoundMask(size,size);
        int rgb;

        for(int y=yr-size/2, i=0; y<=yr+size/2; y++, i++) {
            if(y>=0 && y<srcImage.getHeight()) {
                for(int x=xr-size/2, j=0; x<=xr+size/2; x++, j++) {
                    if(x>=0 && x<srcImage.getWidth()) {
                        if(mask[i*size+j] == 1){
                            rgb = srcImage.getRGB(x,y);
                            list.add((float)colorFromRgb.execute(rgb));
                        }
                    }
                }
            }
        }
        return list;
    }
}
