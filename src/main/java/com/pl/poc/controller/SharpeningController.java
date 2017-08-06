package com.pl.poc.controller;

import com.pl.poc.algorithm.SharpeningAlgorithms;
import com.pl.poc.model.ImagesModel;
import com.pl.poc.view.MainView;
import com.pl.poc.view.SharpeningSettingsView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningController implements ChangeListener{
    private MainView mView;
    private SharpeningSettingsView sharpView;

    public SharpeningController(MainView mainView, SharpeningSettingsView sharpeningSettingsView){
        mView = mainView;
        sharpView = sharpeningSettingsView;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();

        int radius = sharpView.getGaussSlider().getValue();
        int unsharp = sharpView.getSharpeningSlider().getValue();
        sharpView.getNumberGauss().setText(radius+"");
        sharpView.getNumberSharpening().setText(unsharp+"");

        if(!slider.getValueIsAdjusting()){
            if(mView.getImagesModel() != null) {
                long startTime = System.currentTimeMillis();

                ImagesModel model = mView.getImagesModel();
                model = SharpeningAlgorithms.execute(model, unsharp, radius);
                mView.setImagesModel(model);
                mView.repaint();

                System.out.println("Czas operacji: " + (double) (System.currentTimeMillis() - startTime) / 1000 + "s");
            }
        }
    }
}
