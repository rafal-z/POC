package com.pl.poc.view;

import com.pl.poc.controller.AdaptiveMedianController;
import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Rafał on 2017-09-01.
 */
public class AdaptiveMedianSettingsView extends JFrame {
    private MainView mainView;

    private JLabel shapeMaskLabel;
    private JLabel radiusMinLabel;
    private JLabel valueSliderMin;
    private JLabel radiusMaxLabel;
    private JLabel valueSliderMax;
    private JRadioButton squareMaskRB;
    private JRadioButton roundMaskRB;
    private JSlider radiusMinSlider;
    private JSlider radiusMaxSlider;
    private JButton runButton;
    private JButton okButton;
    private JButton cancelButton;
    private ButtonGroup radioButtonGroup;

    public AdaptiveMedianSettingsView(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(500, 280));
        setTitle("Adaptive Median");

        shapeMaskLabel = new JLabel("Shape Mask:");
        radiusMinLabel = new JLabel("Radius min:");
        radiusMaxLabel = new JLabel("Radius max:");
        valueSliderMin = new JLabel("0");
        valueSliderMax = new JLabel("0");
        squareMaskRB = new JRadioButton("Squere Mask");
        roundMaskRB = new JRadioButton("Round Mask");
        radiusMinSlider = new JSlider();
        radiusMaxSlider = new JSlider();
        runButton = new JButton("Run");
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        radioButtonGroup = new ButtonGroup();

        Container contentPane = getContentPane();

        okButton.addActionListener(new UpdateController(mainView, this));

        cancelButton.addActionListener(new CancelController(mainView, this));

        runButton.addActionListener(new AdaptiveMedianController(mainView, this));

        squareMaskRB.setSelected(true);

        radioButtonGroup.add(squareMaskRB);
        radioButtonGroup.add(roundMaskRB);

        radiusMinSlider.setMaximum(10);
        radiusMinSlider.setValue(0);
        radiusMinSlider.setMinorTickSpacing(1);
        radiusMinSlider.setMajorTickSpacing(5);
        radiusMinSlider.setPaintTicks(true);
        radiusMinSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueSliderMin.setText(radiusMinSlider.getValue()+"");
            }
        });
        radiusMinSlider.setEnabled(false);

        radiusMaxSlider.setMaximum(10);
        radiusMaxSlider.setValue(0);
        radiusMaxSlider.setMinorTickSpacing(1);
        radiusMaxSlider.setMajorTickSpacing(5);
        radiusMaxSlider.setPaintTicks(true);
        radiusMaxSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valueSliderMax.setText(radiusMaxSlider.getValue()+"");
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(squareMaskRB)
                                                        .addComponent(shapeMaskLabel)
                                                        .addComponent(roundMaskRB))
                                                .addGap(90, 90, 90)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(radiusMinSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(valueSliderMin))
                                                        .addComponent(radiusMaxLabel)
                                                        .addComponent(radiusMinLabel)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(radiusMaxSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(valueSliderMax, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(runButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(shapeMaskLabel))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(radiusMinLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(squareMaskRB)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(roundMaskRB))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(radiusMinSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(13, 13, 13)
                                                                .addComponent(radiusMaxLabel))
                                                        .addComponent(valueSliderMin))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(valueSliderMax)
                                                        .addComponent(radiusMaxSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(runButton)
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addGap(41, 41, 41))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    public JSlider getRadiusMinSlider() {
        return radiusMinSlider;
    }

    public JSlider getRadiusMaxSlider() {
        return radiusMaxSlider;
    }

    public JRadioButton getSquareMaskRB() {
        return squareMaskRB;
    }

    public JRadioButton getRoundMaskRB() {
        return roundMaskRB;
    }
}
