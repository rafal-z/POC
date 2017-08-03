package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussAlgorithms;
import com.pl.poc.model.ImagesModel;
import com.pl.poc.view.GaussSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussController implements ChangeListener {
    private MainView mView;
    private GaussSettingsView gsView;

    public GaussController(MainView mainView, GaussSettingsView gsView){
        this.mView = mainView;
        this.gsView = gsView;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        gsView.getSpiner().setValue(slider.getValue());
        if (!slider.getValueIsAdjusting()) {
            long startTime = System.currentTimeMillis();

            ImagesModel model = mView.getImagesModel();
            model = GaussAlgorithms.execute(model, slider.getValue());
            mView.setImagesModel(model);
            mView.repaint();

            System.out.println("Czas operacji: "+ (double)(System.currentTimeMillis() - startTime)/1000 +"s"
                    +" - radius: "+ slider.getValue());
        }
    }
}
