package com.pl.poc.controller;

import com.pl.poc.algorithm.AdaptiveMedianAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.AdaptiveMedianSettingsView;
import com.pl.poc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-30.
 */
public class AdaptiveMedianController implements ActionListener{

    private MainView mainView;
    private AdaptiveMedianSettingsView adaptiveView;

    public AdaptiveMedianController(MainView mView, AdaptiveMedianSettingsView view){
        this.mainView = mView;
        this.adaptiveView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Time.start();
        BufferedImage workImage = mainView.getImagesModel().getSrcImage();
        AdaptiveMedianAlgorithms adaptiveFilters = new AdaptiveMedianAlgorithms();
        int shapeMask = (adaptiveView.getSquareMaskRB().isSelected()) ? 1 : 2;
        int sizeMax = adaptiveView.getRadiusMaxSlider().getValue()*2+1;
        int sizeMin = adaptiveView.getRadiusMinSlider().getValue()*2+1;
        workImage = adaptiveFilters.execute(sizeMin, sizeMax, shapeMask, workImage);
        mainView.getImagesModel().setDstImage(workImage);
        mainView.repaint();
        Time.stop(mainView.getTimeLabel());
    }
}
