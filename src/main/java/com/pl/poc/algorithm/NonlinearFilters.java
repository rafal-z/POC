package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.pl.poc.algorithm.ImageAlgorithms.jrgb;

/**
 * Created by Rafał on 2017-08-20.
 */
public abstract class NonlinearFilters {
    private List<Integer> channelRed = new ArrayList<Integer>();
    private List<Integer> channelGreen = new ArrayList<Integer>();
    private List<Integer> channelBlue = new ArrayList<Integer>();

    public BufferedImage execute(BufferedImage srcImage, int[] mat, ElementFromList elementFromList){
        BufferedImage workImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int y=0; y<srcImage.getHeight(); y++){
            for(int x=0; x<srcImage.getWidth(); x++){
                findNeighbors(x,y,mat,srcImage);
                workImage.setRGB(x, y, jrgb(
                        elementFromList.execute(getChannelRed()),
                        elementFromList.execute(getChannelGreen()),
                        elementFromList.execute(getChannelBlue())
                ));
            }
        }
        return workImage;
    }

    protected void findNeighbors(int xr, int yr, int[] mat, BufferedImage srcImage){
        channelRed.clear();
        channelGreen.clear();
        channelBlue.clear();
        int sizeMask = (int)Math.sqrt(mat.length);
        int rgb;

        for(int y=yr-sizeMask/2, i=0; y<=yr+sizeMask/2; y++, i++){
            if(y>=0 && y<srcImage.getHeight()){
                for(int x=xr-sizeMask/2, j=0; x<=xr+sizeMask/2; x++, j++){
                    if(x>=0 && x<srcImage.getWidth()){
                        if((i*sizeMask+j)<mat.length && mat[i*sizeMask+j] == 1){
                            rgb = srcImage.getRGB(x,y);
                            channelRed.add(ImageAlgorithms.jred(rgb));
                            channelGreen.add(ImageAlgorithms.jgreen(rgb));
                            channelBlue.add(ImageAlgorithms.jblue(rgb));
                        }
                    }
                }
            }
        }
    }

    public static int[] makeSquareMask(int row, int column){
        int[] mat = new int[row*column];
        for(int i=0; i<row*column; i++) {
            mat[i] = 1;
        }
        return mat;
    }

    public static int[] makeRoundMask(int row, int column){
        int[] mask = makeSquareMask(row, column);

        // Bez warunku w instrukcji if() przy radius=0,
        // maska jest tablicą jednoelementową z wartością ZERO.
        // Wtedy pixel nie ma żednego sąsiedztwa, a lista z kanałami kolorów jest pusta.
        // W wyniku tego Collections.min() i max() rzucają wyjątek NoSuchElementException.
        if(mask.length >= 3) {
            mask[0] = 0;
            mask[column - 1] = 0;
            mask[row * column - column] = 0;
            mask[row * column - 1] = 0;
        }
        return mask;
    }

    public List<Integer> getChannelRed() {
        return channelRed;
    }

    public List<Integer> getChannelGreen() {
        return channelGreen;
    }

    public List<Integer> getChannelBlue() {
        return channelBlue;
    }
}
