package com.pl.poc.controller;

import com.pl.poc.algorithm.GaussAlgorithms;
import com.pl.poc.algorithm.SharpeningAlgorithms;
import com.pl.poc.view.MainView;
import com.pl.poc.view.SharpeningSettingsView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningController implements ChangeListener{
    private MainView mView;
    private SharpeningSettingsView sharpView;

    public SharpeningController(MainView mainView, SharpeningSettingsView sharpeningSettingsView){
        mView = mainView;
        sharpView = sharpeningSettingsView;
    }

    public void stateChanged(ChangeEvent e) {
        int radius = sharpView.getGaussSlider().getValue();
        int unsharp = sharpView.getSharpeningSlider().getValue();
        sharpView.getNumberGauss().setText(radius + "");
        sharpView.getNumberSharpening().setText(unsharp + "");

        if (mView.getImagesModel() != null) {
            long startTime = System.currentTimeMillis();

            BufferedImage srcImage = mView.getImagesModel().getSrcImage();
            BufferedImage gaussImage = mView.getImagesModel().getGaussImage();
            if(gaussImage == null){
                gaussImage = GaussAlgorithms.execute(srcImage, 1);
                mView.getImagesModel().setGaussImage(gaussImage);
            }
            BufferedImage dstImage = SharpeningAlgorithms.execute(srcImage, gaussImage, unsharp, radius);
            mView.getImagesModel().setDstImage(dstImage);
            mView.repaint();

            System.out.println("Czas operacji: " + (double) (System.currentTimeMillis() - startTime) / 1000 + "s");
        }
    }
}
