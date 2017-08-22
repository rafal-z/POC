package com.pl.poc.controller;

import com.pl.poc.algorithm.MaximumFilterAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.NonlinearFiltersSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-21.
 */
public class MaximumFilterController implements ActionListener {
    private MainView mainView;
    private NonlinearFiltersSettingsView maximumFilterView;

    public MaximumFilterController(MainView mView, NonlinearFiltersSettingsView mfView){
        this.mainView = mView;
        this.maximumFilterView = mfView;
    }

    public void actionPerformed(ActionEvent e) {
        Time.start();
        BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
        int[] mat;
        int radius = maximumFilterView.getRadiusSlider().getValue();
        MaximumFilterAlgorithms maximumFilter = new MaximumFilterAlgorithms();

        if(maximumFilterView.getSquareMask().isSelected()) {
            mat = maximumFilter.makeSquareMask(radius*2+1, radius*2+1);
        }
        else {
            mat = maximumFilter.makeRoundMask(radius*2+1, radius*2+1);
        }
        BufferedImage workImage = maximumFilter.execute(srcImage, mat);
        mainView.getImagesModel().setDstImage(workImage);
        mainView.repaint();
        Time.stop();
    }
}
