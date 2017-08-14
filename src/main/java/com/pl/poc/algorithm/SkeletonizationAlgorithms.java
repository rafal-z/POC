package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;

import static com.pl.poc.algorithm.ImageAlgorithms.jred;
import static com.pl.poc.algorithm.ImageAlgorithms.jrgb;

/**
 * Created by Rafał on 2017-08-10.
 */
public class SkeletonizationAlgorithms {

    public static BufferedImage execute(BufferedImage srcImage){
        BufferedImage workImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage prevImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage currImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int rgb;
        srcImage.copyData(currImage.getRaster());

        do {
            for(int y=0; y<srcImage.getHeight(); y++){
                for(int x=0; x<srcImage.getWidth(); x++){
                    rgb = hitOrMiss(y,x,currImage);
                    workImage.setRGB(x,y,rgb);
                }
            }
            currImage.copyData(prevImage.getRaster());
            currImage = thinnig(currImage, workImage);
        }while(!imagesAreEquals(currImage, prevImage));

        return currImage;
    }

    private static boolean imagesAreEquals(BufferedImage a, BufferedImage b){
        if(a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()){
            return false;
        }

        for(int y=0; y<a.getHeight(); y++){
            for(int x=0; x<a.getWidth(); x++){
                if(a.getRGB(x,y) != b.getRGB(x,y)){
                    return false;
                }
            }
        }

        return true;
    }

    public static int hitOrMiss(int yr, int xr, BufferedImage srcImage){
        int[][] mat =  new int[][]{
                {2,255,2,
                 2,0,2,
                 0,0,0},

                {2,2,0,
                 255,0,0,
                 2,2,0},

                {0,0,0,
                 2,0,2,
                 2,255,2},

                {0,2,2,
                 0,0,255,
                 0,2,2}
        };

        int ROWS = 4;
        int count = 0;
        int halfSizeMat = 3;
        int[] colors = new int[3];
        int rgb;
        int pixel;

        for(int m=0; m<ROWS; m++){
            for(int y=yr-halfSizeMat/2, i=0; y<=yr+halfSizeMat/2; y++, i++){
                if(y>=0 && y<srcImage.getHeight()){
                    for(int x=xr-halfSizeMat/2, j=0; x<=xr+halfSizeMat/2; x++, j++){
                        if(x>=0 && x<srcImage.getWidth() && mat[m][i*halfSizeMat+j]!=2){
                            rgb = srcImage.getRGB(x,y);
                            colors[0]=ImageAlgorithms.jred(rgb);
                            colors[1]=ImageAlgorithms.jgreen(rgb);
                            colors[2]=ImageAlgorithms.jblue(rgb);
                            pixel = mat[m][i*halfSizeMat+j];
                            if(colors[0] != pixel && colors[1] != pixel && colors[2] != pixel){
                                count++;
                                y=(yr+halfSizeMat)+1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return count==ROWS ? jrgb(255,255,255) : jrgb(0,0,0);
    }

    public static BufferedImage thinnig(BufferedImage currImage, BufferedImage difImage){
        BufferedImage resultImage = new BufferedImage(difImage.getWidth(), difImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        int difRgb, currRgb;

        for(int y=0; y<difImage.getHeight(); y++){
            for(int x=0; x<difImage.getWidth(); x++){
                difRgb = difImage.getRGB(x,y);
                currRgb = currImage.getRGB(x,y);
                if(jred(currRgb) == 0 && jred(difRgb) == 255)
                    resultImage.setRGB(x,y,jrgb(0,0,0));
                else
                    resultImage.setRGB(x,y,jrgb(255,255,255));
            }
        }
        return resultImage;
    }
}
