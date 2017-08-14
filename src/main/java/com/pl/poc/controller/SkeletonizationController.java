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
    MainView mView;

    public SkeletonizationController(MainView mainView){
        mView = mainView;
    }

    public void actionPerformed(ActionEvent e) {
        Time.start();
        BufferedImage workImage = mView.getImagesModel().getSrcImage();
        workImage = SkeletonizationAlgorithms.execute(workImage);
        mView.getImagesModel().setDstImage(workImage);
        mView.repaint();
        Time.stop();
    }
}
