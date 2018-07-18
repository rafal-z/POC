package com.pl.poc.controller;

import com.pl.poc.algorithm.ContourAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.ContourSettingsView;
import com.pl.poc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-15.
 */
public class ContourButtonController implements ActionListener {
    private MainView mainView;
    private ContourSettingsView csView;

    public ContourButtonController(MainView mView, ContourSettingsView contourSettingsView){
        this.mainView = mView;
        this.csView = contourSettingsView;
    }

    public void actionPerformed(ActionEvent e) {
        if(mainView.getImagesModel() != null) {
            Time.start();
            int[] mat = csView.getValuesWithTable();
            JTable table = csView.getTable();
            BufferedImage workImage = mainView.getImagesModel().getSrcImage();
            workImage = ContourAlgorithms.execute(mat, table.getRowCount(), table.getColumnCount(), workImage);
            mainView.getImagesModel().setDstImage(workImage);
            mainView.repaint();
            Time.stop(mainView.getTimeLabel());
        }
    }
}
