package com.pl.poc.controller;

import com.pl.poc.algorithm.MedianFilterAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.NonlinearFiltersSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-22.
 */
public class MedianFilterController implements ActionListener {
    private MainView mainView;
    private NonlinearFiltersSettingsView medianFilterView;

    public MedianFilterController(MainView mView, NonlinearFiltersSettingsView medianView){
        this.mainView = mView;
        this.medianFilterView = medianView;
    }


    public void actionPerformed(ActionEvent e) {
        Time.start();
        BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
        int[] mat;
        int radius = medianFilterView.getRadiusSlider().getValue();
        MedianFilterAlgorithms medianFilter = new MedianFilterAlgorithms();

        if(medianFilterView.getSquareMask().isSelected()) {
            mat = medianFilter.makeSquareMask(radius*2+1, radius*2+1);
        }
        else {
            mat = medianFilter.makeRoundMask(radius*2+1, radius*2+1);
        }
        BufferedImage workImage = medianFilter.execute(srcImage, mat);
        mainView.getImagesModel().setDstImage(workImage);
        mainView.repaint();
        Time.stop();
    }
}
