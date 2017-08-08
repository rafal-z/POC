package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.GaussSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussController implements ChangeListener {
    private MainView mView;
    private GaussSettingsView gsView;

    public GaussController(MainView mainView, GaussSettingsView gsView){
        this.mView = mainView;
        this.gsView = gsView;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        gsView.getNumberLabel().setText(slider.getValue() + "");
        if (!slider.getValueIsAdjusting() && mView.getImagesModel() != null) {
            Time.start();

            BufferedImage srcImage = mView.getImagesModel().getSrcImage();
            BufferedImage destImage = GaussAlgorithms.execute(srcImage, slider.getValue());
            mView.getImagesModel().setDstImage(destImage);
            mView.repaint();

            Time.stop();
        }
    }
}
