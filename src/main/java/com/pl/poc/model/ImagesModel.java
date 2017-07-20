package com.pl.poc.model;

import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-07-15.
 */
public class ImagesModel {
    private BufferedImage orginalImage;
    private BufferedImage srcImage;
    private BufferedImage dstImage;

    public ImagesModel(BufferedImage src, BufferedImage dsc){
        this.srcImage = src;
        this.dstImage = dsc;
    }

    public BufferedImage getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(BufferedImage srcImage) {
        this.srcImage = srcImage;
    }

    public BufferedImage getDstImage() {
        return dstImage;
    }

    public void setDstImage(BufferedImage dstImage) {
        this.dstImage = dstImage;
    }

    public BufferedImage getOrginalImage() {
        return orginalImage;
    }

    public void setOrginalImage(BufferedImage orginalImage) {
        this.orginalImage = orginalImage;
    }
}
