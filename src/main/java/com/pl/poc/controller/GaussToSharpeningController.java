package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussianBlurAlgorithms;
import com.pl.poc.algorithm.SharpeningAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.SharpeningSettingsView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-07.
 */
public class GaussToSharpeningController implements ChangeListener {
    private MainView mView;
    private SharpeningSettingsView sharpView;

    public GaussToSharpeningController(MainView mainView, SharpeningSettingsView sharpeningSettingsView){
        this.mView = mainView;
        this.sharpView = sharpeningSettingsView;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        sharpView.getNumberGauss().setText(slider.getValue() + "");
        if (!slider.getValueIsAdjusting() && mView.getImagesModel() != null) {
            Time.start();

            BufferedImage srcImage = mView.getImagesModel().getSrcImage();
            BufferedImage gaussImage = GaussianBlurAlgorithms.execute(srcImage, slider.getValue());
            mView.getImagesModel().setGaussImage(gaussImage);
            BufferedImage dstImage = SharpeningAlgorithms.execute(srcImage, gaussImage, sharpView.getSharpeningSlider().getValue(), slider.getValue());
            mView.getImagesModel().setDstImage(dstImage);
            mView.repaint();

            Time.stop();
        }
    }
}
