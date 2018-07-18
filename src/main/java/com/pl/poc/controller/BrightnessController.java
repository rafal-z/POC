package com.pl.poc.controller;

import com.pl.poc.algorithm.ImageAlgorithms;
import com.pl.poc.view.BrightnessSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Rafał on 2017-07-15.
 */
public class BrightnessController implements ChangeListener {
    private MainView mainView;
    private BrightnessSettingsView bsView;

    public BrightnessController(MainView mainView, BrightnessSettingsView bs){
        this.mainView = mainView;
        this.bsView = bs;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        bsView.getSpiner().setValue(slider.getValue());
        try
        {
            if(mainView.getImagesModel() != null) {
                processImage(mainView.getImagesModel().getSrcImage(), mainView.getImagesModel().getDstImage(), slider.getValue());
                mainView.repaint();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void processImage(BufferedImage src, BufferedImage dst, int db)
    {
        DataBufferInt sbuff = (DataBufferInt) src.getRaster().getDataBuffer();
        DataBufferInt dbuff = (DataBufferInt) dst.getRaster().getDataBuffer();
        int[] sp = sbuff.getData();
        int[] dp = dbuff.getData();

        int i = 0;
        for (int y = 0; y < src.getHeight(); ++y)
        {
            for (int x = 0; x < src.getWidth(); ++x)
            {
                dp[i] = ImageAlgorithms.changeBrightness(sp[i], db);
                i++;
            }
        }

    }
}
