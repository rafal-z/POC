package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.Collections;

import static com.pl.poc.algorithm.ImageAlgorithms.jrgb;

/**
 * Created by Rafał on 2017-08-19.
 */
public class MinimumFilterAlgorithms extends NonlinearFilters {
    public BufferedImage execute(BufferedImage srcImage, int[] mat){
        BufferedImage workImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                super.findNeighbors(x,y,mat,srcImage);
                workImage.setRGB(x, y, jrgb(
                        Collections.min(super.getChannelRed()),
                        Collections.min(super.getChannelGreen()),
                        Collections.min(super.getChannelBlue())
                ));
            }
        }
        return workImage;
    }
}
