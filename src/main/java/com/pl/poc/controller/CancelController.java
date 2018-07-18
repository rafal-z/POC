package com.pl.poc.controller;

import com.pl.poc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafał on 2017-07-16.
 */
public class CancelController implements ActionListener {
    private MainView mainView;
    private JFrame currentView;

    public CancelController(MainView mainView, JFrame currentView){
        this.mainView = mainView;
        this.currentView = currentView;
    }

    public void actionPerformed(ActionEvent e) {
        try
        {
            if(mainView.getImagesModel() != null) {
                mainView.getImagesModel().getSrcImage().copyData(mainView.getImagesModel().getDstImage().getRaster());
                mainView.getImageIcon().setImage(mainView.getImagesModel().getDstImage());
                mainView.getImageLabel().setIcon(mainView.getImageIcon());
                mainView.getImageLabel().repaint();
            }

            currentView.setVisible(false);
            currentView.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
