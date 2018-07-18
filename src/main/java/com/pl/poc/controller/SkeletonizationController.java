package com.pl.poc.controller;

import com.pl.poc.algorithm.SkeletonizationAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-12.
 */
public class SkeletonizationController implements ActionListener {
    MainView mainView;

    public SkeletonizationController(MainView mainView){
        this.mainView = mainView;
    }

    public void actionPerformed(ActionEvent e) {
        if(mainView.getImagesModel() != null) {
            Time.start();
            BufferedImage workImage = mainView.getImagesModel().getSrcImage();
            workImage = SkeletonizationAlgorithms.execute(workImage);
            mainView.getImagesModel().setDstImage(workImage);
            mainView.repaint();
            Time.stop(mainView.getTimeLabel());
        }
    }
}
