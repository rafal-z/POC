package com.pl.poc.controller;

import com.pl.poc.algorithm.MaximumFilterAlgorithms;
import com.pl.poc.algorithm.MinimumFilterAlgorithms;
import com.pl.poc.algorithm.NonlinearFilters;
import com.pl.poc.algorithm.Time;
import com.pl.poc.view.MainView;
import com.pl.poc.view.NonlinearFiltersSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-08-23.
 */
public class ClosingFilterController implements ActionListener {
    private MainView mainView;
    private NonlinearFiltersSettingsView closingFilterView;

    public ClosingFilterController(MainView mView, NonlinearFiltersSettingsView closingView){
        this.mainView = mView;
        this.closingFilterView = closingView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainView.getImagesModel() != null) {
            Time.start();
            BufferedImage srcImage = mainView.getImagesModel().getSrcImage();
            int[] mat;
            int radius = closingFilterView.getRadiusSlider().getValue();
            MaximumFilterAlgorithms maximumFilter = new MaximumFilterAlgorithms();
            MinimumFilterAlgorithms minimumFilter = new MinimumFilterAlgorithms();

            if (closingFilterView.getSquareMask().isSelected()) {
                mat = NonlinearFilters.makeSquareMask(radius * 2 + 1, radius * 2 + 1);
            } else {
                mat = NonlinearFilters.makeRoundMask(radius * 2 + 1, radius * 2 + 1);
            }
            BufferedImage workImage = minimumFilter.execute(srcImage, mat);
            workImage = maximumFilter.execute(workImage, mat);
            mainView.getImagesModel().setDstImage(workImage);
            mainView.repaint();
            Time.stop(mainView.getTimeLabel());
        }
    }
}
