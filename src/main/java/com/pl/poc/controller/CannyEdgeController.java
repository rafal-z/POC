package com.pl.poc.controller;

import com.pl.poc.algorithm.CannyEdgeDetectorAlgorithm;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.CannyEdgeSettingsView;
import com.pl.poc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-09-06.
 */
public class CannyEdgeController implements ActionListener{
    private MainView mainView;
    private CannyEdgeSettingsView cannyView;

    public CannyEdgeController(MainView mainView, CannyEdgeSettingsView view){
        this.mainView = mainView;
        this.cannyView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainView.getImagesModel() != null) {
            Time.start();
            BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
            double low = (double) cannyView.getLowThresholdSlider().getValue();
            double hight = (double) cannyView.getHighThresholdSlider().getValue();
            int radius = cannyView.getRadiusSlider().getValue();
            CannyEdgeDetectorAlgorithm canny = new CannyEdgeDetectorAlgorithm();
            BufferedImage workImage = canny.execute(srcImage, low, hight, radius);
            mainView.getImagesModel().setDstImage(workImage);
            mainView.repaint();

            Time.stop(mainView.getTimeLabel());
        }
    }
}
