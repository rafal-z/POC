package com.pl.poc.controller;

import com.pl.poc.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveImageController implements ActionListener{
    MainView mainView;

    public SaveImageController(MainView mView){
        this.mainView = mView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser("/media/rafal/Dane/Dysk Google/_UMCS/SEMESTR II/POC/Images");
        fc.addChoosableFileFilter(new FileNameExtensionFilter("jpg (*.jpg)", "jpg", "jpeg"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("png (*.png)", "png", "png"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("gif (*.gif)", "gif", "gif"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("bmp (*.bmp)", "bmp", "bmp"));

        int retrieval = fc.showSaveDialog(null);

        if(retrieval == JFileChooser.APPROVE_OPTION){
            File outputFile = new File(fc.getSelectedFile().getPath());
            try{
                BufferedImage imageToSave = mainView.getImagesModel().getDstImage();
                String extension = getExtension(fc);
                ImageIO.write(imageToSave, extension, outputFile);
            }catch (Exception exc){
                System.out.println("Image save error: " + exc.getMessage());
            }
        }
    }

    private String getExtension(JFileChooser fileChooser){
        if(fileChooser.getFileFilter().getDescription() == "jpg (*.jpg)"){
            return "jpg";
        }
        if(fileChooser.getFileFilter().getDescription() == "png (*.png)"){
            return "png";
        }
        if(fileChooser.getFileFilter().getDescription() == "gif (*.gif)"){
            return "gif";
        }
        if(fileChooser.getFileFilter().getDescription() == "bmp (*.bmp)"){
            return "bmp";
        }
        else {
            return "jpg";
        }
    }
}
