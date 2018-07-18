package com.pl.poc.controller;

import com.pl.poc.model.ImagesModel;
import com.pl.poc.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafał on 2017-07-16.
 */
public class ResetImageController implements ActionListener {
    private MainView mainView;

    public ResetImageController(MainView mView){this.mainView = mView;}

    public void actionPerformed(ActionEvent e) {
        ImagesModel model = mainView.getImagesModel();
        if(model != null) {
            model.getOrginalImage().copyData(model.getDstImage().getRaster());
            model.getOrginalImage().copyData(model.getSrcImage().getRaster());
            mainView.setImagesModel(model);
            mainView.repaint();
        }
    }
}
