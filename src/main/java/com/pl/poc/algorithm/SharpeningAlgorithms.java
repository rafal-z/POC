package com.pl.poc.algorithm;

import com.pl.poc.model.ImagesModel;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.*;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningAlgorithms {

    public static ImagesModel execute(ImagesModel model, int sharpeningValue, int radius){
        model = GaussAlgorithms.execute(model, radius);
        model = sharpening(model, sharpeningValue);
        return model;
    }

    public static ImagesModel sharpening(ImagesModel model, int sharpeningValue){
        int[] colors = new int[3];
        int srcRgb, dstRgb;

        BufferedImage srcImage = model.getSrcImage();
        BufferedImage dstImage = model.getDstImage();

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                srcRgb = srcImage.getRGB(x, y);
                dstRgb = dstImage.getRGB(x, y);
                colors[0] = jred(srcRgb) + (jred(srcRgb) - jred(dstRgb)) * sharpeningValue;
                colors[1] = jgreen(srcRgb) + (jgreen(srcRgb) - jgreen(dstRgb)) * sharpeningValue;
                colors[2] = jblue(srcRgb) + (jblue(srcRgb) - jblue(dstRgb)) * sharpeningValue;

                for(int k=0; k<3; k++){
                    colors[k] = ImageAlgorithms.clamp(colors[k], 0, 255);
                }

                dstImage.setRGB(x, y, jrgb(colors[0], colors[1], colors[2]));
            }
        }

        model.setDstImage(dstImage);
        return model;
    }
}
