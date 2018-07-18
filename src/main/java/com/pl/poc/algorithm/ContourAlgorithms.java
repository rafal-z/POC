package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.*;

/**
 * Created by Rafał on 2017-08-15.
 */
public class ContourAlgorithms {
    public static BufferedImage execute(int[] mat, int h, int w, BufferedImage srcImage){
        BufferedImage workImage;
        workImage = modify(mat, h, w, srcImage);
        workImage = contour(srcImage, workImage);
        return workImage;
    }

    private static BufferedImage modify(int[] mat, int h, int w, BufferedImage srcImage){
        int rgb;
        BufferedImage resultImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                rgb = erosion(y, x, mat, h, w, srcImage);
                resultImage.setRGB(x,y,rgb);
            }
        }
        return resultImage;
    }

    private static int erosion(int yr, int xr, int[] mat, int h, int w, BufferedImage srcImage){
        int[] colors = new int[3];
        int rgb;
        for(int y=yr-h/2, i=0; y<=yr+h/2; y++, i++){
            if(y>=0 && y<srcImage.getHeight()){
                for(int x=xr-w/2, j=0; x<=xr+w/2; x++, j++){
                    if(x>=0 && x<srcImage.getWidth() && (i*h+j)<mat.length && mat[i*h+j]==1){
                        rgb = srcImage.getRGB(x,y);
                        colors[0] = jred(rgb);
                        colors[1] = jgreen(rgb);
                        colors[2] = jblue(rgb);
                        if(colors[0]==255 || colors[1]==255 || colors[2]==255){
                            return jrgb(255,255,255);
                        }
                    }
                }
            }
        }
        rgb = srcImage.getRGB(xr,yr);
        return jrgb(jred(rgb), jgreen(rgb), jblue(rgb));
    }

    private static BufferedImage contour(BufferedImage srcImage, BufferedImage workImage){
        BufferedImage resultImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int srcRgb, workRgb;
        int[] colors = new int[3];
        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                srcRgb = srcImage.getRGB(x,y);
                workRgb = workImage.getRGB(x,y);
                colors[0] = jred(workRgb) - jred(srcRgb);
                colors[1] = jgreen(workRgb) - jgreen(srcRgb);
                colors[2] = jblue(workRgb) - jblue(srcRgb);
                for(int k=0; k<3; k++){
                    colors[k] = clamp(colors[k],0,255);
                }
                srcRgb = jrgb(colors[0], colors[1], colors[2]);
                resultImage.setRGB(x,y,srcRgb);
            }
        }
        return resultImage;
    }
}
