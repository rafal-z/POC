package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussianBlurAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.GaussSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussController implements ChangeListener {
    private MainView mainView;
    private GaussSettingsView gsView;

    public GaussController(MainView mainView, GaussSettingsView gsView){
        this.mainView = mainView;
        this.gsView = gsView;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        gsView.getNumberLabel().setText(slider.getValue() + "");
        if (!slider.getValueIsAdjusting() && mainView.getImagesModel() != null) {
            Time.start();

            BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
            List<List<Double>> matrixGauss = GaussianBlurAlgorithms.makeMatrixGauss(slider.getValue());
            BufferedImage destImage = GaussianBlurAlgorithms.execute(srcImage, matrixGauss);
            mainView.getImagesModel().setDstImage(destImage);
            mainView.repaint();

            Time.stop(mainView.getTimeLabel());
        }
    }
}
