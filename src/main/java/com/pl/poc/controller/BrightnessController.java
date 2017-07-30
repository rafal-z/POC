package com.pl.poc.controller;

import com.pl.poc.algorithm.BrightnessAlgorithms;
import com.pl.poc.model.ImagesModel;
import com.pl.poc.view.BrightnessSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Rafał on 2017-07-15.
 */
public class BrightnessController implements ChangeListener {
    private MainView mView;
    private BrightnessSettingsView bsView;

    public BrightnessController(MainView mainView, BrightnessSettingsView bs){
        this.mView = mainView;
        this.bsView = bs;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        bsView.getSpiner().setValue(slider.getValue());
        try
        {
            ImagesModel imagesModel = BrightnessAlgorithms.execute(mView.getImagesModel(), slider.getValue());
            mView.setImagesModel(imagesModel);
            mView.repaint();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
