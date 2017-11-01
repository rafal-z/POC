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
            Time.start();

            BufferedImage srcImage = mView.getImagesModel().getSrcImage();
            BufferedImage gaussImage = mView.getImagesModel().getGaussImage();
            if(gaussImage == null){
                gaussImage = GaussianBlurAlgorithms.execute(srcImage, 1);
                mView.getImagesModel().setGaussImage(gaussImage);
            }
            BufferedImage dstImage = SharpeningAlgorithms.execute(srcImage, gaussImage, unsharp, radius);
            mView.getImagesModel().setDstImage(dstImage);
            mView.repaint();

            Time.stop(mView.getTimeLabel());
        }
    }
}
