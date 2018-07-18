package com.pl.poc.model;

import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-07-15.
 */
public class ImagesModel {
    private BufferedImage srcImage;
    private BufferedImage dstImage;
    private BufferedImage orginalImage;
    private BufferedImage gaussImage;

    public ImagesModel(BufferedImage src, BufferedImage dsc){
        this.srcImage = src;
        this.dstImage = dsc;

        this.orginalImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        src.copyData(this.orginalImage.getRaster());
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

    public BufferedImage getGaussImage() {
        return gaussImage;
    }

    public void setGaussImage(BufferedImage gaussImage) {
        this.gaussImage = gaussImage;
    }
}
