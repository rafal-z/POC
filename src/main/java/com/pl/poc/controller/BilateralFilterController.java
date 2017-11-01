package com.pl.poc.controller;

import com.pl.poc.algorithm.BilateralFilterAlgorithm;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.BilateralFilterSettingsView;
import com.pl.poc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-09-02.
 */
public class BilateralFilterController implements ActionListener {
    private MainView mainView;
    private BilateralFilterSettingsView bilateralView;

    public BilateralFilterController(MainView mainView, BilateralFilterSettingsView bView){
        this.mainView = mainView;
        this.bilateralView = bView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainView.getImagesModel() != null) {
            Time.start();
            BufferedImage workImage = mainView.getImagesModel().getSrcImage();
            float distanceSigma = (float) bilateralView.getSigmaMinSpinner().getValue();
            float intensitySigma = (float) bilateralView.getSigmaMaxSpinner().getValue();
            workImage = new BilateralFilterAlgorithm().execute(workImage, distanceSigma, intensitySigma);
            mainView.getImagesModel().setDstImage(workImage);
            mainView.repaint();
            Time.stop(mainView.getTimeLabel());
        }
    }
}
