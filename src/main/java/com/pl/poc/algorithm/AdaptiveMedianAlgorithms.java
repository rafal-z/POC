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
        Command2 commandRed = new Command2() {
            @Override
            public int runCommand(int rgb) {
                return jred(rgb);
            }
        };

        Command2 commandGreen = new Command2() {
            @Override
            public int runCommand(int rgb) {
                return jgreen(rgb);
            }
        };

        Command2 commandBlue = new Command2() {
            @Override
            public int runCommand(int rgb) {
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

    private int stepA(int x, int y, int size, int sizeMax, List<Float> list, Command2 command, BufferedImage srcImage, int shapeMask){
        float a1, a2;

        list = findNeighbors(y,x,size,list,command, srcImage, shapeMask);
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
            return stepB(x,y,srcImage, command);
        else
            size += 2;

        if(size <= sizeMax)
            return stepA(x,y,size,sizeMax,list,command, srcImage, shapeMask);
        else
            return (int)median;
    }

    private int stepB(int x, int y, BufferedImage srcImage, Command2 command2){
        float b1, b2;
        int rgb;

        rgb = srcImage.getRGB(x,y);
        b1 = command2.runCommand(rgb) - min;
        b2 = command2.runCommand(rgb) - max;

        if(b1>0 && b2<0)
            return command2.runCommand(rgb);
        else
            return (int)median;
    }

    private List<Float> findNeighbors(int yr, int xr, int size, List<Float> list, Command2 command, BufferedImage srcImage, int shapeMask){
        list.clear();
        int[] mask = (shapeMask == 1) ? NonlinearFilters.makeSquareMask(size, size) : NonlinearFilters.makeRoundMask(size,size);
        int rgb;

        for(int y=yr-size/2, i=0; y<=yr+size/2; y++, i++) {
            if(y>=0 && y<srcImage.getHeight()) {
                for(int x=xr-size/2, j=0; x<=xr+size/2; x++, j++) {
                    if(x>=0 && x<srcImage.getWidth()) {
                        if(mask[i*size+j] == 1){
                            rgb = srcImage.getRGB(x,y);
                            list.add((float)command.runCommand(rgb));
                        }
                    }
                }
            }
        }
        return list;
    }
}
