package com.pl.poc.controller;

import com.pl.poc.algorithm.MinimumFilterAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.NonlinearFiltersSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-19.
 */
public class MinimumFilterController implements ActionListener {
    private MainView mainView;
    private NonlinearFiltersSettingsView nfsView;

    public MinimumFilterController(MainView mainView, NonlinearFiltersSettingsView nfsView){
        this.mainView = mainView;
        this.nfsView = nfsView;
    }

    public void actionPerformed(ActionEvent e) {
        Time.start();
        BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
        int[] mat;
        int radius = nfsView.getRadiusSlider().getValue();
        MinimumFilterAlgorithms minimumFilter = new MinimumFilterAlgorithms();

        if(nfsView.getSquareMask().isSelected()) {
            mat = minimumFilter.makeSquareMask(radius*2+1, radius*2+1);
        }
        else {
            mat = minimumFilter.makeRoundMask(radius*2+1, radius*2+1);
        }
        BufferedImage workImage = minimumFilter.execute(srcImage, mat);
        mainView.getImagesModel().setDstImage(workImage);
        mainView.repaint();
        Time.stop();
    }
}
