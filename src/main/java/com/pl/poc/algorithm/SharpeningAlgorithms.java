package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.*;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningAlgorithms {

    public static BufferedImage execute(BufferedImage srcImage, BufferedImage gaussImage, int sharpeningValue, int radius){
        return sharpening(srcImage, gaussImage, sharpeningValue);
    }

    public static BufferedImage sharpening(BufferedImage srcImage, BufferedImage gaussImage, int sharpeningValue){
        int[] colors = new int[3];
        int srcRgb, gaussRgb;

        BufferedImage dstImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                srcRgb = srcImage.getRGB(x, y);
                gaussRgb = gaussImage.getRGB(x, y);
                colors[0] = jred(srcRgb) + (jred(srcRgb) - jred(gaussRgb)) * sharpeningValue;
                colors[1] = jgreen(srcRgb) + (jgreen(srcRgb) - jgreen(gaussRgb)) * sharpeningValue;
                colors[2] = jblue(srcRgb) + (jblue(srcRgb) - jblue(gaussRgb)) * sharpeningValue;

                for(int k=0; k<3; k++){
                    colors[k] = ImageAlgorithms.clamp(colors[k], 0, 255);
                }
                dstImage.setRGB(x, y, jrgb(colors[0], colors[1], colors[2]));
            }
        }
        return dstImage;
    }
}
