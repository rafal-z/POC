package com.pl.poc.controller;

import com.pl.poc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafał on 2017-07-16.
 */
public class UpdateController implements ActionListener {
    private MainView mainView;
    private JFrame currentView;

    public UpdateController(MainView mainView, JFrame currentView){
        this.mainView = mainView;
        this.currentView = currentView;
    }

    public void actionPerformed(ActionEvent e) {
        try
        {
            mainView.getImagesModel().getDstImage().copyData(mainView.getImagesModel().getSrcImage().getRaster());
            mainView.getImageIcon().setImage(mainView.getImagesModel().getDstImage());
            mainView.getImageLabel().setIcon(mainView.getImageIcon());

            currentView.setVisible(false);
            currentView.dispose();
        } catch (Exception ex) {}
    }
}
