package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.GaussController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussSettingsView extends JFrame {
    private MainView mainView;

    private JButton cancelButton;
    private JButton okButton;
    private JSlider gaussSlider;
    private JLabel numberLabel;

    GaussSettingsView(MainView mainView){
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {

        gaussSlider = new JSlider();
        numberLabel = new JLabel("1");
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gaussian Blur...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(380, 120));

        gaussSlider.setMaximum(50);
        gaussSlider.setMinimum(1);
        gaussSlider.setValue(1);
        gaussSlider.addChangeListener(new GaussController(mainView, this));

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(gaussSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gaussSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }

    public JLabel getNumberLabel() {
        return numberLabel;
    }

    public void setNumberLabel(JLabel numberLabel) {
        this.numberLabel = numberLabel;
    }
}
