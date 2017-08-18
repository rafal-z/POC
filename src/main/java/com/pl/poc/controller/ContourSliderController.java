package com.pl.poc.controller;

import com.pl.poc.view.ContourSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Rafał on 2017-08-15.
 */
public class ContourSliderController implements ChangeListener {
    private MainView mainView;
    private ContourSettingsView csView;

    public ContourSliderController(MainView mView, ContourSettingsView contourSettingsView){
        this.mainView = mView;
        this.csView = contourSettingsView;
    }

    public void stateChanged(ChangeEvent e) {
        csView.resizeTable(csView.getVerticallySlider().getValue()*2+1, csView.getHorizontallySlider().getValue()*2+1);
    }
}
