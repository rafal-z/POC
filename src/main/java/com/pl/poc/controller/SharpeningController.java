package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussianBlurAlgorithms;
import com.pl.poc.algorithm.SharpeningAlgorithms;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.SharpeningSettingsView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningController implements ChangeListener{
    private MainView mainView;
    private SharpeningSettingsView sharpView;

    public SharpeningController(MainView mainView, SharpeningSettingsView sharpeningSettingsView){
        this.mainView = mainView;
        sharpView = sharpeningSettingsView;
    }

    public void stateChanged(ChangeEvent e) {
        int radius = sharpView.getGaussSlider().getValue();
        int unsharp = sharpView.getSharpeningSlider().getValue();
        sharpView.getNumberGauss().setText(radius + "");
        sharpView.getNumberSharpening().setText(unsharp + "");

        if (mainView.getImagesModel() != null) {
            Time.start();

            BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
            BufferedImage gaussImage = mainView.getImagesModel().getGaussImage();
            if(gaussImage == null){
                gaussImage = GaussianBlurAlgorithms.execute(srcImage, 1);
                mainView.getImagesModel().setGaussImage(gaussImage);
            }
            BufferedImage dstImage = SharpeningAlgorithms.execute(srcImage, gaussImage, unsharp, radius);
            mainView.getImagesModel().setDstImage(dstImage);
            mainView.repaint();

            Time.stop(mainView.getTimeLabel());
        }
    }
}
