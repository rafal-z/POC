package com.pl.poc.view;

import com.pl.poc.controller.BrightnessController;
import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-07-13.
 */
public class BrightnessSettingsView extends JFrame {
    private MainView mainView;

    private JSlider brightnessSlider;
    private JButton cancelButton;
    private JButton okButton;
    private JSpinner spiner;

    public JSpinner getSpiner() {
        return spiner;
    }

    public void setSpiner(JSpinner spiner) {
        this.spiner = spiner;
    }

    public BrightnessSettingsView(MainView mainView)
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mainView = mainView;
        initComponents();
    }


//    @SuppressWarnings("unchecked")
    private void initComponents() {

        brightnessSlider = new JSlider();
        spiner = new JSpinner();
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Brightness...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(380, 120));

        brightnessSlider.setMaximum(255);
        brightnessSlider.setMinimum(-255);
        brightnessSlider.setValue(0);
        brightnessSlider.addChangeListener(new BrightnessController(mainView,this));

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
                                                .addComponent(brightnessSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(spiner, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spiner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(brightnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }

}
