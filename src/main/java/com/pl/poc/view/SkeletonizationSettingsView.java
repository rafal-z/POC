package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.SkeletonizationController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-08-12.
 */
public class SkeletonizationSettingsView extends BaseSettingsView {
    private MainView mainView;

    private JButton cancelButton;
    private JButton okButton;
    private JButton runButton;

    public SkeletonizationSettingsView(MainView mView){
        mainView = mView;
        initComponents();
    }

    private void initComponents() {
        cancelButton = new JButton();
        okButton = new JButton();
        runButton = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Skeletonization...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(380, 150));

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        runButton.setText("Run");
        runButton.setPreferredSize(new Dimension(180, 25));
        runButton.addActionListener(new SkeletonizationController(mainView));

        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10,0,0,0);
        pane.add(runButton, c);

        c.gridy = 1;
        c.gridx = 0;
        c.insets = new Insets(20,10,0,30);
        pane.add(okButton, c);

        c.gridy = 1; //second row
        c.gridx = 3; //4th column
        c.insets = new Insets(20,30,0,0);
        pane.add(cancelButton, c);

        pack();
    }

}
