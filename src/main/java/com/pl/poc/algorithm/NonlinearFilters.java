package com.pl.poc.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafał on 2017-08-20.
 */
public abstract class NonlinearFilters {
    private List<Integer> channelRed = new ArrayList<Integer>();
    private List<Integer> channelGreen = new ArrayList<Integer>();
    private List<Integer> channelBlue = new ArrayList<Integer>();

    public abstract BufferedImage execute(BufferedImage srcImage, int[] mat);

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
        mask[0] = 0;
        mask[column-1] = 0;
        mask[row*column-column] = 0;
        mask[row*column-1] = 0;
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
