package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.GaussController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-07-30.
 */
public class GaussSettingsView extends BaseSettingsView {
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
        numberLabel = new JLabel("0");
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gaussian Blur...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(380, 120));

        gaussSlider.setMaximum(50);
        gaussSlider.setMinimum(0);
        gaussSlider.setValue(0);
        gaussSlider.addChangeListener(new GaussController(mainView, this));

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2; //2 columns wide
        c.gridx = 0;
        pane.add(gaussSlider, c);

        c.gridwidth = 1;
        c.gridx = 3;
        pane.add(numberLabel, c);

        c.gridy = 1;
        c.gridx = 0;
        c.insets = new Insets(20,0,0,0);
        pane.add(okButton, c);

        c.gridy = 1; //second row
        c.gridx = 3; //4th column
        c.insets = new Insets(20,0,0,0);
        pane.add(cancelButton, c);

        pack();
    }

    public JLabel getNumberLabel() {
        return numberLabel;
    }

    public void setNumberLabel(JLabel numberLabel) {
        this.numberLabel = numberLabel;
    }
}
