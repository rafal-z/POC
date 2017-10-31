package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.CannyEdgeController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Rafał on 2017-09-06.
 */
public class CannyEdgeSettingsView extends BaseSettingsView{
    private JLabel highLabel;
    private JLabel lowLabel;
    private JLabel valueLowLabel;
    private JLabel valueHighLabel;
    private JLabel radiusLabel;
    private JLabel valueRadiusLabel;
    private JButton runButton;
    private JButton okButton;
    private JButton cancelButton;
    private JSlider lowThresholdSlider;
    private JSlider highThresholdSlider;
    private JSlider radiusSlider;

    private MainView mainView;

    public CannyEdgeSettingsView(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        runButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();
        highLabel = new JLabel();
        lowLabel = new JLabel();
        lowThresholdSlider = new JSlider(1,255,100);
        highThresholdSlider = new JSlider(1,255,120);
        radiusSlider = new JSlider(1,10,2);
        valueLowLabel = new JLabel();
        valueHighLabel = new JLabel();
        radiusLabel = new JLabel();
        valueRadiusLabel = new JLabel();

        setTitle("Canny Edge Detector");

        Container contentPane = getContentPane();

        highLabel.setText("High Threshold:");

        runButton.setText("Run");
        runButton.addActionListener(new CannyEdgeController(mainView, this));

        okButton.setText("OK");
        okButton.addActionListener(new UpdateController(mainView, this));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new CancelController(mainView,this));

        lowLabel.setText("Low Threshold:");

        radiusLabel.setText("Radius:");

        lowThresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueLowLabel.setText(lowThresholdSlider.getValue()+"");
            }
        });

        highThresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueHighLabel.setText(highThresholdSlider.getValue()+"");
            }
        });

        radiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueRadiusLabel.setText(radiusSlider.getValue()+"");
            }
        });

        valueLowLabel.setText(lowThresholdSlider.getValue()+"");

        valueHighLabel.setText(highThresholdSlider.getValue()+"");

        valueRadiusLabel.setText(radiusSlider.getValue()+"");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(runButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(radiusLabel)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(lowLabel)
                                                                        .addComponent(highLabel))
                                                                .addGap(39, 39, 39)
                                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                                        .addComponent(lowThresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(highThresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(radiusSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(27, 27, 27)
                                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(valueLowLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                                                                .addComponent(valueHighLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                                                                        .addComponent(valueRadiusLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(lowLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(highLabel))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(lowThresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(valueLowLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(highThresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(valueHighLabel))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(radiusSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(valueRadiusLabel)
                                        .addComponent(radiusLabel))
                                .addGap(21, 21, 21)
                                .addComponent(runButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    public JSlider getLowThresholdSlider() {
        return lowThresholdSlider;
    }

    public JSlider getHighThresholdSlider() {
        return highThresholdSlider;
    }

    public JSlider getRadiusSlider() {
        return radiusSlider;
    }
}
