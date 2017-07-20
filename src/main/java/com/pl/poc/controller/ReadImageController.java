package com.pl.poc.controller;

import com.pl.poc.model.ImagesModel;
import com.pl.poc.view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Rafał on 2017-07-16.
 */
public class ReadImageController implements ActionListener {
    private MainView mainView;

    public ReadImageController(MainView mainView){
        this.mainView = mainView;
    }

    public void actionPerformed(ActionEvent e) {
//        JFileChooser fc = new JFileChooser();
        JFileChooser fc = new JFileChooser("F:\\Dysk Google\\_UMCS\\SEMESTR II\\POC\\Images");
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            String fname = file.getPath();
            readImage(fname);
            mainView.repaint();
        }
    }

    public void readImage(String fileName)
    {
        try
        {
            BufferedImage originalImage = null;
            BufferedImage workImage = null;

            BufferedImage loadImage = ImageIO.read(new File(fileName));

            // Oryginalny obraz tworzony na podstawie wczytanego z ewentualna konwersja obrazu do formatu 32 bit RGB
            originalImage = new BufferedImage(loadImage.getWidth(), loadImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            originalImage.getGraphics().drawImage(loadImage, 0, 0, null);

            workImage = new BufferedImage(loadImage.getWidth(), loadImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            originalImage.copyData(workImage.getRaster());

            ImageIcon imageIcon = new ImageIcon(workImage);
            mainView.setImageIcon(imageIcon);
            mainView.getImageLabel().setIcon(imageIcon);

            mainView.setImagesModel(new ImagesModel(originalImage, workImage));

        } catch (Exception e)
        {
            System.out.println("Image read error: " + e.getMessage());
        }
    }
}
