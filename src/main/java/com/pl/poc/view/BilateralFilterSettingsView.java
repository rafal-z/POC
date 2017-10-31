package com.pl.poc.view;

import com.pl.poc.controller.BilateralFilterController;
import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-09-02.
 */
public class BilateralFilterSettingsView extends BaseSettingsView {
    private JLabel sigmaMinLabel;
    private JLabel sigmaMaxLabel;
    private JButton runButton;
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner sigmaMinSpinner;
    private JSpinner sigmaMaxSpinner;

    private MainView mainView;

    public BilateralFilterSettingsView(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        sigmaMinLabel = new JLabel();
        runButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();
        sigmaMaxLabel = new JLabel();
        sigmaMinSpinner = new JSpinner();
        sigmaMaxSpinner = new JSpinner();

        Container contentPane = getContentPane();

        sigmaMinLabel.setText("DistanceSigma:");

        runButton.setText("Run");
        runButton.addActionListener(new BilateralFilterController(mainView, this));

        okButton.setText("OK");
        okButton.addActionListener(new UpdateController(mainView, this));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new CancelController(mainView,this));

        sigmaMaxLabel.setText("IntensitySigma:");

        sigmaMinSpinner.setModel(new SpinnerNumberModel(1.0F, 0.0F, null, 1.0F));

        sigmaMaxSpinner.setModel(new SpinnerNumberModel(1.0F, 0.0F, null, 1.0F));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(sigmaMinLabel)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(sigmaMinSpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(sigmaMaxLabel)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(sigmaMaxSpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                .addComponent(runButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sigmaMinLabel)
                                                        .addComponent(sigmaMinSpinner, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(runButton))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sigmaMaxLabel)
                                                        .addComponent(sigmaMaxSpinner, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
                                .addGap(35, 35, 35)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    public JSpinner getSigmaMinSpinner() {
        return sigmaMinSpinner;
    }

    public JSpinner getSigmaMaxSpinner() {
        return sigmaMaxSpinner;
    }
}
